package edu.core.java.rabbitbag.translator;

import edu.core.java.rabbitbag.domain.DomainObject;
import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.vo.KitsValueObject;
import edu.core.java.rabbitbag.vo.ValueObject;

public class KitsTranslator implements Translator<KitsValueObject, Kits> {

    @Override
    public KitsValueObject translate(Kits domain) {
        return new KitsValueObject(domain);
    }

    @Override
    public Kits translate(KitsValueObject vo) {
        return new Kits(vo);
    }

}
