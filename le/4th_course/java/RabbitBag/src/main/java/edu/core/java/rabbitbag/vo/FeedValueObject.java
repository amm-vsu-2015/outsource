package edu.core.java.rabbitbag.vo;

import edu.core.java.rabbitbag.domain.Feed;

import java.util.Date;

public class FeedValueObject extends ValueObject {

    private String name;

    private BrandValueObject brand;

    private Date expirationTerm;

    private FeedTypeValueObject type;

    // Constructor

    public FeedValueObject(Feed entity) {
        this.name = entity.getName();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandValueObject getBrand() {
        return brand;
    }

    public void setBrand(BrandValueObject brand) {
        this.brand = brand;
    }

    public Date getExpirationTerm() {
        return expirationTerm;
    }

    public void setExpirationTerm(Date expirationTerm) {
        this.expirationTerm = expirationTerm;
    }

    public FeedTypeValueObject getType() {
        return type;
    }

    public void setType(FeedTypeValueObject type) {
        this.type = type;
    }

}
