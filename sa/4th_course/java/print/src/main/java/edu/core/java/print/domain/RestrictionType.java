package edu.core.java.print.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.core.java.print.vo.FeedValueObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestrictionType extends DomainObject {

    @JsonProperty("name")
    private String name;

    // Constructors

    public RestrictionType() { }

    // Convenience

    public RestrictionType(FeedValueObject vo) {
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
