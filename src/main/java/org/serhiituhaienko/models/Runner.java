package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Runner {
    private final String name;
    private final String price;

    public Runner(
            @JsonProperty String name,
            @JsonProperty String price
    ) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
