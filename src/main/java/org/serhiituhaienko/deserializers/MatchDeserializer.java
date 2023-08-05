package org.serhiituhaienko.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.serhiituhaienko.models.Match;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class MatchDeserializer extends StdDeserializer<Match> {

    public MatchDeserializer() {
        this(null);
    }

    public MatchDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Match deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        var startDateTime = Instant.ofEpochMilli(node.get("kickoff").asLong());
        var markets = new ArrayList<Match.Market>();
        for (var n: node.get("markets")) {
            markets.add(p.getCodec().treeToValue(n, Match.Market.class));
        }

        return new Match(
                node.get("id").asText(),
                node.get("name").asText(),
                startDateTime,
                markets
        );
    }
}
