package dataprocessorservice;


import artsymodel.ArtsyArtwork;
import model.Obra_Arte;



public class ArtworkConverter {

    private static final int IS_ARTSY = 1;

    public static Obra_Arte process(ArtsyArtwork artsyArtwork) {
        Obra_Arte obraArte = new Obra_Arte();
        obraArte.setTitulo(artsyArtwork.getTitle());
        obraArte.setLink_Imagem(artsyArtwork.getThumbnailHref());
        obraArte.setAno_Criacao(artsyArtwork.getDate());
        obraArte.setPreco(0);
        obraArte.setAltura(artsyArtwork.getHeight());
        obraArte.setLargura(artsyArtwork.getWidth());
        obraArte.setProfundidade(artsyArtwork.getDepth());
        obraArte.setDiametro(artsyArtwork.getDiameter());
        obraArte.setIsActive(1);
        obraArte.setId_artista(1); //will be updated
        obraArte.setId_Tecnica(1); //will be updated
        obraArte.setId_Estilo(1); //will be updated
        obraArte.setIsArtsy(IS_ARTSY);
        obraArte.setId_Material(1); //will be updated

    return obraArte;
    }
}
