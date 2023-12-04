package service;

import model.Artista;
import model.Materiais;
import repository.MateriaisRepository;

import java.util.List;

public class MateriaisService {

    private MateriaisRepository materiaisRepository;

    public MateriaisService(MateriaisRepository materiaisRepository) {
        this.materiaisRepository = materiaisRepository;
    }

    public List<Materiais> getAllMateriais (){return materiaisRepository.getAllMateriais();}

    public Materiais getMaterialById (int materialId){return materiaisRepository.getMaterialById(materialId);}

    public Materiais addMaterial (Materiais material){return materiaisRepository.addMaterial(material);}

    public Materiais upddateMaterial (int id, Materiais material){return materiaisRepository.updateMaterial(id, material);}

    public String deleteMaterial (int id){return materiaisRepository.deleteMaterial(id);}

    public void addAllMateriais(List<Materiais> MateriaisList) {
        for (Materiais materiais : MateriaisList) {
            materiaisRepository.addMaterial(materiais);
        }
    }
}
