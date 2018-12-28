package edu.core.java.print.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DomainObject {

    @JsonProperty("id")
    protected long id;

    public String toJSON() {
        try {
            return (new ObjectMapper()).writeValueAsString(this);
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
