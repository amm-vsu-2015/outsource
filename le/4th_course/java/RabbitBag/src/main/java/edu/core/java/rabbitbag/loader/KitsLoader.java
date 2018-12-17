package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;

import java.io.IOException;
import java.util.List;

public class KitsLoader extends Loader<JsonFileObject> {

    public List<Kits> load() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser parser = getParserFromJsonDB();
            JsonNode node = mapper.readTree(parser);
            return mapper.readValue(node.get("kits").toString(), new TypeReference<List<Kits>>(){});
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
