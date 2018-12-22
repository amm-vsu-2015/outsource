package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.core.java.rabbitbag.vo.KitsValueObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kits extends DomainObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("restriction")
    private long restriction;

    // Constructors

    public Kits() { super(); }

    // Convenience

    public Kits(KitsValueObject vo) {
        super();
        this.id = vo.getId();
        this.name = vo.getName();
        this.description = vo.getDescription();
        this.restriction = vo.getRestriction().getId();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRestriction() {
        return restriction;
    }

    public void setRestrictions(long restriction) {
        this.restriction = restriction;
    }

}
