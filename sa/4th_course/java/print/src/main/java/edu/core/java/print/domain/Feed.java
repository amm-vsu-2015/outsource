package edu.core.java.print.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.core.java.print.vo.FeedValueObject;

import java.io.IOException;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feed extends DomainObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private long brand;

    @JsonProperty("expiration_term")
    private Date expirationTerm;

    @JsonProperty("type")
    private long type;

    // Constructors

    public Feed() { }

    // Convenience

    public Feed(long id, String name, long brand, Date term, long type) {
        super();
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.expirationTerm = term;
        this.type = type;
    }

    public Feed(FeedValueObject vo) {
        super();
        this.id = vo.getId();
        this.name = vo.getName();
        this.brand = vo.getBrand().getId();
        this.expirationTerm = vo.getExpirationTerm();
        this.type = vo.getType().getId();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBrand() {
        return brand;
    }

    public void setBrand(long brand) {
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
