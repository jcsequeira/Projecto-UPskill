package restapiservice;

import adapters.LocalDateAdapterArtsy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class RestApiService {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapterArtsy()).create();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");

    public static <T> void postToRestApi(String apiUrl, List<T> dataList) throws IOException {
        String json = gson.toJson(dataList);
        postToRestApi(apiUrl, json);
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



}
