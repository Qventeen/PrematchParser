package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Market {
    private final String name;
    private final List<Runner> runners;

    public Market(
            @JsonProperty String name,
            @JsonProperty List<Runner> runners
    ) {
        this.name = name;
        this.runners = runners;
    }

    public String getName() {
        return name;
    }

    public List<Runner> getRunners() {
        return runners;
    }
}
