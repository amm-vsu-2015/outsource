package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.repository.FeedRepository;
import edu.core.java.rabbitbag.repository.KitsRepository;
import edu.core.java.rabbitbag.translator.FeedTranslator;
import edu.core.java.rabbitbag.translator.KitsTranslator;
import edu.core.java.rabbitbag.vo.FeedValueObject;
import edu.core.java.rabbitbag.vo.KitsValueObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    public void upload(KitsRepository kitsRepository) {
        try {
            JsonParser parser = getParserFromJsonDB();

            JsonNode node = mapper.readTree(parser);

            List<Kits> Kits = new ArrayList<Kits>();
            KitsTranslator kTranslator = new KitsTranslator();

            for (KitsValueObject feedVO : kitsRepository.findAll()) {
                Kits.add(kTranslator.translate(feedVO));
            }

            ObjectNode KitsTree = (ObjectNode) node;

            KitsTree.remove("Kits");
            KitsTree.set("kits", mapper.valueToTree(Kits));

            BufferedWriter writer = getFileWriter();
            mapper.writeValue(writer, KitsTree);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
