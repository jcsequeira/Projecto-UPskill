package apiservice;

import adapters.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

/**
 * The ApiService class provides methods for interacting with a RESTful API,
 * including sending HTTP requests, handling responses, and performing CRUD operations.
 */
public class ApiService {

    /**
     * The OkHttpClient instance used for making HTTP requests.
     */
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * The media type for JSON content.
     */
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");

    /**
     * The Gson instance with a custom TypeAdapter for handling LocalDate serialization and deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    /**
     * Builds an HTTP request with the specified API URL.
     *
     * @param apiUrl The URL of the API endpoint.
     * @return A configured Request object.
     */
    private static Request buildRequest(String apiUrl) {
        return new Request.Builder()
                .url(apiUrl)
                .build();
    }

    /**
     * Retrieves a list of items from the specified API endpoint.
     *
     * @param <T>      The type of items in the list.
     * @param apiUrl   The URL of the API endpoint.
     * @param itemType The class representing the type of items in the list.
     * @return A List containing items of the specified type.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
     */
    public static <T> List<T> getAllItems(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            // Directly deserialize the array into List<T>
            Type listType = TypeToken.getParameterized(List.class, itemType).getType();
            return gson.fromJson(response.body().string(), listType);
        }
    }

    /**
     * Retrieves a single item from the specified API endpoint.
     *
     * @param <T>      The type of the item.
     * @param apiUrl   The URL of the API endpoint.
     * @param itemType The class representing the type of the item.
     * @return An instance of the specified item type.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
     */
    public static <T> T getItem(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return gson.fromJson(response.body().string(), itemType);
        }
    }

    /**
     * Posts JSON data to the specified API endpoint.
     *
     * @param apiUrl The URL of the API endpoint.
     * @param json   The JSON data to be sent in the request body.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
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
        }
    }

    /**
     * Posts an object to the specified API endpoint, converting it to JSON.
     *
     * @param <T>    The type of the object to be posted.
     * @param apiUrl The URL of the API endpoint.
     * @param object The object to be posted.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
     */
    public static <T> void postToRestApi(String apiUrl, T object) throws IOException {
        String json = gson.toJson(object);
        postToRestApi(apiUrl, json);
    }

    /**
     * Updates data on the specified API endpoint using a PUT request.
     *
     * @param apiUrl The URL of the API endpoint.
     * @param json   The JSON data to be sent in the request body.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
     */
    private static void putToRestApi(String apiUrl, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(json, JSON_MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(apiUrl)
                .put(requestBody)  // Use put() instead of post() for a PUT request
                .build();
        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
        }
    }

    /**
     * Updates an object on the specified API endpoint using a PUT request, converting it to JSON.
     *
     * @param <T>    The type of the object to be updated.
     * @param apiUrl The URL of the API endpoint.
     * @param object The object to be updated.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
     */
    public static <T> void putToRestApi(String apiUrl, T object) throws IOException {
        String json = gson.toJson(object);
        putToRestApi(apiUrl, json);
    }

    /**
     * Sends a DELETE request to the specified API endpoint.
     *
     * @param apiUrl The URL of the API endpoint.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
     */
    public static void deleteToRestApi(String apiUrl) throws IOException {
        Request request = new Request.Builder()
                .url(apiUrl)
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
        }
    }

    /**
     * Triggers a predefined DELETE request for deleting Artsy items from the specified API endpoint.
     *
     * @param apiUrl The URL of the API endpoint.
     * @throws IOException If an I/O error occurs while making the HTTP request or processing the response.
     */
    public static void triggerDeleteAllArtsyInRestApi(String apiUrl) throws IOException {
        String json = "{\"script_name\": \"delete Artsy\"}";
        postToRestApi(apiUrl, json);
    }
}

