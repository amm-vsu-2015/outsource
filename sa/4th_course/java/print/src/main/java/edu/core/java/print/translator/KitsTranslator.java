package edu.core.java.print.translator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.print.domain.Brand;
import edu.core.java.print.domain.FeedType;
import edu.core.java.print.domain.Kits;
import edu.core.java.print.domain.RestrictionType;
import edu.core.java.print.vo.KitsValueObject;

import java.io.IOException;
import java.util.List;

public class KitsTranslator implements Translator<KitsValueObject, Kits> {

    @Override
    public KitsValueObject translate(Kits domain, JsonNode root) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<RestrictionType> restrictions = mapper.readValue(root.get("restriction_type").toString(), new TypeReference<List<RestrictionType>>(){});

        RestrictionType restriction = null;
        for (RestrictionType restrict : restrictions) {
            if (restrict.getId() == domain.getRestriction()) {
                restriction = restrict;
                break;
            }
        }

        return new KitsValueObject(domain, restriction);
    }

    @Override
    public Kits translate(KitsValueObject vo) {
        return new Kits(vo);
    }

}
