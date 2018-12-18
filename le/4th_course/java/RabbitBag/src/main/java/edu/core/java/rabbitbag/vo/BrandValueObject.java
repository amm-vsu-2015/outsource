package edu.core.java.rabbitbag.vo;

import edu.core.java.rabbitbag.domain.Brand;

public class BrandValueObject extends ValueObject {

    private String name;

    // Constructors

    public BrandValueObject(Brand entity) {
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
