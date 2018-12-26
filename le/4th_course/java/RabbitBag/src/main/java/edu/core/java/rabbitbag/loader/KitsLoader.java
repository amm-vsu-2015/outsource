package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.repository.KitsRepository;
import edu.core.java.rabbitbag.translator.KitsTranslator;
import edu.core.java.rabbitbag.vo.KitsValueObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KitsLoader extends Loader<Kits> {

    public KitsRepository download() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = getRootNode();
            List<Kits> kits = mapper.readValue(node.get("kits").toString(), new TypeReference<List<Kits>>(){});

            KitsRepository repository = new KitsRepository();
            KitsTranslator translator = new KitsTranslator();

            for (Kits kit : kits) {
                repository.add(translator.translate(kit, node));
            }

            return repository;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void upload(KitsRepository kitsRepository) {
        try {
            List<Kits> kits = new ArrayList<Kits>();
            KitsTranslator translator = new KitsTranslator();

            for (KitsValueObject feedVO : kitsRepository.findAll()) {
                kits.add(translator.translate(feedVO));
            }

            // update

            ObjectNode tree = (ObjectNode) getRootNode();
            tree.set("kits", mapper.valueToTree(kits));

            // write

            BufferedWriter writer = getFileWriter();
            mapper.writeValue(writer, tree);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
