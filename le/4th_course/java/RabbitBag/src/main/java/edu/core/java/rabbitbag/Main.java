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

public class Main {

    // javac -d out src/main/java/edu/core/java/rabbitbag/Main.java
    // out
    // jar cmvf ../src/main/resources/META-INF/manifest.mf ../out/rb.jar edu/core/java/rabbitbag/Main.class
    // cd ..
    // java -jar ./out/rb.jar
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("edu.core.java.rabbitbag.Main");
        logger.debug("Hello, debugger!");

        char answer = 'y';

        try {
            while (answer != 'n') {

                // setup loaders
                FeedLoader feedLoader = new FeedLoader();
                KitsLoader kitsLoader = new KitsLoader();

                // get repos
                FeedRepository feedRepository = feedLoader.getRepository();
                KitsRepository kitsRepository = kitsLoader.getRepository();

                // get translators
                FeedTranslator feedsTranslator = new FeedTranslator();
                KitsTranslator kitsTranslator = new KitsTranslator();

                // define conditions

                List<FeedValueObject> feeds = feedRepository.findAll();
                List<KitsValueObject> kits = kitsRepository.findAll();

                System.out.println("\nWhat are we have to store in " + ApplicationProperties.shared().getDatabaseType() + " now:");
                System.out.println("[ ] 1. Feeds (" + feeds.size() + " in store)");
                System.out.println("[ ] 2. Kits  (" + kits.size() + " in store)");

                answer = askUser("\nPlease, write number of entity for showing some actions: ");

                switch (answer) {
                    case '1':
                        feedsFlow(feedLoader, feedRepository, feedsTranslator);
                        break;
                    case '2':
                        kitsFlow(kitsLoader, kitsRepository, kitsTranslator);
                        break;
                    default:
                        System.out.println("\nUnfortunately, entity with this id not found...");
                }

                System.out.println("Please, try again...\n\n");

            }

        }  catch (IOException|ParseException e) {
            System.out.println(e);
        }

    }

    private static void feedsFlow(FeedLoader loader, FeedRepository repository, FeedTranslator translator) throws IOException, ParseException {
        Character answer = 'y';

        while (answer != 'n') {

            System.out.println("\n\n[#] 1. Feeds selected.");
            printBaseActions();

            answer = askUser("Please, select your action number (or write 'n' to back): ");
            System.out.println();

            switch (answer) {
                case '1':
                    Feed createdFeed = createFeed((new Date()).getTime(), repository, translator);
                    repository.add(translator.translate(createdFeed, loader.getFeedsRootNode()));
                    loader.upload(repository);
                    break;
                case '2':

                    System.out.println("Feed's repository have the next items:");
                    showFeeds(repository, translator);

                    answer = askUser("\nSelect id index of entity what you want to [ update ] or 'n' to cancel action: ");

                    if (answer.equals('n')) {
                        System.out.println("Canceling remove action...");
                        break;
                    }

                    updateFeedBy(Long.valueOf(answer.toString()), loader, repository, translator);

                    break;
                case '3':

                    System.out.println("Feeds repository have the next items:");
                    showFeeds(repository, translator);

                    answer= askUser("\nSelect id index of entity what you want to [ remove ] or 'n' to cancel action: ");

                    if (answer.equals('n')) {
                        System.out.println("Canceling remove action...");
                        break;
                    }

                    removeFeedBy(Long.valueOf(answer.toString()), repository);

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
                // todo: maybe move find by id into entity
                selectedVO = vo;
                break;
            }
        }

        Feed ent = translator.translate(selectedVO);

        System.out.println("\nYou selected the next entity:");
        System.out.println(ent.toJSON());

        Feed updatedFeed = createFeed(ent.getId(), repository, translator);
        repository.update(translator.translate(updatedFeed, loader.getFeedsRootNode()));
        loader.upload(repository);
        System.out.println();
    }

    private static Feed createFeed(long id, FeedRepository repository, FeedTranslator translator) throws IOException, ParseException {
        // todo getters / setters of new Feed value
        // todo shows detail table views (as feed_type) in json value.

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Write name: ");
        String name = buffer.readLine().toLowerCase();

        System.out.print("Write brand: ");
        long brand = Long.valueOf(buffer.readLine().toLowerCase());

        System.out.print("Write date: (as 2018-12-31) ");
        String dateString = buffer.readLine().toLowerCase();
        Date date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(dateString);

        System.out.print("Write type: ");
        long type = Long.valueOf(buffer.readLine().toLowerCase());

        return new Feed(id, name, brand, date, type);
    }

    private static void showFeeds(FeedRepository repository, FeedTranslator translator) {
        repository.findAll().forEach(feedVO -> System.out.println(translator.translate(feedVO).toJSON()));
    }




    // kits flow

    private static void kitsFlow(KitsLoader loader, KitsRepository repository, KitsTranslator translator) throws IOException, ParseException {
        Character answer = 'y';

        while (answer != 'n') {

            System.out.println("\n\n[#] 1. Kits selected.");
            printBaseActions();

            answer = askUser("Please, select your action number (or write 'n' to back): ");
            System.out.println();

            switch (answer) {
                case '1':
                    Kits createdKit = createKit((new Date()).getTime(), repository, translator);
                    repository.add(translator.translate(createdKit, loader.getFeedsRootNode()));
                    loader.upload(repository);
                    break;
                case '2':

                    System.out.println("Kit's repository have the next items:");
                    showKits(repository, translator);

                    answer = askUser("\nSelect id index of entity what you want to [ update ] or 'n' to cancel action: ");

                    if (answer.equals('n')) {
                        System.out.println("Canceling remove action...");
                        break;
                    }

                    updateKitBy(Long.valueOf(answer.toString()), loader, repository, translator);

                    break;
                case '3':

                    System.out.println("Feeds repository have the next items:");
                    showKits(repository, translator);

                    answer= askUser("\nSelect id index of entity what you want to [ remove ] or 'n' to cancel action: ");

                    if (answer.equals('n')) {
                        System.out.println("Canceling remove action...");
                        break;
                    }

                    removeKitBy(Long.valueOf(answer.toString()), repository);

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
        repository.update(translator.translate(updatedKit, loader.getFeedsRootNode()));
        loader.upload(repository);
        System.out.println();
    }

    private static Kits createKit(long id, KitsRepository repository, KitsTranslator translator) throws IOException, ParseException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Write name: ");
        String name = buffer.readLine().toLowerCase();

        System.out.print("Write description: ");
        String description = buffer.readLine().toLowerCase();

        System.out.print("Write restruction: ");
        long restriction = Long.valueOf(buffer.readLine().toLowerCase());

        return new Kits(id, name, description, restriction);
    }

    private static void showKits(KitsRepository repository, KitsTranslator translator) {
        repository.findAll().forEach(feedVO -> System.out.println(translator.translate(feedVO).toJSON()));
    }





    // supports

    private static void printBaseActions() {
        System.out.println("You have a few actions: ");
        System.out.println("1. Add new entity.");
        System.out.println("2. Update entity by id.");
        System.out.println("3. Remove entity by id.");
        System.out.println("4. Show entity list.\n");
    }


    private static Character askUser(String text) throws IOException {
        System.out.print(text);

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String answer = buffer.readLine().toLowerCase();

        if (answer.length() > 1) {
            System.out.println("You wrote large answer, we take only first char...");
        }

        return answer.trim().charAt(0);
    }

}
