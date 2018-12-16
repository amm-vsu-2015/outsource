package edu.core.java.rabbitbag;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.loader.JsonFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.CodeSource;
import java.util.List;
import java.util.Properties;

public class Main {

    // javac -d out src/main/java/edu/core/java/rabbitbag/Main.java
    // out
    // jar cmvf ../src/main/resources/META-INF/manifest.mf ../out/rb.jar edu/core/java/rabbitbag/Main.class
    // cd ..
    // java -jar ./out/rb.jar
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("edu.core.java.rabbitbag.Main");
        logger.debug("Hello, debugger!");

        JsonFileLoader loader = new JsonFileLoader();

        List<Kits> kits = loader.loadKits();

        if (kits != null) {
            for (int i = 0; i < kits.size(); i++) {
                System.out.println(kits.get(i).getName());
            }
        }
    }

}
