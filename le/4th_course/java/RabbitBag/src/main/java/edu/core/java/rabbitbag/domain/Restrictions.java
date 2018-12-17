package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// connect restrictions and feed type

@JsonIgnoreProperties(ignoreUnknown = true)
public class Restrictions extends DomainObject {

    @JsonProperty("feed_type")
    private long feedType;

    // Getters and Setters

    public long getFeedType() {
        return feedType;
    }

    public void setFeedType(long feedType) {
        this.feedType = feedType;
    }
}
