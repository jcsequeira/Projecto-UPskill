package dataprocessorservice;

import artsymodel.ArtsyShow;
import model.Evento;

public class ShowConverter {
    private static final int IS_ARTSY = 1;

    public static Evento process (ArtsyShow artsyShow){
        Evento evento = new Evento();
        evento.setNome(artsyShow.getName());
        evento.setData_inicio(artsyShow.getStart_at());
        evento.setData_Fim(artsyShow.getEnd_at());
        evento.setDescricao(artsyShow.getDescription());
        evento.setId_Galeria(1);
        evento.setIsArtsy(IS_ARTSY);

        return evento;
    }
}
