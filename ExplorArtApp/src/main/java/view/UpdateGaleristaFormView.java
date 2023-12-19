package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.ExplorArtModel;
import model.Galerista;
import presenter.ExplorArtPresenter;

import java.io.IOException;

public class UpdateGaleristaFormView extends Parent {
    private Galerista galerista;
    private ExplorArtPresenter myPresenter;

    public UpdateGaleristaFormView(Galerista galerista) {
        this.galerista = galerista;

        doLayout(galerista);
    }

    private void doLayout(Galerista galerista) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

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
                confirmationAlert.setTitle("Modificar galerista");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar as alterações efetuadas no galerista?");
                confirmationAlert.setContentText("Ao confirmar, o galerista será atualizado na Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    galerista.setData_Inicio_Atividade(startDayPicker.getValue());
                    galerista.setPassword(passwordField.getText());

                    myPresenter.modifyGallerist(galerista.getId_colaborador(), galerista);
                    // Se o utilizador confirmar, fecha a janela
                    getScene().getWindow().hide();
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });

        gridPane.add(submitButton, 0, 3, 2, 1);
    }
}

