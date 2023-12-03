package apiserviceartsy;



import artsymodel.ArtsyArtist;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ApiServiceArtsy {
   // private final OkHttpClient client = new OkHttpClient();
   private final OkHttpClient client = new OkHttpClient.Builder()
           .connectTimeout(5, TimeUnit.SECONDS) // Adjust the timeout values as needed
           .readTimeout(5, TimeUnit.SECONDS)
           .writeTimeout(5, TimeUnit.SECONDS)
           .build();

    private static final String ARTSY_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTU0ZTMzODBlMTc4ZjAwMGI4MWY2MzgiLCJzYWx0X2hhc2giOiJjNTU5MmQ2MDdhNTkzOWU3NjlkMWExOTA5ODU3NjYwMSIsInJvbGVzIjoidXNlciIsInBhcnRuZXJfaWRzIjpbXSwib3RwIjpmYWxzZSwiZXhwIjoxNzMxNjg0NDU3LCJpYXQiOjE3MDAwNjIwNTcsImF1ZCI6IjUzZmYxYmNjNzc2ZjcyNDBkOTAwMDAwMCIsImlzcyI6IkdyYXZpdHkiLCJqdGkiOiI2NTU0ZTM2OWQyZTI2MzAwMGM2YTA3NmIifQ.ME1rmoOHoAicfOZSggikwsSnf4Zzpezz2QnTr54lbFg";
    private Gson gson;

    public ApiServiceArtsy(Gson gson) {
        this.gson = gson;
    }


    public ArtsyArtist getArtsyArtist(String apiUrl) throws IOException {

        // Build the request
        Request request = new Request.Builder()
                .url(apiUrl)
                .header("X-Access-Token", ARTSY_ACCESS_TOKEN)
                .build();

        // Make a GET request to the Artsy API
        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), ArtsyArtist.class);
        }
    }

    public List<ArtsyArtist> getAllArtsyArtists(String apiUrl) throws IOException {
        // Build the request
        Request request = new Request.Builder()
                .url(apiUrl)
                .header("X-Access-Token", ARTSY_ACCESS_TOKEN)
                .build();

        // Make a GET request to the Artsy API
        try (Response response = client.newCall(request).execute()) {
            // DesSerialização do Json para um objecto
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(response.body().string()).getAsJsonObject();
            JsonElement embeddedElement = jsonObject.get("_embedded");
            JsonObject embeddedObject = embeddedElement.getAsJsonObject();
            JsonElement artistsElement = embeddedObject.get("artists");

            //Isto é para ajduar no parsing do localdate, diz nos em que elemento deu erro
            /*JsonArray artistsArray = artistsElement.getAsJsonArray();
            List<ArtsyArtist> artistsList = new ArrayList<>();
            for (int i = 0; i < artistsArray.size(); i++) {
                try {
                    ArtsyArtist artist = gson.fromJson(artistsArray.get(i), ArtsyArtist.class);
                    artistsList.add(artist);
                } catch (DateTimeParseException e) {
                    // Handle the exception (e.g., log it or throw a more meaningful exception)
                    throw new ServiceException("Error parsing LocalDate at index " + i + ": " + e);
                } catch (Exception e) {
                    throw new ServiceException("Error at index " + i + ": " + e);
                }
            }*/
            List<ArtsyArtist> artistsList = gson.fromJson(artistsElement, new TypeToken<List<ArtsyArtist>>(){}.getType());
            return removeArtistsWithNullBirthday(artistsList);
        }
    }

    private static List<ArtsyArtist> removeArtistsWithNullBirthday(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() != null)
                .collect(Collectors.toList());
    }
}
