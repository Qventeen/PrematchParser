package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//event
public class Match {
    private final String id;

    private final String name;

    //kickoff
    private final String startTime;

    @JsonIgnore
    private List<Market> markets;

    public Match(
            @JsonProperty String id,
            @JsonProperty String name,
            @JsonProperty("kickoff") String startTime
    ) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public List<Market> getMarkets() {
        return markets;
    }
}
