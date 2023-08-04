package org.serhiituhaienko.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record League(String id, String name, boolean top) {
    public League(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("top") boolean top
    ) {
        this.id = id;
        this.name = name;
        this.top = top;
    }
}
