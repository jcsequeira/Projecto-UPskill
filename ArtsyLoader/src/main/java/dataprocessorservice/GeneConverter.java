package dataprocessorservice;

import artsymodel.ArtsyGene;
import model.Movimento;

/**
 * The {@code GeneConverter} class is responsible for converting an {@link ArtsyGene} object
 * to a {@link Movimento} object representing the gene as a movement in art.
 */
public class GeneConverter {

    /**
     * Converts an {@link ArtsyGene} object to a {@link Movimento} object representing the gene as a movement in art.
     *
     * @param artsyGene The Artsy gene to be converted.
     * @return A {@link Movimento} object representing the converted gene as a movement in art.
     */
    public static Movimento process(ArtsyGene artsyGene) {
        Movimento movimento = new Movimento();
        movimento.setNome_Movimento(artsyGene.getName());
        return movimento;
    }
}
