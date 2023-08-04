package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Region(String name, List<League> leagues) {
    public Region(
            @JsonProperty("name") String name,
            @JsonProperty("leagues") List<League> leagues
    ) {
        this.name = name;
        this.leagues = leagues;
    }
}
