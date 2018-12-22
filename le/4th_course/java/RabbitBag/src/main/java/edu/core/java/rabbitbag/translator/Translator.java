package edu.core.java.rabbitbag.translator;

import com.fasterxml.jackson.databind.JsonNode;
import edu.core.java.rabbitbag.domain.DomainObject;
import edu.core.java.rabbitbag.vo.ValueObject;

import java.io.IOException;

public interface Translator<V extends ValueObject, D extends DomainObject> {

    // translate one to another

    V translate(D domain, JsonNode root) throws IOException;

    D translate(V domain);
}
