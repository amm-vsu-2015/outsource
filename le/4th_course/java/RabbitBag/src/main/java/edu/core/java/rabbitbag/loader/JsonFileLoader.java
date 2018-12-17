package edu.core.java.rabbitbag.loader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;

import java.io.IOException;
import java.util.List;

public class JsonFileLoader extends Loader<JsonFileObject> {

    public JsonFileObject loadKits() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser parser = getParserFromJsonDB();
            JsonNode node = mapper.readTree(parser);

            List<Kits> kits = mapper.readValue(node.get("kits").toString(), new TypeReference<List<Kits>>(){});

            return mapper.readValue(parser, JsonFileObject.class);
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
