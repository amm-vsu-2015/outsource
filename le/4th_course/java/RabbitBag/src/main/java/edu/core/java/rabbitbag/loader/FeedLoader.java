package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;

import java.io.IOException;
import java.util.List;

public class FeedLoader extends Loader<JsonFileObject> {

    public List<Feed> load() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser parser = getParserFromJsonDB();
            JsonNode node = mapper.readTree(parser);
            return mapper.readValue(node.get("feed").toString(), new TypeReference<List<Feed>>(){});
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
