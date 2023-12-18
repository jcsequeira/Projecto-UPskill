package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.*;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.util.List;

public class UpdateGaleriaFormView extends Parent {
    private Galeria galeria;
    private ExplorArtPresenter myPresenter;
    private ObservableList<Cidade> cidadeObservableList;
    private ComboBox<Cidade> cidadeComboBox;
    private ObservableList<Galerista> galeristaObservableList;
    private ComboBox<Galerista> galeristaComboBox;

    public UpdateGaleriaFormView(Galeria galeria) {
        this.galeria = galeria;

        doLayout(galeria);
    }

    private void doLayout(Galeria galeria) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Nome da galeria
        Label nomeGaleriaLabel = new Label("Nome da galeria:");
        TextField nomeGaleriaField = new TextField(galeria.getNome_Galeria());
        gridPane.add(nomeGaleriaLabel, 0, 0);
        gridPane.add(nomeGaleriaField, 1, 0);

        // Morada da galeria
        Label moradaGaleriaLabel = new Label("Morada:");
        TextField moradaGaleriaField = new TextField(galeria.getMorada());
        gridPane.add(moradaGaleriaLabel, 0, 1);
        gridPane.add(moradaGaleriaField, 1, 1);

        // Cidade da galeria
        Label cidadeGaleriaLabel = new Label("Cidade:");
        List<Cidade> cidadesList = null;
        ExplorArtModel modelAux = new ExplorArtModel();
        try {
            cidadesList = modelAux.getCidades();
        } catch (IOException e){
            throw  new RuntimeException();
        }

        cidadeObservableList = FXCollections.observableArrayList();
        cidadeObservableList.clear();
        cidadeObservableList.addAll(cidadesList);
        cidadeComboBox = new ComboBox<>(cidadeObservableList);
        cidadeComboBox.getSelectionModel().select(galeria.getId_Cidade());
        gridPane.add(cidadeGaleriaLabel, 0, 2);
        gridPane.add(cidadeComboBox, 1, 2);

        // Telefone da galeria
        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField(galeria.getTelefone());
        gridPane.add(telefoneLabel, 0, 3);
        gridPane.add(telefoneField, 1, 3);

        // Email da galeria
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(galeria.getEmail());
        gridPane.add(emailLabel, 0, 4);
        gridPane.add(emailField, 1, 4);

        // Website da galeria
        Label urlLabel = new Label("Website:");
        TextField urlField = new TextField(galeria.getWebsite());
        gridPane.add(urlLabel, 0, 5);
        gridPane.add(urlField, 1, 5);

        // Nome do Galerista associado
        Label nomeLabel = new Label("Galerista:");
        List<Galerista> galeristaList;
        try {
            galeristaList = modelAux.getGallerists();
        } catch (IOException e){
            throw  new RuntimeException();
        }
        galeristaObservableList = FXCollections.observableArrayList();
        galeristaObservableList.clear();
        galeristaObservableList.addAll(galeristaList);
        galeristaComboBox = new ComboBox<>(galeristaObservableList);
        galeristaComboBox.getSelectionModel().select(galeria.getId_colaborador());
        gridPane.add(nomeLabel, 0, 6);
        gridPane.add(galeristaComboBox, 1, 6);

        getChildren().add(gridPane);

        // Botão "Adicionar" e respetiva lógica
        Button submitButton = new Button("Modificar galeria");
        submitButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Modificar galeria");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardas as alterações efetuadas na galeria?");
                confirmationAlert.setContentText("Ao confirmar, a galeria será atualizada na Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    galeria.setNome_Galeria(nomeGaleriaField.getText());
                    galeria.setMorada(moradaGaleriaField.getText());
                    galeria.setId_Cidade(cidadeComboBox.getSelectionModel().getSelectedItem().getId_Cidade());
                    galeria.setTelefone(telefoneField.getText());
                    galeria.setEmail(emailField.getText());
                    galeria.setWebsite(urlField.getText());
                    galeria.setId_colaborador(galeristaComboBox.getSelectionModel().getSelectedItem().getId_colaborador());

                    myPresenter.modifyGallery(galeria.getId_Galeria(), galeria);
                    // Se o utilizador confirmar, fecha a janela
                    getScene().getWindow().hide();
                }
            } catch (NumberFormatException e){
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });

        gridPane.add(submitButton, 0, 7, 2, 1);

    }
}
