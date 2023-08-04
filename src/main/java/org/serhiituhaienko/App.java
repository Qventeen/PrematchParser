package org.serhiituhaienko;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.serhiituhaienko.models.Sport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class App {
    public static void main( String[] args ) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //0-request
        //test getting http://leonbets.com


        //1-request
        //get main data about sports
        //https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2


        String result = getData(client, "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2").body();

        List<Sport> sportList = objectMapper.readValue(result, new TypeReference<>() {});
        System.out.println(sportList);

        //2-request
        //get data about concrete league
        //https://leonbets.com/api-2/betline/events/all?ctag=en-US&league_id=1970324836980105&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup
        //System.out.println(getData(client, "https://leonbets.com/api-2/betline/events/all?ctag=en-US&league_id=1970324836980105&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup").body());
        //3-request
        //for every event in league
        //https://leonbets.com/api-2/betline/event/all?ctag=en-US&eventId=1970324842107886&flags=smg,reg,urlv2,mm2,rrc,nodup
//        System.out.println(getData(client, "https://leonbets.com/api-2/betline/events/all?ctag=en-US&league_id=1970324836980105&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup").body());


    }

    private static HttpResponse<String> getData(HttpClient client, String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(uri))
                .header("accept", "application/json")
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
