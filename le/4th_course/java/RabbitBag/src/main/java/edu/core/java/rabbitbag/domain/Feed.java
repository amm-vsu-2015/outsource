package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.core.java.rabbitbag.vo.FeedValueObject;

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
