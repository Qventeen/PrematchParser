package org.serhiituhaienko.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.serhiituhaienko.deserializers.LeagueDeserializer;

@JsonDeserialize(using = LeagueDeserializer.class)
public record League(String matchId) {}
