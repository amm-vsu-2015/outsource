package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.repository.FeedRepository;
import edu.core.java.rabbitbag.repository.KitsRepository;
import edu.core.java.rabbitbag.translator.FeedTranslator;
import edu.core.java.rabbitbag.translator.KitsTranslator;

import java.io.IOException;
import java.util.List;

public class KitsLoader extends Loader<JsonFileObject> {

    public KitsRepository getRepository() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser parser = getParserFromJsonDB();

            // collect object

            JsonNode node = mapper.readTree(parser);
            List<Kits> kits = mapper.readValue(node.get("kits").toString(), new TypeReference<List<Kits>>(){});

            KitsRepository repository = new KitsRepository();
            KitsTranslator kTranslator = new KitsTranslator();

            for (Kits kit : kits) {
                repository.add(kTranslator.translate(kit, node));
            }

            return repository;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
