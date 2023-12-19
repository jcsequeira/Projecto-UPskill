package dataprocessorservice;

import artsymodel.ArtsyShow;
import model.Evento;

/**
 * The {@code ShowConverter} class is responsible for converting an {@link ArtsyShow} object
 * to an {@link Evento} object representing an art show event.
 */
public class ShowConverter {

    private static final int IS_ARTSY = 1;

    /**
     * Converts an {@link ArtsyShow} object to an {@link Evento} object representing an art show event.
     * All foreign keys will be assgined to 1 on conversion
     *
     * @param artsyShow The Artsy show for which the event is to be converted.
     * @return An {@link Evento} object representing the converted art show event.
     */
    public static Evento process(ArtsyShow artsyShow) {
        Evento evento = new Evento();
        evento.setNome(artsyShow.getName());
        evento.setData_inicio(artsyShow.getStart_at());
        evento.setData_Fim(artsyShow.getEnd_at());

        if (artsyShow.getDescription() != null) {
            evento.setDescricao(artsyShow.getDescription());
        } else {
            evento.setDescricao("To Be Announced");
        }

        evento.setId_Galeria(1); // will be updated
        evento.setIsArtsy(IS_ARTSY);

        return evento;
    }
}
