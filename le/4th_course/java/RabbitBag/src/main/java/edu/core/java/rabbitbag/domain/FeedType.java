package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedType extends DomainObject {

    @JsonProperty("name")
    private String name;

    // Constructors

    // Convenience

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
