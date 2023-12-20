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
 * The UpdateArtistaFormView class represents a view for updating artist information.
 * It allows modifying the artist's name, birth date, biography, death date, and nationality.
 */
public class UpdateArtistaFormView extends Parent {
    private final Artista artista;
    private Pais nacionalidade;

    private ExplorArtPresenter myPresenter;
    private ComboBox<Pais> paisComboBox;
    private ObservableList<Pais> paisObservableList;

    /**
     * Constructs an instance of UpdateArtistaFormView with the specified artist.
     *
     * @param artista The artist for which information is being updated.
     */
    public UpdateArtistaFormView(Artista artista) {
        this.artista = artista;
        doLayout(artista);
    }

    /**
     * Configures the layout for updating artist information.
     *
     * @param artista The artist for which information is being updated.
     */
    private void doLayout(Artista artista) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Título
        Label nameLabel = new Label("Nome:");
        TextField nameField = new TextField(artista.getNome_artista());
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

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
        Pais paisAux = new Pais();
        paisAux.setNacionalidade(artista.getNacionalidade());
        paisList.add(paisAux);
        paisObservableList = FXCollections.observableArrayList();
        paisObservableList.clear();
        paisObservableList.addAll(paisList);
        paisComboBox = new ComboBox<>(paisObservableList);
        paisComboBox.getSelectionModel().select(paisAux);
        gridPane.add(nacionalityLabel, 0, 4);
        gridPane.add(paisComboBox, 1, 4);

        getChildren().add(gridPane);

        // Botão "Adicionar" e respetiva lógica
        Button submitButton = new Button("Modificar Artista");
        submitButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Modificar artista");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar  as alterações efetuadas no artista?");
                confirmationAlert.setContentText("Ao confirmar, o artista será atualizado na Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    artista.setNome_artista(nameField.getText());
                    artista.setData_Nascimento(birthDayPicker.getValue());
                    artista.setBiografia(biographyField.getText());
                    artista.setData_Morte(deathDayPicker.getValue());
                    artista.setNacionalidade(paisComboBox.getSelectionModel().getSelectedItem().getNacionalidade());

                    myPresenter.modifyArtist((int) artista.getId_artista(), artista);
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
