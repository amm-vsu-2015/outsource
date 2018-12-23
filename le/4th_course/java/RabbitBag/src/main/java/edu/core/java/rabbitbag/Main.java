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
import java.util.List;

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

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    // todo maybe abstract with
    private static void feedsFlow(FeedLoader loader, FeedRepository repository, FeedTranslator translator) throws IOException {

        Character answer = 'y';

        while (answer != 'n') {

            System.out.println("\n\n[#] 1. Feeds selected (\" + feeds.size() + \" in store).\n");

            System.out.println("You have a few actions: ");
            System.out.println("1. Add new entity.");
            System.out.println("2. Update entity by id.");
            System.out.println("3. Remove entity by id.");
            System.out.println("4. Show entity list.\n");

            answer = askUser("Please, select your action number (or write 'n' to back): ");
            System.out.println();

            switch (answer) {
                case '1':
                    break;
                case '2':

                    

                    break;
                case '3':

                    System.out.println("Feeds repository have the next items:");

                    List<FeedValueObject> feedVOs = repository.findAll();
                    feedVOs.forEach(feedVO -> System.out.println(translator.translate(feedVO).toJSON()));

                    Character idChar= askUser("\n Select id index of entity what you want to remove or 'n' to cancel action: ");

                    if (idChar.equals('n')) {
                        System.out.println("Canceling remove action...");
                        break;
                    }

                    long id = Long.parseLong(idChar.toString());

                    for (FeedValueObject vo : feedVOs) {
                        if (id == vo.getId()) {
                            repository.remove(id);
                            break;
                        }
                    }

                    break;
                case '4':
                    repository.findAll().forEach(feedVO -> System.out.println(translator.translate(feedVO).toJSON()));
            }
        }
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
