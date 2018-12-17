package edu.core.java.rabbitbag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonFileObject extends DomainObject {

    @JsonProperty("feed_type")
    public List<FeedType> feedType;

    @JsonProperty("restriction_type")
    public List<RestrictionType> restrictionType;

    @JsonProperty("feed")
    public List<Feed> feed;

    @JsonProperty("brand")
    public List<Brand> brand;

    @JsonProperty("kits")
    public List<Kits> kits;

}
