package restapiservice;

import adapters.LocalDateAdapterArtsy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * The {@code RestApiService} class provides methods for making POST requests to a REST API,
 * using Generics.
 */
public class RestApiService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapterArtsy()).create();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");

    /**
     * Makes a POST request to a REST API with a list of data.
     *
     * @param <T>    The type of data in the list.
     * @param apiUrl The URL of the REST API.
     * @param dataList The list of data to be posted.
     * @throws IOException If an I/O error occurs.
     */
    public static <T> void postToRestApi(String apiUrl, List<T> dataList) throws IOException {
        String json = gson.toJson(dataList);
        postToRestApi(apiUrl, json);
    }

    /**
     * Makes a POST request to a REST API with JSON data.
     *
     * @param apiUrl The URL of the REST API.
     * @param json   The JSON data to be posted.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Makes a POST request to a REST API with a single object.
     *
     * @param <T>    The type of the object.
     * @param apiUrl The URL of the REST API.
     * @param object The object to be posted.
     * @throws IOException If an I/O error occurs.
     */
    public static <T> void postToRestApi(String apiUrl, T object) throws IOException {
        String json = gson.toJson(object);
        postToRestApi(apiUrl, json);
    }
}
