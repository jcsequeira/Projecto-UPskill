package repository;

import model.Obra_Arte;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ObraArteRepository {
    private Connection con;

    public ObraArteRepository(Connection con) {
        this.con = con;
    }

    public List<Obra_Arte> getAllObraArte (){
        List<Obra_Arte> obraArteList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from obra_arte")){
            while (resultSet.next()){
                Obra_Arte obraArte = new Obra_Arte();
                obraArte.setId_Obra_Arte(resultSet.getInt("id_obra_arte"));
                obraArte.setTitulo(resultSet.getString("titulo"));
                obraArte.setLink_Imagem(resultSet.getString("Link_Imagem"));
                if (resultSet.getDate("Ano_Criacao") != null){
                    obraArte.setAno_Criacao(resultSet.getDate("Ano_Criacao").toLocalDate());}
                else {
                    obraArte.setAno_Criacao(null);
                }
                obraArte.setPreco(resultSet.getFloat("Preco"));
                obraArte.setLargura(resultSet.getFloat("Largura"));
                obraArte.setProfundidade(resultSet.getFloat("Profundidade"));
                obraArte.setDiametro(resultSet.getFloat("Diametro"));
                obraArte.setIsActive(resultSet.getInt("IsActive"));
                obraArte.setId_artista(resultSet.getInt("id_artista"));
                obraArte.setId_Tecnica(resultSet.getInt("id_Tecnica"));
                obraArte.setId_Estilo(resultSet.getInt("id_Estilo"));
                obraArte.setIsArtsy(resultSet.getInt("IsArtsy"));

                obraArteList.add(obraArte);
            }

            //ResultSet resultSetObraMaterial = statement.executeQuery("select * from obra_arte"))
        }
        catch (SQLException sqlException){sqlException.printStackTrace();};

        return obraArteList;
    }

    public Obra_Arte getObraArteById(){
        // Por fazer
        return null;
    }
}
