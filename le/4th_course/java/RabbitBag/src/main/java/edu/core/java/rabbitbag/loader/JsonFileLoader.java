package edu.core.java.rabbitbag.loader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;

import java.io.IOException;
import java.util.List;

public class JsonFileLoader extends Loader<JsonFileObject> {

    public List<Kits> loadKits() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser parser = getParserFromJsonDB();
            JsonFileObject kits = mapper.readValue(parser, JsonFileObject.class);
            return kits.kits;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
