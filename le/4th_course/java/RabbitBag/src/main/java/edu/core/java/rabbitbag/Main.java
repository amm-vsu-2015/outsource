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

            FeedTranslator feedstranslator = new FeedTranslator();
            KitsTranslator kitsTranslator = new KitsTranslator();
            
            // define conditions

            List<FeedValueObject> feeds = feedRepository.findAll();
            List<KitsValueObject> kits = kitsRepository.findAll();

            System.out.println("What we are have to store in database now:");

            if (!feeds.isEmpty()) {
                System.out.println("Feeds: " + feeds.size());
            }

            if (!kits.isEmpty()) {
                System.out.println("Kits: " + kits.size());
            }

            feeds.forEach(feedVO -> System.out.println(feedstranslator.translate(feedVO).toJSON()));
            System.out.println();
            kits.forEach(kitVO -> System.out.println(kitsTranslator.translate(kitVO).toJSON()));

            break;

        }
    }

    private static Character askUser() throws IOException {

        System.out.println("\nPlease, write char in A-Z ranges:");

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String answer = buffer.readLine().toUpperCase();

        if (answer.length() > 1) {
            System.out.println("You wrote answer that has more length than requied. We will use only first char of string...");
        }

        return answer.trim().charAt(0);
    }

}
