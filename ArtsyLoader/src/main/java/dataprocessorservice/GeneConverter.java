package dataprocessorservice;


import artsymodel.ArtsyGene;
import model.Movimento;


public class GeneConverter {   

    public  static Movimento process(ArtsyGene artsyGene) {
        Movimento movimento = new Movimento();
        movimento.setNome_Movimento(artsyGene.getName());
        return movimento;
    }
    
}
