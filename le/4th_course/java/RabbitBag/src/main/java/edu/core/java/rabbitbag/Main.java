package edu.core.java.rabbitbag;

import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.loader.FeedLoader;
import edu.core.java.rabbitbag.loader.KitsLoader;
import edu.core.java.rabbitbag.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        KitsLoader kitsLoader = new KitsLoader();
        List<Kits> kits = kitsLoader.load();
        kits.forEach(kit -> System.out.println(kit.getName()));

        // setup loader
        FeedLoader feedLoader = new FeedLoader();

        // get repo
        FeedRepository feedRepository = feedLoader.getRepository();

        // get feeds
        feedRepository
                .findAll()
                .forEach(fd -> System.out.println(fd.getBrand()));

        feedRepository.remove(1);

        // get feeds
        feedRepository
                .findAll()
                .forEach(fd -> System.out.println(fd.getName()));

        feedLoader.upload(feedRepository);
    }

}
