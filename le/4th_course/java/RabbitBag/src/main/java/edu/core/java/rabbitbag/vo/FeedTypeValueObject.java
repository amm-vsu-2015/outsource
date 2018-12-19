package edu.core.java.rabbitbag.vo;

import edu.core.java.rabbitbag.domain.FeedType;

public class FeedTypeValueObject extends ValueObject {

    private String name;

    // Constructors

    public FeedTypeValueObject(FeedType entity) {
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
