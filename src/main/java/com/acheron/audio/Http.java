package com.acheron.audio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
    public static void main(String[] args) throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().build();

        var response = client.send(HttpRequest.newBuilder(URI.create("https://www.youtube.com/watch?v=mirodngindbasdsadasdsada")).GET().build(), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }
}
