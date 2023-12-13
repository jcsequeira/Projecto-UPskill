package apiservice;



import adapters.LocalDateAdapter;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;



public class ApiService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();


    private static Request buildRequest(String apiUrl) {
        return new Request.Builder()
                .url(apiUrl)
                .build();
    }


    public static <T> List<T> getAllItems(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;

            // Directly deserialize the array into List<T>
            Type listType = TypeToken.getParameterized(List.class, itemType).getType();
            return gson.fromJson(response.body().string(), listType);
        }
    }


    public static <T> T getItem(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
           return gson.fromJson(response.body().string(),itemType); }
    }

    private static void postToRestApi(String apiUrl, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(json, JSON_MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
            System.out.println("POST Request Response Code: " + responseCode);
        }
    }

    public static <T> void postToRestApi(String apiUrl, T object) throws IOException {
        String json = gson.toJson(object);
        postToRestApi(apiUrl, json);
    }

    private static void putToRestApi(String apiUrl, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(json, JSON_MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(apiUrl)
                .put(requestBody)  // Use put() instead of post() for a PUT request
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
            System.out.println("PUT Request Response Code: " + responseCode);
        }
    }

    public static <T> void putToRestApi(String apiUrl, T object) throws IOException {
        String json = gson.toJson(object);
        putToRestApi(apiUrl, json);
    }

    private static void deleteToRestApi(String apiUrl) throws IOException {
        Request request = new Request.Builder()
                .url(apiUrl)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
            System.out.println("DELETE Request Response Code: " + responseCode);
        }
    }




}