package edu.core.java.rabbitbag.translator;

import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.vo.KitsValueObject;

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
