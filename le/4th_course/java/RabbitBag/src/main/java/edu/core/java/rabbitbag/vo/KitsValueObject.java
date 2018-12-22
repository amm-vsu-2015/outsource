package edu.core.java.rabbitbag.vo;

import edu.core.java.rabbitbag.domain.Kits;
import edu.core.java.rabbitbag.domain.RestrictionType;

public class KitsValueObject extends ValueObject {

    private String name;

    private String description;

    private RestrictionType restriction;

    // Constructors

    public KitsValueObject(Kits entity, RestrictionType restrictionType) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.restriction = restrictionType;
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

    public RestrictionType getRestriction() { return restriction; }

    public void setRestrictions(RestrictionType restriction) { this.restriction = restriction; }

}
