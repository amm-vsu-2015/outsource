package edu.core.java.print.translator;

import com.fasterxml.jackson.databind.JsonNode;
import edu.core.java.print.domain.DomainObject;
import edu.core.java.print.vo.ValueObject;

import java.io.IOException;

public interface Translator<V extends ValueObject, D extends DomainObject> {

    // translate one to another

    V translate(D domain, JsonNode root) throws IOException;

    D translate(V domain);
}
