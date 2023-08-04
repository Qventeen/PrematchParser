package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Sport {
    private final String name;
    private final List<Region> regions;

    public Sport(
            @JsonProperty String name,
            @JsonProperty List<Region> regions
    ) {
        this.name = name;
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public List<Region> getRegions() {
        return regions;
    }
}
