package org.serhiituhaienko.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.serhiituhaienko.deserializers.SportDeserializer;

import java.util.List;

@JsonDeserialize(using = SportDeserializer.class)
public record Sport(String name, List<League> topLeagues) {
    public record League(String id, String name, String regionName) {}
}
