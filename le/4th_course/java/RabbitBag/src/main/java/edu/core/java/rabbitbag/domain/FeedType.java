package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.core.java.rabbitbag.vo.FeedValueObject;

public class FeedType extends DomainObject {

    @JsonProperty("name")
    private String name;

    // Constructors

    public FeedType() { }

    // Convenience

    public FeedType(FeedValueObject vo) {
        super();
        this.id = vo.getId();
        this.name = vo.getName();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
