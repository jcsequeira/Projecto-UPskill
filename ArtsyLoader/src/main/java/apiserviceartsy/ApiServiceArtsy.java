package apiserviceartsy;

import adapters.LocalDateAdapterArtsy;
import artsymodel.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A utility class for interacting with the Artsy API.
 */
public class ApiServiceArtsy {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

    private static final String ARTSY_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTU0ZTMzODBlMTc4ZjAwMGI4MWY2MzgiLCJzYWx0X2hhc2giOiJjNTU5MmQ2MDdhNTkzOWU3NjlkMWExOTA5ODU3NjYwMSIsInJvbGVzIjoidXNlciIsInBhcnRuZXJfaWRzIjpbXSwib3RwIjpmYWxzZSwiZXhwIjoxNzMxNjg0NDU3LCJpYXQiOjE3MDAwNjIwNTcsImF1ZCI6IjUzZmYxYmNjNzc2ZjcyNDBkOTAwMDAwMCIsImlzcyI6IkdyYXZpdHkiLCJqdGkiOiI2NTU0ZTM2OWQyZTI2MzAwMGM2YTA3NmIifQ.ME1rmoOHoAicfOZSggikwsSnf4Zzpezz2QnTr54lbFg";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapterArtsy()).create();
    private static final String HEADER_ACCESS_TOKEN = "X-Access-Token";

    /**
     * Builds an HTTP request with the Artsy API URL and authentication headers.
     *
     * @param apiUrl The Artsy API URL.
     * @return The configured HTTP request.
     */
    private static Request buildRequest(String apiUrl) {
        return new Request.Builder()
                .url(apiUrl)
                .header(HEADER_ACCESS_TOKEN, ARTSY_ACCESS_TOKEN)
                .build();
    }

    /**
     * Retrieves a list of Artsy items (e.g., artists, genes, artworks) from the Artsy API.
     *
     * @param <T>      The type of Artsy item.
     * @param apiUrl   The Artsy API URL.
     * @param itemType The class type of the Artsy item.
     * @return A list of Artsy items.
     * @throws IOException If an error occurs during the HTTP request.
     */
    public static <T> List<T> getAllArtsyItems(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            JsonElement embeddedElement = JsonParser.parseString(response.body().string()).getAsJsonObject().getAsJsonObject("_embedded");

            // Extract the array based on the type
            JsonArray itemsArray = null;
            if (itemType == ArtsyArtist.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("artists");
            } else if (itemType == ArtsyGene.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("genes");
            } else if (itemType == ArtsyArtwork.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("artworks");
            } else if (itemType == ArtsyPartner.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("partners");
            } else if (itemType == ArtsyShow.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("shows");
            }

            // Deserialize the array
            Type listType = TypeToken.getParameterized(List.class, itemType).getType();
            return gson.fromJson(itemsArray, listType);
        }
    }

    /**
     * Retrieves a single Artsy item (e.g., artist, gene, artwork) from the Artsy API.
     *
     * @param <T>      The type of Artsy item.
     * @param apiUrl   The Artsy API URL.
     * @param itemType The class type of the Artsy item.
     * @return The requested Artsy item.
     * @throws IOException If an error occurs during the HTTP request.
     */
    public static <T> T getArtsyItem(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            JsonElement embeddedElement = JsonParser.parseString(response.body().string()).getAsJsonObject().getAsJsonObject("_embedded");

            // Extract the array based on the type
            JsonArray itemsArray = null;
            if (itemType == ArtsyArtist.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("artists");
            } else if (itemType == ArtsyGene.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("genes");
            } else if (itemType == ArtsyArtwork.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("artworks");
            } else if (itemType == ArtsyPartner.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("partners");
            } else if (itemType == ArtsyShow.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("shows");
            }

            // Check if the array is not null and not empty
            if (itemsArray != null && !itemsArray.isEmpty()) {
                // Deserialize the array
                return gson.fromJson(itemsArray.get(0), itemType);
            } else {
                // Handle the case when the array is empty
                throw new RuntimeException("No items found in the response for type: " + itemType.getSimpleName());
            }
        }
    }

    /**
     * Retrieves an Artsy partner by reference from the Artsy API.
     *
     * @param apiUrl The Artsy API URL.
     * @return The requested Artsy partner.
     * @throws IOException If an error occurs during the HTTP request.
     */
    public static ArtsyPartner getArtsyPartnerByRef(String apiUrl) throws IOException {
        Request request = buildRequest(apiUrl);

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Parse JSON response using Gson
                assert response.body() != null;
                return gson.fromJson(response.body().charStream(), ArtsyPartner.class);
            } else {
                throw new RuntimeException("Error retrieving ArtsyPartner. HTTP Code: " + response.code());
            }
        }
    }
}
