package dataprocessorservice;

import artsymodel.ArtsyArtwork;
import model.Materiais;

public class MediumConverter {

        public  static Materiais process(ArtsyArtwork artsyArtwork) {
            Materiais material = new Materiais();
            material.setTipo_Material(artsyArtwork.getMedium());

            return material;
        }

}
