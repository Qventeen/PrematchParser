package org.serhiituhaienko.web;

import com.fasterxml.jackson.core.type.TypeReference;
import org.serhiituhaienko.models.League;
import org.serhiituhaienko.models.Match;
import org.serhiituhaienko.models.Sport;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class HttpRequester {
    private static final String SPORT_URI = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";
    private static final String LEAGUE_URI_TEMPLATE = "https://leonbets.com/api-2/betline/events/all?ctag=en-US&league_id=%s&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup";
    private static final String MATCH_URI_TEMPLATE = "https://leonbets.com/api-2/betline/event/all?ctag=en-US&eventId=%s&flags=smg,reg,urlv2,mm2,rrc,nodup";
    private final HttpClient client;
    private final JsonConverter converter;

    public HttpRequester(HttpClient client, JsonConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    public List<Sport> requestSports() {
        return getAsync(SPORT_URI, new TypeReference<List<Sport>>() {
        }).join();
    }

    public Map<String, Match> requestMatches(List<Sport.League> topLeagues) {
        var result = new ConcurrentHashMap<String, Match>();
        for (Sport.League league : topLeagues) {
            CompletableFuture.allOf(getAsync(String.format(LEAGUE_URI_TEMPLATE, league.id()), new TypeReference<League>() {})
                    .thenApply(League::matchId)
                    .thenComposeAsync(matchId -> getAsync(String.format(MATCH_URI_TEMPLATE, matchId), new TypeReference<Match>() {}))
                    .thenApply(match -> result.put(league.id(), match))).join();
        }

        return result;
    }

    private <T> CompletableFuture<T> getAsync(String uri, TypeReference<T> typeReference) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(uri)).build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> converter.fromJson(json, typeReference));
    }
}
