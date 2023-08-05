package org.serhiituhaienko;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.serhiituhaienko.util.Printer;
import org.serhiituhaienko.web.HttpRequester;
import org.serhiituhaienko.web.JsonConverter;

import java.net.http.HttpClient;

public class App {
    public static void main( String[] args ) {
        JsonConverter converter = new JsonConverter(new ObjectMapper());
        HttpRequester requester = new HttpRequester(HttpClient.newHttpClient(), converter);

        var sports = requester.requestSports();
        var topLeagues = sports.stream().flatMap(sport -> sport.topLeagues().stream()).toList();
        var matches = requester.requestMatches(topLeagues);
        Printer.print(sports, matches);
    }
}
