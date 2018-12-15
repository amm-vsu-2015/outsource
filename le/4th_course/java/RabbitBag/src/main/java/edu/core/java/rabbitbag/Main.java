package edu.core.java.rabbitbag;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.CodeSource;
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

        JsonFactory f = new JsonFactory();

        ObjectMapper mapper = new ObjectMapper();

        try {
            InputStream in = Main.class.getResourceAsStream("/data/data.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            JsonParser p = f.createParser(in);
            JsonFileObject k = mapper.readValue(p, JsonFileObject.class);

            for (int i = 0; i < k.kits.size(); i++) {
                System.out.println(k.kits.get(i).getName());
            }

        } catch (IOException ex) {
             logger.debug(ex.toString());
        }
    }

}
