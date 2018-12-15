package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedType extends DomainObject {

    @JsonProperty("name")
    private String name;

}
