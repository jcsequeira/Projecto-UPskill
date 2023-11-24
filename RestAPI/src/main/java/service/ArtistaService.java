package service;

import model.Artista;
import repository.ArtistRepository;

import java.util.List;

public class ArtistaService {
    private ArtistRepository artistaRepository;

    public ArtistaService(ArtistRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<Artista> getAllArtists (){
        return artistaRepository.getAllArtists();
    }
}
