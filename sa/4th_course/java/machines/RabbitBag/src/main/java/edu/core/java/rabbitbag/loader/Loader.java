package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.DomainObject;

import java.io.*;
import java.nio.file.Files;

public class Loader<D extends DomainObject> {

    ObjectMapper mapper = new ObjectMapper();

    // private properties

    private String jsonFilePath() {
        return new File("").getAbsolutePath() + "/data/data.json";
    }

    // service for load entities from json

    JsonParser getParserFromJsonDB() throws IOException {
        JsonFactory factory = new JsonFactory();
        BufferedReader reader = Files.newBufferedReader(new File(jsonFilePath()).toPath());
        return factory.createParser(reader);
    }

    BufferedWriter getFileWriter() throws IOException {
        return Files.newBufferedWriter(new File(jsonFilePath()).toPath());
    }

    public JsonNode getRootNode() {
        try {
            JsonParser parser = getParserFromJsonDB();
            return mapper.readTree(parser);

        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
