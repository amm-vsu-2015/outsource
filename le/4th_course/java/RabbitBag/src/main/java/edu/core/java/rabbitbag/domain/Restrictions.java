package edu.core.java.rabbitbag.domain;

// connect restrictions and feed type

import com.fasterxml.jackson.annotation.JsonProperty;

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
