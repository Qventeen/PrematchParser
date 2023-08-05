package org.serhiituhaienko.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.serhiituhaienko.models.Sport;

import java.io.IOException;
import java.util.ArrayList;

public class SportDeserializer extends StdDeserializer<Sport> {

    public SportDeserializer() {
        this(null);
    }

    public SportDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Sport deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        var topLeagues = new ArrayList<Sport.League>();
        for (var region: node.get("regions")) {
            for (var league: region.get("leagues")) {
                if(league.get("top").booleanValue()) {
                    topLeagues.add(new Sport.League(
                            league.get("id").asText(),
                            league.get("name").asText(),
                            region.get("name").asText()));
                }
            }
        }

        return new Sport(node.get("name").asText(), topLeagues);
    }
}
