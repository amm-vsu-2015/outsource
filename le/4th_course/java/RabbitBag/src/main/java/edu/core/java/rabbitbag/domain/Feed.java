package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feed extends DomainObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("expiration_term")
    private Date expirationTerm;

    @JsonProperty("type")
    private long type;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getExpirationTerm() {
        return expirationTerm;
    }

    public void setExpirationTerm(Date expirationTerm) {
        this.expirationTerm = expirationTerm;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

}
