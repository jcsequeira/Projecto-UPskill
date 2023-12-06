package dataprocessorservice;

import artsymodel.ArtsyArtwork;
import artsymodel.ArtsyPartner;
import model.Galeria;
import model.Obra_Arte;

public class PartnerConverter {
    private static final int IS_ARTSY = 1;

    public static Galeria process (ArtsyPartner artsyPartner) {
        Galeria galeria = new Galeria();

        galeria.setNome_Galeria(artsyPartner.getName());
        galeria.setMorada(artsyPartner.getRegion());
        galeria.setEmail(artsyPartner.getEmail());
        galeria.setWebsite(artsyPartner.getWebsiteHref());
        galeria.setIsArtsy(IS_ARTSY);


        return galeria;
    }
}
