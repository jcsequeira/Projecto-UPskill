import adapters.LocalDateAdapter;
import apiserviceartsy.ApiServiceArtsy;
import artsymodel.ArtsyArtist;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataprocessorservice.ArtistConverter;
import dataprocessorservice.DataProcessor;
import exceptions.ServiceException;
import model.Artista;
import restapiservice.RestApiService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class LoaderMain {

    //esta class apenas executa o controller ou talvez os seus metodos (que sera o mestre das operaçoes)
    //sera tbem para por aqui as constantes todas, urls/toekns etc, objectos que so precisam criar uma vez
    //como o client e gson etc

    //por agora é para testes
    private static Gson gsonLocalDate = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    public static void main(String[] args) throws IOException {
        // Artsy API endpoint URL
        String artistByIdApiUrl = "https://api.artsy.net/api/artists/505cbd2af5d4ca0002000898";
        String allArtistApiUrl = "https://api.artsy.net/api/artists?artworks=true&sort=-trending&size=1500&page=1";


        System.out.println("Artsy GET req TEST:");
        ApiServiceArtsy apiServiceArtsy = new ApiServiceArtsy(gsonLocalDate);
        //ArtsyArtist artsyArtist = apiServiceArtsy.getArtsyArtist(artistByIdApiUrl);
        List<ArtsyArtist> artsyArtistList = apiServiceArtsy.getAllArtsyArtists(allArtistApiUrl);
        //System.out.println(artsyArtistList);
        System.out.println("Number of birthday nulls: " + countArtistsWithNullBirthday(artsyArtistList) + " in " + artsyArtistList.size());


        System.out.println("\nConversion Test:");
        List<Artista> artistaList = DataProcessor.listProcessor(artsyArtistList, ArtistConverter.class);
        System.out.println(artistaList.get(artistaList.size() - 1));


        System.out.println("\nRestAPI POST TEST");
        RestApiService restApiService = new RestApiService(gsonLocalDate);
        // Create Artista objects and add them to the list
        restApiService.postAllArtistasToRestApi(artistaList);

    }


    private static long countArtistsWithNullBirthday(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() == null)
                .count();
    }


}
