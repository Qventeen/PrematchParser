package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Market(String name, List<Runner> runners) {
    public Market(
            @JsonProperty("name") String name,
            @JsonProperty("runners") List<Runner> runners
    ) {
        this.name = name;
        this.runners = runners;
    }
}
