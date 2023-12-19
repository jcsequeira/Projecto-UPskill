package dataprocessorservice;

import artsymodel.ArtsyPartner;
import model.Galeria;

public class PartnerConverter {
    private static final int IS_ARTSY = 1;
    private static final int SYSTEM_CITY_BD_ID = 1;
    private static final int  SYSTEM_USER_GALERISTA_BD_ID= 2;

    public static Galeria process (ArtsyPartner artsyPartner) {
        Galeria galeria = new Galeria();

        galeria.setNome_Galeria(artsyPartner.getName());
        galeria.setMorada(artsyPartner.getRegion());
        galeria.setEmail(artsyPartner.getEmail());
        galeria.setWebsite(artsyPartner.getWebsiteHref());
        galeria.setId_Cidade(SYSTEM_CITY_BD_ID);
        galeria.setId_colaborador(SYSTEM_USER_GALERISTA_BD_ID);
        galeria.setIsArtsy(IS_ARTSY);


        return galeria;
    }
}
