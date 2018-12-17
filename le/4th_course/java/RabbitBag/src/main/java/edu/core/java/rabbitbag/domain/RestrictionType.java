package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestrictionType extends DomainObject {

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
