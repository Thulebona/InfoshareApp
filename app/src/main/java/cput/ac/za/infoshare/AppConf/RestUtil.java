package cput.ac.za.infoshare.AppConf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user13 on 2015/06/25.
 */
public class RestUtil {
    public static final String URL = "http://10.47.2.247:9000";

    private static URLConnection openConnection(String fetchUrl){
        URLConnection urlConnection = null;
        try {
            urlConnection =  new URL(fetchUrl).openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            e.getMessage();
        }
        return urlConnection;
    }


    public static <T> T getById(String fetchUrl, String ID, Class<T> classType) {
        try
        {
            JsonParser jsonParser = new JsonParser();
            com.google.gson.stream.JsonReader reader = new JsonReader(new InputStreamReader(openConnection(fetchUrl + ID)
                            .getInputStream()));
            JsonElement element = jsonParser.parse(reader);
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            }).create();
            return (gson.fromJson(element,classType));
        }
        catch (Exception e)
        {
            e.getMessage();
            return null;
        }
    }


    public static <T> T save(String url,T classTypeObject, Class<T> classType){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> body = new HttpEntity<T>(classTypeObject,headers);
        JsonElement element = new JsonParser()
                .parse(restTemplate.postForObject(url, body, String.class))
                .getAsJsonObject();
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();

        return gson.fromJson(element,classType);
    }

    public static <T> T update(String url, T classType){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> body = new HttpEntity<T>( classType,headers);
        restTemplate.put(url, body);
        return classType;

    }

    public static <T> Set<T> getAll(String fetchUrl, Class<T> classType) {
        Set<T> list = new HashSet<>();
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(openConnection(fetchUrl).getInputStream()));
            JsonParser jsonParser = new JsonParser();
            JsonArray json = jsonParser.parse(reader).getAsJsonArray();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            }).create();

            for (JsonElement element : json) {
                list.add(gson.fromJson(element, classType));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }


}
