package edu.core.java.rabbitbag;

import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.loader.FeedLoader;
import edu.core.java.rabbitbag.loader.KitsLoader;
import edu.core.java.rabbitbag.repository.FeedRepository;
import edu.core.java.rabbitbag.repository.KitsRepository;
import edu.core.java.rabbitbag.shared.ApplicationProperties;
import edu.core.java.rabbitbag.translator.FeedTranslator;
import edu.core.java.rabbitbag.translator.KitsTranslator;
import edu.core.java.rabbitbag.vo.FeedValueObject;
import edu.core.java.rabbitbag.vo.KitsValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// configs
// exec:java -D envType=dev // dev environment

public class Main {

    private static Logger logger = LoggerFactory.getLogger("edu.core.java.rabbitbag.Main");

    // javac -d out src/main/java/edu/core/java/rabbitbag/Main.java
    // out
    // jar cmvf ../src/main/resources/META-INF/manifest.mf ../out/rb.jar edu/core/java/rabbitbag/Main.class
    // cd ..
    // java -jar ./out/rb.jar
    public static void main(String[] args) {

        logger.debug("Начало выполнения программы");

        char answer = 'y';

        try {
            while (answer != 'n') {

                // setup loaders
                FeedLoader feedLoader = new FeedLoader();
                KitsLoader kitsLoader = new KitsLoader();

                // get repos
                FeedRepository feedRepository = feedLoader.getRepository();
                KitsRepository kitsRepository = kitsLoader.download();

                // get translators
                FeedTranslator feedsTranslator = new FeedTranslator();
                KitsTranslator kitsTranslator = new KitsTranslator();

                // define conditions

                List<FeedValueObject> feeds = feedRepository.findAll();
                List<KitsValueObject> kits = kitsRepository.findAll();

                System.out.println("\nВ базе данных " + ApplicationProperties.shared().getDatabaseType() + " хранятся следующие сущности:");
                System.out.println("[ ] 1. Питание (" + feeds.size() + " шт.)");
                System.out.println("[ ] 2. Наборы  (" + kits.size() + " шт.)");

                answer = askUser("\nВыберите номер необходимой сущности, чтобы посмотреть доступные действия: (или 'n' для отмены) ");

                switch (answer) {
                    case '1':
                        feedsFlow(feedLoader, feedRepository, feedsTranslator);
                        break;
                    case '2':
                        kitsFlow(kitsLoader, kitsRepository, kitsTranslator);
                        break;
                    default:
                        System.out.println("\nК сожалению, такой сущности не существует...");
                }

                System.out.println("Попробуйте снова...\n\n");

            }

        }  catch (IOException|ParseException e) {
            System.out.println(e);
        }

    }

    private static void feedsFlow(FeedLoader loader, FeedRepository repository, FeedTranslator translator) throws IOException, ParseException {
        Character answer = 'y';
        String id = "";
        BufferedReader buffer;

        while (answer != 'n') {

            System.out.println("\n\n[#] 1. Выбрано 'питание'.");
            printBaseActions();

            answer = askUser("Вам доступны следующие действия, выберите номер (или 'n' для отмены): ");
            System.out.println();

            switch (answer) {
                case '1':
                    Feed createdFeed = createFeed((new Date()).getTime(), repository, translator);
                    repository.add(translator.translate(createdFeed, loader.getRootNode()));
                    loader.upload(repository);
                    logger.debug("Сущность Feed создана");
                    break;
                case '2':

                    System.out.println("В репозитории 'питание' находятся следующие сущности:");
                    showFeeds(repository, translator);

                    System.out.print("\nВыберите номер 'id' для [ обновления ] сущности (или 'n' для отмены): ");
                    buffer = new BufferedReader(new InputStreamReader(System.in));
                    id = buffer.readLine().toLowerCase();

                    if (id.equals('n')) {
                        System.out.println("Отмена обновления сущностей...");
                        break;
                    }

                    updateFeedBy(Long.valueOf(id.toString()), loader, repository, translator);
                    logger.debug("Сущность Feed обновлена");

                    break;
                case '3':

                    System.out.println("В репозитории 'питание' находятся следующие сущности:");
                    showFeeds(repository, translator);

                    System.out.print("\nВыберите номер 'id' для [ удаления ] сущности (или 'n' для отмены): ");
                    buffer = new BufferedReader(new InputStreamReader(System.in));
                    id = buffer.readLine().toLowerCase();

                    if (id.equals('n')) {
                        System.out.println("Отмена обновления сущностей...");
                        break;
                    }

                    removeFeedBy(Long.valueOf(id.toString()), repository);
                    logger.debug("Сущность Feed удалена");

                    break;
                case '4':
                    showFeeds(repository, translator);
            }
        }
    }


    // feeds flow

    private static void removeFeedBy(long id, FeedRepository repository) {
        List<FeedValueObject> feedVOs = repository.findAll();

        for (FeedValueObject vo : feedVOs) {
            if (id == vo.getId()) {
                repository.remove(id);
                break;
            }
        }
    }

    private static void updateFeedBy(long id, FeedLoader loader, FeedRepository repository, FeedTranslator translator) throws IOException, ParseException {
        List<FeedValueObject> feedVOs = repository.findAll();
        FeedValueObject selectedVO = null;

        for (FeedValueObject vo : feedVOs) {
            if (id == vo.getId()) {
                selectedVO = vo;
                break;
            }
        }

        Feed ent = translator.translate(selectedVO);

        System.out.println("\nВыбрана следующая сущность для обновления:");
        System.out.println(ent.toJSON());

        Feed updatedFeed = createFeed(ent.getId(), repository, translator);
        repository.update(translator.translate(updatedFeed, loader.getRootNode()));
        loader.upload(repository);
        System.out.println();
    }

