package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Match(String id, String name, String startTime) {
    public Match(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("kickoff") String startTime
    ) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
    }
}
