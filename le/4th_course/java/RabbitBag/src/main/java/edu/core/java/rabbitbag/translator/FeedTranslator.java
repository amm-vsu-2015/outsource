package edu.core.java.rabbitbag.translator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.rabbitbag.domain.Brand;
import edu.core.java.rabbitbag.domain.Feed;
import edu.core.java.rabbitbag.domain.FeedType;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.vo.FeedValueObject;
import edu.core.java.rabbitbag.vo.KitsValueObject;

import java.io.IOException;
import java.util.List;

public class FeedTranslator implements Translator<FeedValueObject, Feed> {

    @Override
    public FeedValueObject translate(Feed domain, JsonNode root) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Brand> brands = mapper.readValue(root.get("brand").toString(), new TypeReference<List<Brand>>(){});
        List<FeedType> types = mapper.readValue(root.get("feed_type").toString(), new TypeReference<List<FeedType>>(){});

        Brand feedBrand = null;
        FeedType feedType = null;

        for (Brand brand : brands) {
            if (brand.getId() == domain.getBrand()) {
                feedBrand = brand;
                break;
            }
        }

        for (FeedType type : types) {
            if (type.getId() == domain.getType()) {
                feedType = type;
                break;
            }
        }

        return new FeedValueObject(domain, feedBrand, feedType);
    }

    @Override
    public Feed translate(FeedValueObject vo) {
        return new Feed(vo);
    }

}