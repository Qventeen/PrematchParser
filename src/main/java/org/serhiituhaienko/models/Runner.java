package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Runner(String name, String price) {
    public Runner(
            @JsonProperty("name") String name,
            @JsonProperty("price") String price
    ) {
        this.name = name;
        this.price = price;
    }
}
