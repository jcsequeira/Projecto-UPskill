package dataprocessorservice;

import artsymodel.ArtsyArtist;
import model.Artista;



public class ArtistConverter {

    private static final int IS_ARTSY = 1;

    public  static Artista process(ArtsyArtist artsyArtist) {
        Artista artista = new Artista();

        // Populate the Artista fields
        artista.setNome_artista(artsyArtist.getName());
        artista.setData_Nascimento(artsyArtist.getBirthday());
        artista.setBiografia(artsyArtist.getBiography());
        artista.setData_Morte(artsyArtist.getDeathday());
        artista.setNacionalidade(artsyArtist.getNationality());
        artista.setIsArtsy(IS_ARTSY);
        return artista;
    }



}
