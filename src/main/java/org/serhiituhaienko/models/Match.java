package org.serhiituhaienko.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.serhiituhaienko.deserializers.MatchDeserializer;

import java.time.Instant;
import java.util.List;

@JsonDeserialize(using = MatchDeserializer.class)
public record Match(String id, String name, Instant startTime, List<Market> markets) {
    public record Market(String name, List<Runner> runners) {}
    public record Runner(String id, String name, String price) {}
}
