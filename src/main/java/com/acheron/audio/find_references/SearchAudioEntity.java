package com.acheron.audio.find_references;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAudioEntity {
    public static void main(String[] args) {

        System.out.println(searchIdURL("night vision lxner",5));
    }

    public static String referenceId(String str){
        if(str!=null){
            String string = str.replace("https://www.youtube.com/watch?v=","");
            String[] strings =string.split("&");
            return strings[0];
        }
        return null;
    }
    public static List<Map<String,String>> searchIdURL(String str, int initialNumbers) {


        if(str!=null) {
            if (initialNumbers <= 5 && initialNumbers > 0) {
                String[] element = str.trim().split(" ");
                String formattedString = null;
                for (int i = 0; i < element.length; i++) {
                    if (i == 0) {
                        formattedString = element[i];
                    } else
                        formattedString = formattedString + "+" + element[i];
                }
                String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=%s&key=AIzaSyBX761j5-PjN_4inYyqrCUy8jaMS4m3OWM".formatted(formattedString);
                HttpClient client = HttpClient.newBuilder().build();
                HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
                HttpResponse response = null;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String body = (String) response.body();
                List<Map<String,String>> references = new ArrayList<>();
                for (int i = 0; i < initialNumbers; i++) {
                    JSONParser parser = new JSONParser();
                    JSONObject object = null;
                    try {
                        object = (JSONObject) parser.parse(body);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Map<String,String> map =new HashMap<>();
                    JSONArray arrayItems = (JSONArray) object.get("items");
                    JSONObject itemsIteration = (JSONObject) arrayItems.get(i);
                    JSONObject id = (JSONObject) itemsIteration.get("id");
                    JSONObject snippet = (JSONObject) itemsIteration.get("snippet");
                    map.put((String) id.get("videoId"),(String) snippet.get("title"));
                    references.add(map);
                }
                return references;
            }
            return null;
        }
        return null;
    }
    public static List<String> searchURL(String str,int initialNumbers) {
        if(str!=null) {
            String URL =null;
            if (initialNumbers <= 5 && initialNumbers > 0) {
                String[] element = str.trim().split(" ");
                String formattedString = null;
                for (int i = 0; i < element.length; i++) {
                    if (i == 0) {
                        formattedString = element[i];
                    } else
                        formattedString = formattedString + "+" + element[i];
                }
                String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=%s&key=AIzaSyBX761j5-PjN_4inYyqrCUy8jaMS4m3OWM".formatted(formattedString);
                HttpClient client = HttpClient.newBuilder().build();
                HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
                HttpResponse response = null;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String body = (String) response.body();
                List<String> references = new ArrayList<>();
                for (int i = 0; i < initialNumbers; i++) {
                    JSONParser parser = new JSONParser();
                    JSONObject object = null;
                    try {
                        object = (JSONObject) parser.parse(body);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    JSONArray arrayItems = (JSONArray) object.get("items");
                    JSONObject itemsIteration = (JSONObject) arrayItems.get(i);
                    JSONObject id = (JSONObject) itemsIteration.get("id");
                    references.add("https://www.youtube.com/watch?v="+((String) id.get("videoId")));
                }
                return references;
            }
            return null;
        }
        return null;
    }
}
