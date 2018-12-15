package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestrictionType extends DomainObject {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

}
