package service;



import exceptions.ServiceException;
import model.Artista;
import model.Cidade;
import repository.CidadeRepository;

import java.util.List;


public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<Cidade> getAllCidade (){
        return cidadeRepository.getAllCidade();
    }

    public Cidade getCidadeById(int cidadeId){ return cidadeRepository.getCidadeById(cidadeId); }

    public Cidade addCidade(Cidade cidade){
        // Validate events with mandatory dates
        if (cidade.getNome_Cidade() == null) {
            throw new ServiceException("Nome_Cidade can't be null.");
        }
        return cidadeRepository.addCidade(cidade);
    }

    public Cidade updateCidade(int id, Cidade cidade) {
        if (cidade.getNome_Cidade() == null) {
            throw new ServiceException("Nome_Cidade can't be null.");
        }
        return cidadeRepository.updateCidade(id, cidade);
    }

    public String deleteCidade(int id) {
        return cidadeRepository.deleteCidade(id);
    }

    public void addAllCidades(List<Cidade> cidadeList) {
        for (Cidade cidade : cidadeList) {
            cidadeRepository.addCidade(cidade);
        }
    }
}
