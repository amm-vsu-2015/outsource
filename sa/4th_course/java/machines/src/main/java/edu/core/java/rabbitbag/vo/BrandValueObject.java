package edu.core.java.rabbitbag.vo;

import edu.core.java.rabbitbag.domain.Brand;

public class BrandValueObject extends ValueObject {

    private String name;

    // Constructors

    BrandValueObject(Brand entity) {
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
