package edu.core.java.rabbitbag.translator;

import edu.core.java.rabbitbag.domain.DomainObject;
import edu.core.java.rabbitbag.vo.ValueObject;

public interface Translator<V extends ValueObject, D extends DomainObject> {

    // translate one to another

    V translate(D domain);

    D translate(V domain);
}
