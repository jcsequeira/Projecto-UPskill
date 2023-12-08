package apiserviceartsy;

import adapters.LocalDateAdapter;
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
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ApiServiceArtsy {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

    private static final String ARTSY_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTU0ZTMzODBlMTc4ZjAwMGI4MWY2MzgiLCJzYWx0X2hhc2giOiJjNTU5MmQ2MDdhNTkzOWU3NjlkMWExOTA5ODU3NjYwMSIsInJvbGVzIjoidXNlciIsInBhcnRuZXJfaWRzIjpbXSwib3RwIjpmYWxzZSwiZXhwIjoxNzMxNjg0NDU3LCJpYXQiOjE3MDAwNjIwNTcsImF1ZCI6IjUzZmYxYmNjNzc2ZjcyNDBkOTAwMDAwMCIsImlzcyI6IkdyYXZpdHkiLCJqdGkiOiI2NTU0ZTM2OWQyZTI2MzAwMGM2YTA3NmIifQ.ME1rmoOHoAicfOZSggikwsSnf4Zzpezz2QnTr54lbFg";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    private static final String HEADER_ACCESS_TOKEN = "X-Access-Token";

    private static Request buildRequest(String apiUrl) {
        return new Request.Builder()
                .url(apiUrl)
                .header(HEADER_ACCESS_TOKEN, ARTSY_ACCESS_TOKEN)
                .build();
    }


    public static <T> List<T> getAllArtsyItems(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
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

    public static <T> T getArtsyItem(String apiUrl, Class<T> itemType) throws IOException {
        Request request = buildRequest(apiUrl);
        try (Response response = client.newCall(request).execute()) {
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
}