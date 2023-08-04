package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Region {
    private final String name;

    private final List<League> leagues;

    public Region(
            @JsonProperty String name,
            @JsonProperty List<League> leagues
    ) {
        this.name = name;
        this.leagues = leagues;
    }

    public String getName() {
        return name;
    }

    public List<League> getLeagues() {
        return leagues;
    }
}
