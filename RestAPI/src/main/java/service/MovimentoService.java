package service;

import model.Movimento;
import repository.MovimentoRepository;

import java.util.List;

public class MovimentoService {

    private MovimentoRepository movimentoRepository;

    public MovimentoService(MovimentoRepository movimentoRepository) {
        this.movimentoRepository = movimentoRepository;
    }

    public List<Movimento> getAllMovimentos (){
        return movimentoRepository.getAllMovimentos();
    }

    public Movimento getMovimentoById(int movimentoId){ return movimentoRepository.getMovimentoById(movimentoId); }

    public Movimento addMovimento(Movimento movimento){
        // You can add additional validation logic before saving to the database if needed
        // For example, check if the fields are not empty
        // Save to the database
        return movimentoRepository.addMovimento(movimento);
    }

    public Movimento updateMovimento(int id, Movimento movimento) {
        // You might want to add validation logic or additional checks before updating
        return movimentoRepository.updateMovimento(id, movimento);
    }

    public String deleteMovimento(int id) {return
        movimentoRepository.deleteMovimento(id);
    }
}
