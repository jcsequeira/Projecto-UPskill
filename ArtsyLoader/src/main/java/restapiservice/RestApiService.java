package restapiservice;

import artsymodel.ArtsyArtist;
import com.google.gson.Gson;
import model.Artista;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class RestApiService {
    private final OkHttpClient client = new OkHttpClient();

    private Gson gson;

    public RestApiService(Gson gson) {
        this.gson = gson;
    }



    public void postArtistaToRestApi(Artista artista) throws IOException {
        // Custom API endpoint URL
        String apiUrl = "http://localhost:4567/api/artista";

        // Build the request body
        RequestBody requestBody = RequestBody.create(gson.toJson(artista,Artista.class), MediaType.parse("application/json"));

        // Build the request
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        // Make a POST request to the custom API
        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
            System.out.println("POST Request Response Code: " + responseCode);
            System.out.println("Response Body: ");
            System.out.println(response.body().string());
        }
    }
    public void postAllArtistasToRestApi(List<Artista> artistasList) throws IOException {
        //TBD vai enviar json com lista de artistas para endpoint especifico (por fazer na REST!)
        String apiUrl = "http://localhost:4567/populate/artista";

        // Build the request body
        RequestBody requestBody = RequestBody.create(gson.toJson(artistasList), MediaType.parse("application/json"));

        // Build the request
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        // Make a POST request to the custom API
        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
            System.out.println("POST Request Response Code: " + responseCode);
        }
    }
}

