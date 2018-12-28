package edu.core.java.print.translator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.print.domain.Brand;
import edu.core.java.print.domain.Feed;
import edu.core.java.print.domain.FeedType;
import edu.core.java.print.vo.FeedValueObject;

import java.io.IOException;
import java.util.List;

public class FeedTranslator implements Translator<FeedValueObject, Feed> {

    @Override
    public FeedValueObject translate(Feed domain, JsonNode root) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Brand> brands = mapper.readValue(root.get("brand").toString(), new TypeReference<List<Brand>>(){});
        List<FeedType> types = mapper.readValue(root.get("feed_type").toString(), new TypeReference<List<FeedType>>(){});

        Brand brand = null;
        FeedType type = null;

        for (Brand b : brands) {
            if (b.getId() == domain.getBrand()) {
                brand = b;
                break;
            }
        }

        for (FeedType t : types) {
            if (t.getId() == domain.getType()) {
                type = t;
                break;
            }
        }

        return new FeedValueObject(domain, brand, type);
    }

    @Override
    public Feed translate(FeedValueObject vo) {
        return new Feed(vo);
    }

}