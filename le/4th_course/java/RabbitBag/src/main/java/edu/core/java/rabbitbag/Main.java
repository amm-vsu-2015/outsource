package edu.core.java.rabbitbag;

import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.loader.FeedLoader;
import edu.core.java.rabbitbag.loader.KitsLoader;
import edu.core.java.rabbitbag.repository.FeedRepository;
import edu.core.java.rabbitbag.repository.KitsRepository;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

                // todo: get actual data
                // todo: print list of created entities on the screen (or add new)
                // todo: ask user what type of ent will change
                // todo: show all ent of selected type
                // todo: show available actions
                // todo: make some action
                // todo: update repos & update file
                // todo: return to 1st step

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

                System.out.println("\nWhat are we have to store in database now:");
                System.out.println("[ ] 1. Feeds (" + feeds.size() + " in store)");
                System.out.println("[ ] 2. Kits  (" + kits.size() + " in store)");

                Character flowType = askUser("\nPlease, write number of entity for showing some actions: ");

                switch (flowType) {
                    case '1':
                        feedsFlow(feedLoader, feedRepository, feedsTranslator);
                        break;
                    case '2':
                        // todo kits flow
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

    // todo maybe abstract with
    private static void feedsFlow(FeedLoader loader, FeedRepository repository, FeedTranslator translator) throws IOException, ParseException {

        char answer = 'y';

        while (answer != 'n') {

            Character idChar = 'y';

            System.out.println("\n\n[#] 1. Feeds selected.\n");
            printBaseActions();

            answer = askUser("Please, select your action number (or write 'n' to back): ");
            System.out.println();

            switch (answer) {
                case '1':
                    break;
                case '2':

                    System.out.println("Feeds repository have the next items:");
                    showFeeds(repository, translator);

                    idChar = askUser("\nSelect id index of entity what you want to [ update ] or 'n' to cancel action: ");

                    if (idChar.equals('n')) {
                        System.out.println("Canceling remove action...");
                        break;
                    }

                    updateFeedBy(Long.valueOf(idChar.toString()), repository, translator);

                    break;
                case '3':

                    System.out.println("Feeds repository have the next items:");
                    showFeeds(repository, translator);

                    idChar= askUser("\nSelect id index of entity what you want to [ remove ] or 'n' to cancel action: ");

                    if (idChar.equals('n')) {
                        System.out.println("Canceling remove action...");
                        break;
                    }

                    removeFeedBy(Long.valueOf(idChar.toString()), repository);

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

    private static void updateFeedBy(long id, FeedRepository repository, FeedTranslator translator) throws IOException, ParseException {
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
        // repository.update(translator.t);

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
//        Date date = (Date) LocalDate.parse(dateString, formatter);
//        System.out.println(date);

        System.out.print("Write type: ");
        long type = Long.valueOf(buffer.readLine().toLowerCase());

        return new Feed(id, name, brand, date, type);
    }

    private static void showFeeds(FeedRepository repository, FeedTranslator translator) {
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
