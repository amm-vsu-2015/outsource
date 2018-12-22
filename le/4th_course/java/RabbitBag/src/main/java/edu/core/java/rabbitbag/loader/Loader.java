package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import edu.core.java.rabbitbag.Main;
import edu.core.java.rabbitbag.domain.DomainObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarFile;

public class Loader<D extends DomainObject> {

    // service for load entities from json

    protected JsonParser getParserFromJsonDB() throws IOException {
        JsonFactory factory = new JsonFactory();
        //InputStream in = Main.class.getResourceAsStream("/data/data.json");
        // BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        // (new File("").getAbsolutePath() + "/classes/data/data.json")/
        String s = new File("").getAbsolutePath() + "/data/data.json";
        BufferedReader reader = Files.newBufferedReader(new File(s).toPath());
        return factory.createParser(reader);
    }

    protected BufferedWriter getFileWriter() throws IOException {
//        ClassLoader classLoader = Main.class.getClassLoader();
//        File file = new File(classLoader.getResource("data/data.json").getFile());
        String s = new File("").getAbsolutePath() + "/data/data.json";
        return Files.newBufferedWriter(new File(s).toPath());
    }

}
