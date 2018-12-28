package edu.core.java.print.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.core.java.print.vo.BrandValueObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Brand extends DomainObject {

    @JsonProperty("name")
    private String name;

    // Constructors

    public Brand() { }

    // Convenience

    public Brand(BrandValueObject vo) {
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
