package org.serhiituhaienko;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.serhiituhaienko.models.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class App {
    private static final String SPORT_URI = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";
    private static final String LEAGUE_URI_TEMPLATE = "https://leonbets.com/api-2/betline/events/all?ctag=en-US&league_id=%s&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup";
    private static final String MATCH_URI_TEMPLATE = "https://leonbets.com/api-2/betline/event/all?ctag=en-US&eventId=%s&flags=smg,reg,urlv2,mm2,rrc,nodup";

    public static void main( String[] args ) {
        HttpClient client = HttpClient.newHttpClient();

        List<Sport> sportList = fromJson(getAsync(client, SPORT_URI).join().body(), new TypeReference<>() {});
        List<Sport.League> topLeagues = sportList.stream()
                .flatMap(sport -> sport.regions().stream())
                .flatMap(region -> region.leagues().stream())
                .filter(Sport.League::top)
                .toList();

        Map<String, List<League.Match>> matches = new ConcurrentHashMap<>();

        for (Sport.League league : topLeagues) {
            CompletableFuture.allOf(getAsync(client, String.format(LEAGUE_URI_TEMPLATE, league.id()))
                    .thenApply(HttpResponse::body)
                    .thenApply(json -> fromJson(json, new TypeReference<League>() {}))
                    .thenApply(League::matches)
                    .thenApply(list -> matches.put(league.id(), list))).join();
        }

        Map<String, List<Match.Market>> markets = new ConcurrentHashMap<>();

        for (League.Match match : matches.values().stream().flatMap(List::stream).toList()) {
            CompletableFuture.allOf(getAsync(client, String.format(MATCH_URI_TEMPLATE, match.id()))
                    .thenApply(HttpResponse::body)
                    .thenApply(json -> fromJson(json, new TypeReference<Match>() {}))
                    .thenApply(Match::markets)
                    .thenApply(list -> markets.put(match.id(), list))).join();
        }
    }

    private static CompletableFuture<HttpResponse<String>> getAsync(HttpClient client, String uri) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(uri))
                .header("accept", "application/json")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    private static <T> T fromJson(String data, TypeReference<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T result;
        try {
            result = objectMapper.readValue(data, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
