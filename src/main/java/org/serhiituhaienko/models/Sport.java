package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Sport(String name, List<Region> regions) {
    public Sport(
            @JsonProperty("name") String name,
            @JsonProperty("regions") List<Region> regions
    ) {
        this.name = name;
        this.regions = regions;
    }
}
