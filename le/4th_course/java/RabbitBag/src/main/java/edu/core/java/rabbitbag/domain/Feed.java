package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Feed extends DomainObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("expiration_term")
    private Date expirationTerm;

    @JsonProperty("type")
    private long type;

}
