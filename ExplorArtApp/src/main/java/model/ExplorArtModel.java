package model;

import apiservice.ApiService;
import presenter.ExplorArtContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExplorArtModel implements ExplorArtContract.Model {

    @Override
    public List<Artista> getArtists() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/artistas", Artista.class);
    }

    @Override
    public List<Obra_Arte> getArtworks() {
        // Lógica para obter obras de arte
        return new ArrayList<>();
    }

    @Override
    public List<Evento> getEvents() {
        // Lógica para obter eventos
        return new ArrayList<>();
    }

    @Override
    public List<Galeria> getGalleries() {
        // Lógica para obter galerias
        return new ArrayList<>();
    }

    @Override
    public List<Galerista> getGallerists() {
        // Lógica para obter galeristas
        return new ArrayList<>();
    }
}
