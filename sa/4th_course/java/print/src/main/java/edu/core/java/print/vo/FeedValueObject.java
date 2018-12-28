package edu.core.java.print.vo;

import edu.core.java.print.domain.Brand;
import edu.core.java.print.domain.Feed;
import edu.core.java.print.domain.FeedType;

import java.util.Date;

public class FeedValueObject extends ValueObject {

    private String name;

    private BrandValueObject brand;

    private Date expirationTerm;

    private FeedTypeValueObject type;

    // Constructor

    public FeedValueObject(Feed entity, Brand brand, FeedType type) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.brand = new BrandValueObject(brand);
        this.expirationTerm = entity.getExpirationTerm();
        this.type = new FeedTypeValueObject(type);
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
