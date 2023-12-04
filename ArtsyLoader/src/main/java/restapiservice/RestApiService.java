package restapiservice;

import adapters.LocalDateAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Artista;
import model.Cidade;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class RestApiService {
    private static final OkHttpClient client = new OkHttpClient();
    private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();



    public RestApiService(Gson gson) {
        RestApiService.gson = gson;
    }




    public void postArtistaToRestApi(Artista artista) throws IOException {
        // Custom API endpoint URL
        String apiUrl = "http://localhost:4567/api/artista";
        RequestBody requestBody = RequestBody.create(gson.toJson(artista,Artista.class), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Get the response code (optional)
            int responseCode = response.code();
            System.out.println("POST Request Response Code: " + responseCode);
            System.out.println("Response Body: ");
            System.out.println(response.body().string());
        }
    }
    public static void postAllArtistasToRestApi(List<Artista> artistasList) throws IOException {
        String apiUrl = "http://localhost:4567/populate/artista";
        RequestBody requestBody = RequestBody.create(gson.toJson(artistasList), MediaType.parse("application/json"));
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

    public static void postAllCidadesToRestApi(List<Cidade> cidadesList) throws IOException {
        String apiUrl = "http://localhost:4567/populate/cidades";
        RequestBody requestBody = RequestBody.create(gson.toJson(cidadesList), MediaType.parse("application/json"));
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
}

