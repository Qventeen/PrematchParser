package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class League {
    private final String id;
    private final String name;
    private final boolean top;
    @JsonIgnore
    private List<Match> matches;

    public League(
            @JsonProperty String id,
            @JsonProperty String name,
            @JsonProperty boolean top
    ) {
        this.id = id;
        this.name = name;
        this.top = top;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isTop() {
        return top;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
