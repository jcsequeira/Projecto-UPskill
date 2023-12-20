package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Artista;
import model.ExplorArtModel;
import model.Pais;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.util.List;

/**
 * The AddArtistaFormView class represents the view for adding a new artist in the ExplorArtApp.
 * It extends Parent and provides a form for users to input artist details.
 */
public class AddArtistaFormView extends Parent {

    private final ExplorArtPresenter myPresenter;
    private final ComboBox<Pais> paisesComboBox;
    private final ObservableList<Pais> paisesObservableList;

    /**
     * Constructs an instance of AddArtistaFormView.
     *
     * @param artista The artist object to which the entered details will be associated.
     */
    public AddArtistaFormView(Artista artista) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Nome do Artista
        Label nomeLabel = new Label("Nome:");
        TextField nomeField = new TextField();
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(nomeField, 1, 0);

        // Data de Nascimento
        Label birthDayLabel = new Label("Data de nascimento:");
        DatePicker birthDayPicker = new DatePicker();
        gridPane.add(birthDayLabel, 0, 1);
        gridPane.add(birthDayPicker, 1, 1);

        // Biografia do Artista
        Label biographyLabel = new Label("Biografia:");
        TextField biographyField = new TextField();
        gridPane.add(biographyLabel, 0, 2);
        gridPane.add(biographyField, 1, 2);

        // Data de morte
        Label deathDayLabel = new Label("Data de morte:");
        DatePicker deathDayPicker = new DatePicker();
        gridPane.add(deathDayLabel, 0, 3);
        gridPane.add(deathDayPicker, 1, 3);

        // Nacionalidade
        Label nacionalityLabel = new Label("Nacionalidade:");
        List<Pais> paisList = null;
        ExplorArtModel modelAux = new ExplorArtModel();
        try {
            paisList = modelAux.getPaises();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        paisesObservableList = FXCollections.observableArrayList();
        paisesObservableList.clear();
        paisesObservableList.addAll(paisList);
        paisesComboBox = new ComboBox<>(paisesObservableList);
        gridPane.add(nacionalityLabel, 0, 4);
        gridPane.add(paisesComboBox, 1, 4);

        getChildren().add(gridPane);

        // Botão "Adicionar" e respetiva lógica
        Button submitButton = new Button("Adicionar Artista");
        submitButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Novo artista");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar o novo artista?");
                confirmationAlert.setContentText("Ao confirmar, o artista será adicionado à Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    artista.setNome_artista(nomeField.getText());
                    artista.setData_Nascimento(birthDayPicker.getValue());
                    artista.setBiografia(biographyField.getText());
                    artista.setData_Morte(deathDayPicker.getValue());
                    artista.setNacionalidade(paisesComboBox.getSelectionModel().getSelectedItem().getNacionalidade());

                    myPresenter.addArtist(artista);
                    // Se o utilizador confirmar, fecha a janela
                    getScene().getWindow().hide();
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });

        gridPane.add(submitButton, 0, 5, 2, 1);
    }
}
