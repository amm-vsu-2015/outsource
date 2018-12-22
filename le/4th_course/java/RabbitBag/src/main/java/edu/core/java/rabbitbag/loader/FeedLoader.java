package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.repository.FeedRepository;
import edu.core.java.rabbitbag.translator.FeedTranslator;
import edu.core.java.rabbitbag.vo.FeedValueObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedLoader extends Loader<JsonFileObject> {

    private ObjectMapper mapper = new ObjectMapper();

    public FeedRepository getRepository() {

        try {
            JsonParser parser = getParserFromJsonDB();

            // collect object

            JsonNode node = mapper.readTree(parser);
            List<Feed> feeds = mapper.readValue(node.get("feed").toString(), new TypeReference<List<Feed>>(){});

            FeedRepository repository = new FeedRepository();
            FeedTranslator fTranslator = new FeedTranslator();

            for (Feed feed : feeds) {
                repository.add(fTranslator.translate(feed, node));
            }

            return repository;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void upload(FeedRepository feedRepository) {
        try {
            JsonParser parser = getParserFromJsonDB();

            JsonNode node = mapper.readTree(parser);
            JsonNode brandTree = node.get("brand");

            for (JsonNode brandNode : brandTree) {
                Long id = mapper.readValue(brandNode.get("id").toString(), Long.class);
                if (id == 2) {
                    ObjectNode on = ((ObjectNode) brandNode).put("name", "lololl");
                    break;
                }
            }

            List<Feed> feeds = new ArrayList<Feed>();
            FeedTranslator fTranslator = new FeedTranslator();

            for (FeedValueObject feedVO : feedRepository.findAll()) {
                feeds.add(fTranslator.translate(feedVO));
            }

            ObjectNode feedTree = (ObjectNode) node;

            feedTree.remove("feed");
            feedTree.set("feed", mapper.valueToTree(feeds));

            BufferedWriter writer = getFileWriter();
            mapper.writeValue(writer, feedTree);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
