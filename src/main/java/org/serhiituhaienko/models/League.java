package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record League(List<Match> matches) {

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
    public League(@JsonProperty("events") List<Match> matches) {
        this.matches = matches;
    }
}
