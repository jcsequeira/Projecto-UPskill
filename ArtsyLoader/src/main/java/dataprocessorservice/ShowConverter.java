package dataprocessorservice;

import artsymodel.ArtsyShow;
import model.Evento;

public class ShowConverter {
    private static final int IS_ARTSY = 1;

    public static Evento process (ArtsyShow artsyShow){
        Evento evento = new Evento();
        evento.setNome(artsyShow.getName());
        evento.setData_inicio(artsyShow.getStartAt());
        evento.setData_Fim(artsyShow.getEndAt());
        evento.setDescricao(artsyShow.getDescription());
        evento.setId_Expo(1);
        evento.setIsArtsy(IS_ARTSY);

        return evento;
    }
}
