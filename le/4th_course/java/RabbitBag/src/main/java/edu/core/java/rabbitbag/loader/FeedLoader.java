package edu.core.java.rabbitbag.loader;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.core.java.rabbitbag.domain.Brand;
import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.FeedType;
import edu.core.java.rabbitbag.domain.JsonFileObject;
import edu.core.java.rabbitbag.repository.FeedRepository;
import edu.core.java.rabbitbag.vo.FeedValueObject;

import java.io.IOException;
import java.util.List;

public class FeedLoader extends Loader<JsonFileObject> {

    private ObjectMapper mapper = new ObjectMapper();

    public FeedRepository getRepository() {

        try {
            JsonParser parser = getParserFromJsonDB();

            // collect object

            JsonNode node = mapper.readTree(parser);
            List<Feed> feeds = mapper.readValue(node.get("feed").toString(), new TypeReference<List<Feed>>(){});
            List<Brand> brands = mapper.readValue(node.get("brand").toString(), new TypeReference<List<Brand>>(){});
            List<FeedType> types = mapper.readValue(node.get("feed_type").toString(), new TypeReference<List<FeedType>>(){});

            FeedRepository repository = new FeedRepository();

            for (Feed feed : feeds) {

                Brand feedBrand = null;
                FeedType feedType = null;

                for (Brand brand : brands) {
                    if (brand.getId() == feed.getBrand()) {
                        feedBrand = brand;
                        break;
                    }
                }

                for (FeedType type : types) {
                    if (type.getId() == feed.getType()) {
                        feedType = type;
                        break;
                    }
                }

                repository.add(new FeedValueObject(feed, feedBrand, feedType));
            }

            return repository;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void upload(FeedRepository feedRepository) {
        // todo 1: load all available for this repo entities from json
        // todo 2: load in same repos
        // todo 3: parse repos and replace some items if they was changed (by id)
        // todo 4: convert to json and save

        try {
            JsonParser parser = getParserFromJsonDB();

            JsonNode node = mapper.readTree(parser);
            JsonNode brandTree = node.get("brand");

            for (JsonNode brandNode : brandTree) {
                Long id = mapper.readValue(brandNode.get("id").toString(), Long.class);
                if (id == 2) {
                    ObjectNode on = ((ObjectNode) brandNode).put("name", "lololl");
                    break;
                }
            }


            for (FeedValueObject feedVO : feedRepository.findAll()) {

            }

            // todo save to file
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
