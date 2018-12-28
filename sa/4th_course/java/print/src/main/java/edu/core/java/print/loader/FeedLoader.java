package edu.core.java.print.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.core.java.print.domain.Feed;
import edu.core.java.print.repository.FeedRepository;
import edu.core.java.print.translator.FeedTranslator;
import edu.core.java.print.vo.FeedValueObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedLoader extends Loader<Feed> {

    public FeedRepository getRepository() {

        try {
            JsonNode node = getRootNode();
            List<Feed> feeds = mapper.readValue(node.get("feed").toString(), new TypeReference<List<Feed>>(){});

            FeedRepository repository = new FeedRepository();
            FeedTranslator translator = new FeedTranslator();

            for (Feed feed : feeds) {
                repository.add(translator.translate(feed, node));
            }

            return repository;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void upload(FeedRepository feedRepository) {
        try {
            List<Feed> feeds = new ArrayList<Feed>();
            FeedTranslator translator = new FeedTranslator();

            for (FeedValueObject feedVO : feedRepository.findAll()) {
                feeds.add(translator.translate(feedVO));
            }

            // update

            ObjectNode tree = (ObjectNode) getRootNode();
            tree.remove("feed");
            tree.set("feed", mapper.valueToTree(feeds));

            // write

            BufferedWriter writer = getFileWriter();
            mapper.writeValue(writer, tree);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
