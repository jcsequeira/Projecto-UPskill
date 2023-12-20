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
 * The UpdateEventoFormView class represents a view for updating event information.
 * It allows modifying the event's name, start date, end date, description, and associated gallery.
 */
public class UpdateEventoFormView extends Parent {
    private final Evento evento;
    private final Galeria galeria;
    private ExplorArtPresenter myPresenter;
    private ComboBox<Galeria> galeriaComboBox;
    private ObservableList<Galeria> galeriaObservableList;

    /**
     * Constructs an instance of UpdateEventoFormView with the specified event and gallery details.
     *
     * @param evento  The event for which information is being updated.
     * @param galeria The gallery associated with the event.
     */
    public UpdateEventoFormView(Evento evento, Galeria galeria) {
        this.evento = evento;
        this.galeria = galeria;

        doLayout(evento);
    }

    /**
     * Configures the layout for updating event information.
     *
     * @param evento The event for which information is being updated.
     */
    private void doLayout(Evento evento) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Nome do Evento
        Label nomeLabel = new Label("Nome do evento:");
        TextField nomeField = new TextField(evento.getNome());
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(nomeField, 1, 0);

        // Data de início
        Label startDayLabel = new Label("Data de início:");
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
        TextField descriptionField = new TextField(evento.getDescricao());
        gridPane.add(descriptionLabel, 0, 3);
        gridPane.add(descriptionField, 1, 3);

        // Galeria
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
        galeriaComboBox.getSelectionModel().select(galeria);
        gridPane.add(galleryLabel, 0, 4);
        gridPane.add(galeriaComboBox, 1, 4);

        getChildren().add(gridPane);

        // Botão "Modificar" e respetiva lógica
        Button submitButton = new Button("Modificar Evento");
        submitButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Modificar evento");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar as alterações efetuadas no evento?");
                confirmationAlert.setContentText("Ao confirmar, o evento será atualizado na Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    evento.setNome(nomeField.getText());
                    evento.setData_inicio(startDayPicker.getValue());
                    evento.setData_Fim(endDayPicker.getValue());
                    evento.setDescricao(descriptionField.getText());
                    evento.setId_Galeria(galeriaComboBox.getSelectionModel().getSelectedItem().getId_Galeria());

                    myPresenter.modifyShow(evento.getId_Expo(), evento);
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
