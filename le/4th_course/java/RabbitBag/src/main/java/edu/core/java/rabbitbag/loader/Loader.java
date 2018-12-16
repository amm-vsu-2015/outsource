package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import edu.core.java.rabbitbag.Main;
import edu.core.java.rabbitbag.domain.DomainObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Loader<D extends DomainObject> {

    // service for load entities from json

    protected JsonParser getParserFromJsonDB() throws IOException {
        JsonFactory factory = new JsonFactory();
        InputStream in = Main.class.getResourceAsStream("/data/data.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return factory.createParser(in);
    }

}
