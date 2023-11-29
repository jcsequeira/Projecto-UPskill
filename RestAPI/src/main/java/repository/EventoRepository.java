package repository;


import model.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoRepository {

    private Connection con;

    public EventoRepository(Connection con) {
        this.con = con;
    }


    public List<Evento> getAllEvento (){
        List<Evento> eventoList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from Evento")){
            while (resultSet.next()){
                Evento evento = new Evento();
                evento.setId_Expo(resultSet.getInt("id_Expo"));
                evento.setNome(resultSet.getString("Nome"));
                evento.setData_inicio(resultSet.getDate("Data_inicio").toLocalDate());
                evento.setData_Fim(resultSet.getDate("Data_Fim").toLocalDate());
                evento.setDescricao(resultSet.getString("Descricao"));
                evento.setId_Galeria(resultSet.getInt("id_Galeria"));
                evento.setIsArtsy(resultSet.getInt("IsArtsy"));
                eventoList.add(evento);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();}

        return eventoList;
    }

    public Evento getEventoById(int eventoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Evento WHERE id_Expo = ?")) {

            preparedStatement.setInt(1, eventoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Evento object and return it
                    Evento evento = new Evento();
                    evento.setId_Expo(resultSet.getInt("id_Expo"));
                    evento.setNome(resultSet.getString("Nome"));
                    evento.setData_inicio(resultSet.getDate("Data_inicio").toLocalDate());
                    evento.setData_Fim(resultSet.getDate("Data_Fim").toLocalDate());
                    evento.setDescricao(resultSet.getString("Descricao"));
                    evento.setId_Galeria(resultSet.getInt("id_Galeria"));
                    evento.setIsArtsy(resultSet.getInt("IsArtsy"));
                    return evento;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the evento is not found or an exception occurs
    }


    //Versao Assumindo autoIncrement na tabela evento, na PK CodigoEvento
    //Nota: o metodo vai inserir a mesma na tabela o evento, desde que o Codigo_Evento ainda nao exista, mas vai retornar null na resposta
    public Evento addEvento(Evento evento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Evento (Nome, Data_inicio, Data_Fim, Descricao, id_Galeria, IsArtsy) " +
                        "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setDate(2, Date.valueOf(evento.getData_inicio()));
            preparedStatement.setDate(3, Date.valueOf(evento.getData_Fim()));
            preparedStatement.setString(4, evento.getDescricao());
            preparedStatement.setInt(5, evento.getId_Galeria());
            preparedStatement.setInt(6, evento.getIsArtsy());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object evento failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the evento object
                    evento.setId_Expo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object evento failed, no ID obtained.");
                }
            }

            return evento;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Evento updateEvento(int id, Evento evento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Evento SET Nome = ?, Data_inicio = ?, Data_Fim = ?, Descricao = ?, id_Galeria = ?, IsArtsy = ? WHERE id_Expo = ?")) {

            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setDate(2, Date.valueOf(evento.getData_inicio()));
            preparedStatement.setDate(3, Date.valueOf(evento.getData_Fim()));
            preparedStatement.setString(4, evento.getDescricao());
            preparedStatement.setInt(5, evento.getId_Galeria());
            preparedStatement.setInt(6, evento.getIsArtsy());
            preparedStatement.setInt(7, id); // assuming 'id' is the id_Expo to update


            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // If the update was successful, return the updated evento
                return getEventoById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the update fails or an exception occurs
    }


    public String deleteEvento(int eventoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM evento WHERE id_Expo = ?")) {
            preparedStatement.setInt(1, eventoId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                //throw new SQLException("Deleting Evento  failed, no rows affected.");
                return "Deleting Evento failed, no rows affected.";
            } else {return "Deleting Evento  successful.";}
        } catch (SQLIntegrityConstraintViolationException e ) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "Deleting Evento  failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
