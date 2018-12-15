package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kits extends DomainObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("restrictions")
    private long restrictions;


    public String getName() {
        return name;
    }
}
