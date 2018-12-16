package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.core.java.rabbitbag.vo.KitsValueObject;

public class Kits extends DomainObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("restrictions")
    private long restrictions;

    // Constructors

    public Kits() { super(); }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(long restrictions) {
        this.restrictions = restrictions;
    }

    // Convenience

    public Kits(String name, String description, long restrictions) {
        super();
        this.name = name;
        this.description = description;
        this.restrictions = restrictions;
    }

    public Kits(KitsValueObject vo) {
        super();
        this.name = vo.getName();
        this.description = vo.getDescription();
        this.restrictions = vo.getRestrictions();
    }
}
