package edu.core.java.rabbitbag.vo;

import edu.core.java.rabbitbag.domain.RestrictionType;

public class RestrictionTypeValueObject extends ValueObject {

    private String name;

    // Constructors

    public RestrictionTypeValueObject(RestrictionType entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
