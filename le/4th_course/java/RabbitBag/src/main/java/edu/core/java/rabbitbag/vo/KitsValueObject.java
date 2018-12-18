package edu.core.java.rabbitbag.vo;

import edu.core.java.rabbitbag.domain.Kits;

public class KitsValueObject extends ValueObject {

    private String name;

    private String description;

    private long restrictions;

    // Constructors

    public KitsValueObject(Kits entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }

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

    public long getRestrictions() { return restrictions; }

    public void setRestrictions(long restrictions) { this.restrictions = restrictions; }

}
