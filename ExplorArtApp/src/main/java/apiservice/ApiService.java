package apiservice;



import adapters.LocalDateAdapter;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;



public class ApiService {

    private static final OkHttpClient client = new OkHttpClient();


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

}