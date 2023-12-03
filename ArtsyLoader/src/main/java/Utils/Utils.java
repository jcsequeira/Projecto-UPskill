package Utils;

import artsymodel.ArtsyArtist;

import java.util.List;

public class Utils {

    //class com metodos para ler ficheiro ~CSVs com pais e nacionalidades, gera uma lista de objectos Pais
    //same com cidades
    //talvez materiais/tecncias e movimento

    private static long countArtistsWithNullBirthday(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() == null)
                .count();
    }
}
