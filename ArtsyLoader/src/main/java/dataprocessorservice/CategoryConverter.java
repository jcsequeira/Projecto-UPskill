package dataprocessorservice;

import artsymodel.ArtsyArtwork;
import model.Tecnica;

public class CategoryConverter {

    public  static Tecnica process(ArtsyArtwork artsyArtwork) {
        Tecnica tecnica = new Tecnica();
        tecnica.setTipo_Tecnica(artsyArtwork.getCategory());

        return tecnica;
    }
}
