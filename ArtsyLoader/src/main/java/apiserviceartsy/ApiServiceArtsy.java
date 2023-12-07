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

    private static final String ARTSY_ACCESS_TOKEN = "YOUR_ACCESS_TOKEN";
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
            }else if (itemType == ArtsyPartner.class) {
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("partners");
            } else if (itemType == ArtsyShow.class){
                itemsArray = embeddedElement.getAsJsonObject().getAsJsonArray("shows");
            }

            // Deserialize the array
            Type listType = TypeToken.getParameterized(List.class, itemType).getType();
            return gson.fromJson(itemsArray, listType);
        }
    }

    private static List<ArtsyArtist> removeArtistsWithNullBirthdayAndEmptyNationality(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() != null && !Objects.equals(artist.getNationality(), ""))
                .collect(Collectors.toList());
    }
}