    private static Feed createFeed(long id, FeedRepository repository, FeedTranslator translator) throws IOException, ParseException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите имя: ");
        String name = buffer.readLine().toLowerCase();

        System.out.print("Введите id бренда: ");
        long brand = Long.valueOf(buffer.readLine().toLowerCase());

        System.out.print("Введите дату: (в формате 2018-12-31) ");
        String dateString = buffer.readLine().toLowerCase();
        Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(dateString);

        System.out.print("Введите тип питания (по id): ");
        long type = Long.valueOf(buffer.readLine().toLowerCase());

        return new Feed(id, name, brand, date, type);
    }

    private static void showFeeds(FeedRepository repository, FeedTranslator translator) {
        repository.findAll().forEach(feedVO -> System.out.println(translator.translate(feedVO).toJSON()));
    }




    // kits flow

    private static void kitsFlow(KitsLoader loader, KitsRepository repository, KitsTranslator translator) throws IOException, ParseException {
        Character answer = 'y';
        String id = "";
        BufferedReader buffer;

        while (answer != 'n') {

            System.out.println("\n\n[#] 1. Выбрано 'наборы'.");
            printBaseActions();

            answer = askUser("Вам доступны следующие действия, выберите номер (или 'n' для отмены): ");
            System.out.println();

            switch (answer) {
                case '1':
                    Kits createdKit = createKit((new Date()).getTime(), repository, translator);
                    repository.add(translator.translate(createdKit, loader.getRootNode()));
                    loader.upload(repository);
                    logger.debug("Сущность kits создана");
                    break;
                case '2':

                    System.out.println("В репозитории 'питание' находятся следующие сущности:");
                    showKits(repository, translator);

                    System.out.print("\nВыберите номер 'id' для [ обновления ] сущности (или 'n' для отмены): ");
                    buffer = new BufferedReader(new InputStreamReader(System.in));
                    id = buffer.readLine().toLowerCase();

                    if (id.equals("n")) {
                        System.out.println("Отмена обновления сущностей...");
                        break;
                    }

                    updateKitBy(Long.valueOf(id.toString()), loader, repository, translator);
                    logger.debug("Сущность kits обновлена");

                    break;
                case '3':

                    System.out.println("В репозитории 'питание' находятся следующие сущности:");
                    showKits(repository, translator);

                    System.out.print("\nВыберите номер 'id' для [ удаления ] сущности (или 'n' для отмены): ");
                    buffer = new BufferedReader(new InputStreamReader(System.in));
                    id = buffer.readLine().toLowerCase();

                    if (id.equals("n")) {
                        System.out.println("Отмена обновления сущностей...");
                        break;
                    }

                    removeKitBy(Long.valueOf(id.toString()), repository);
                    logger.debug("Сущность kits удалена");

                    break;
                case '4':
                    showKits(repository, translator);
            }
        }
    }


    // feeds flow

    private static void removeKitBy(long id, KitsRepository repository) {
        List<KitsValueObject> KitVOs = repository.findAll();

        for (KitsValueObject vo : KitVOs) {
            if (id == vo.getId()) {
                repository.remove(id);
                break;
            }
        }
    }

    private static void updateKitBy(long id, KitsLoader loader, KitsRepository repository, KitsTranslator translator) throws IOException, ParseException {
        List<KitsValueObject> KitVOs = repository.findAll();
        KitsValueObject selectedVO = null;

        for (KitsValueObject vo : KitVOs) {
            if (id == vo.getId()) {
                selectedVO = vo;
                break;
            }
        }

        Kits ent = translator.translate(selectedVO);

        System.out.println("\nYou selected the next entity:");
        System.out.println(ent.toJSON());

        Kits updatedKit = createKit(ent.getId(), repository, translator);
        repository.update(translator.translate(updatedKit, loader.getRootNode()));
        loader.upload(repository);
        System.out.println();
    }

    private static Kits createKit(long id, KitsRepository repository, KitsTranslator translator) throws IOException, ParseException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите имя: ");
        String name = buffer.readLine().toLowerCase();

        System.out.print("Введите описание: ");
        String description = buffer.readLine().toLowerCase();

        System.out.print("Введите ограничение (по id): ");
        long restriction = Long.valueOf(buffer.readLine().toLowerCase());

        return new Kits(id, name, description, restriction);
    }

    private static void showKits(KitsRepository repository, KitsTranslator translator) {
        repository.findAll().forEach(feedVO -> System.out.println(translator.translate(feedVO).toJSON()));
    }





    // supports

    private static void printBaseActions() {
        System.out.println("Список доступных действия с сущностями: ");
        System.out.println("1. Добавить новую сущность.");
        System.out.println("2. Обновить по id.");
        System.out.println("3. Удалить по id.");
        System.out.println("4. Показать список всех сущностей.\n");
    }


    private static Character askUser(String text) throws IOException {
        System.out.print(text);

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String answer = buffer.readLine().toLowerCase();

        if (answer.length() > 1) {
            System.out.println("Вы ввели слишком длинный ответ, мы возьмем первый введенный вами символ...");
        }

        return answer.trim().charAt(0);
    }

}
