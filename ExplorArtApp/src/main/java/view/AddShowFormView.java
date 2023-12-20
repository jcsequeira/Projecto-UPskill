package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Evento;
import model.ExplorArtModel;
import model.Galeria;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.util.List;

/**
 * The AddShowFormView class represents the view for adding a new event (show).
 * It includes form elements for capturing information about the event and its associated details.
 * Upon submitting the form, the data is processed and added to the database through the presenter.
 */
public class AddShowFormView extends Parent {
    private final ExplorArtPresenter myPresenter;
    private final ComboBox<Galeria> galeriaComboBox;
    private final ObservableList<Galeria> galeriaObservableList;

    /**
     * Constructs an instance of AddShowFormView with the specified event.
     *
     * @param evento The event to be edited or added.
     */
    public AddShowFormView(Evento evento) {
        // Initialize presenter and layout elements
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Initialize form elements for event information
        Label nomeLabel = new Label("Nome do evento:");
        TextField nomeField = new TextField();
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(nomeField, 1, 0);

        // Data de inicio
        Label startDayLabel = new Label("Data de inicio:");
        DatePicker startDayPicker = new DatePicker();
        gridPane.add(startDayLabel, 0, 1);
        gridPane.add(startDayPicker, 1, 1);

        // Data de término
        Label endDayLabel = new Label("Data de término:");
        DatePicker endDayPicker = new DatePicker();
        gridPane.add(endDayLabel, 0, 2);
        gridPane.add(endDayPicker, 1, 2);

        // Descrição do evento
        Label descriptionLabel = new Label("Descrição do evento:");
        TextField descriptionField = new TextField();
        gridPane.add(descriptionLabel, 0, 3);
        gridPane.add(descriptionField, 1, 3);

        // Initialize and populate ComboBox for galleries
        Label galleryLabel = new Label("Galeria:");
        List<Galeria> galleryList;
        ExplorArtModel modelAux = new ExplorArtModel();
        try {
            galleryList = modelAux.getGalleries();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        galeriaObservableList = FXCollections.observableArrayList();
        galeriaObservableList.clear();
        galeriaObservableList.addAll(galleryList);
        galeriaComboBox = new ComboBox<>(galeriaObservableList);
        gridPane.add(galleryLabel, 0, 4);
        gridPane.add(galeriaComboBox, 1, 4);

        getChildren().add(gridPane);

        // Button and its logic for adding a new event
        Button submitButton = new Button("Adicionar Evento");
        submitButton.setOnAction(event -> {
            try {
                // Confirmation alert
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Novo evento");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar o novo evento?");
                confirmationAlert.setContentText("Ao confirmar, o evento será adicionado à Base de Dados.");

                // Obtain confirmation result
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                // Process the data upon confirmation
                if (result == ButtonType.OK) {
                    evento.setNome(nomeField.getText());
                    evento.setData_inicio(startDayPicker.getValue());
                    evento.setData_Fim(endDayPicker.getValue());
                    evento.setDescricao(descriptionField.getText());
                    evento.setId_Galeria(galeriaComboBox.getSelectionModel().getSelectedItem().getId_Galeria());

                    myPresenter.addShow(evento);
                    // Close the window upon user confirmation
                    getScene().getWindow().hide();
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });

        // Add elements to the layout
        gridPane.add(submitButton, 0, 5, 2, 1);
    }
}
