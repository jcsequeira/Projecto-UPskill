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
    public List<Obra_Arte> getArtworks() throws IOException {
        // Lógica para obter obras de arte
        return ApiService.getAllItems("http://localhost:4567/api/obrasarte", Obra_Arte.class);
    }

    @Override
    public List<Evento> getEvents() throws IOException {
        // Lógica para obter eventos
        return ApiService.getAllItems("http://localhost:4567/api/eventos", Evento.class);
    }

    @Override
    public List<Galeria> getGalleries() throws IOException {
        // Lógica para obter galerias
        return ApiService.getAllItems("http://localhost:4567/api/galerias", Galeria.class);
    }

    @Override
    public List<Galerista> getGallerists() throws IOException {
        // Lógica para obter galeristas
        return ApiService.getAllItems("http://localhost:4567/api/galeristas", Galerista.class);
    }
}
