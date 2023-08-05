package org.serhiituhaienko.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.serhiituhaienko.models.League;

import java.io.IOException;

public class LeagueDeserializer extends StdDeserializer<League> {
    public LeagueDeserializer() {
        this(null);
    }

    public LeagueDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public League deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        for (var n : node.get("events")) {
            return new League(n.get("id").asText());
        }

        return new League("");
    }
}
