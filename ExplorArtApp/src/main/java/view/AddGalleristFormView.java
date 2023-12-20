package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Colaborador;
import model.ExplorArtModel;
import model.Galerista;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.util.List;

/**
 * The AddGalleristFormView class represents the view for adding a new gallerist in the ExplorArtApp.
 * It extends Parent and provides a form for users to input gallerist details.
 *
 */
public class AddGalleristFormView extends Parent {
    private final ExplorArtPresenter myPresenter;
    private final ComboBox<Colaborador> colaboradorComboBox;
    private final ObservableList<Colaborador> colaboradorObservableList;

    /**
     * Constructs an instance of AddGalleristFormView.
     *
     * @param galerista The gallerist object to which the entered details will be associated.
     */
    public AddGalleristFormView(Galerista galerista) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Nome do Galerista
        Label nomeLabel = new Label("Nome:");
        List<Colaborador> colaboradorList;
        ExplorArtModel modelAux = new ExplorArtModel();
        try {
            colaboradorList = modelAux.getColaboradores();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        colaboradorObservableList = FXCollections.observableArrayList();
        colaboradorObservableList.clear();
        colaboradorObservableList.addAll(colaboradorList);
        colaboradorComboBox = new ComboBox<>(colaboradorObservableList);
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(colaboradorComboBox, 1, 0);

        // Data de inicio de atividade
        Label startDayLabel = new Label("Data de inicio de atividade:");
        DatePicker startDayPicker = new DatePicker();
        gridPane.add(startDayLabel, 0, 1);
        gridPane.add(startDayPicker, 1, 1);

        // Password
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);

        getChildren().add(gridPane);

        // Botão "Adicionar" e respetiva lógica
        Button submitButton = new Button("Adicionar galerista");
        submitButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Novo galerista");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar o novo galerista?");
                confirmationAlert.setContentText("Ao confirmar, o galerista será adicionado à Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    galerista.setId_colaborador(colaboradorComboBox.getSelectionModel().getSelectedItem().getId_colaborador());
                    galerista.setData_Inicio_Atividade(startDayPicker.getValue());
                    galerista.setPassword(passwordField.getText());

                    myPresenter.addGalerist(galerista);
                    // Se o utilizador confirmar, fecha a janela
                    getScene().getWindow().hide();
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        gridPane.add(submitButton, 0, 3, 2, 1);
    }
}
