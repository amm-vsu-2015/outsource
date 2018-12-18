package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.repository.FeedRepository;
import edu.core.java.rabbitbag.vo.FeedValueObject;

import java.io.IOException;
import java.util.List;

public class FeedLoader extends Loader<JsonFileObject> {

    public FeedRepository getRepository() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser parser = getParserFromJsonDB();
            JsonNode node = mapper.readTree(parser);
            List<Feed> feeds = mapper.readValue(node.get("feed").toString(), new TypeReference<List<Feed>>(){});

            FeedRepository repository = new FeedRepository();

            for (Feed feed : feeds) {
                repository.add(new FeedValueObject(feed));
            }

            return repository;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    // todo set update for repository -> entities
}
