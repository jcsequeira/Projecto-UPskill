package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Colaborador;
import model.ExplorArtModel;
import model.Pais;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.util.List;

/**
 * The AddColaboradorFormView class represents the view for adding a new collaborator in the ExplorArtApp.
 * It extends Parent and provides a form for users to input collaborator details.
 *
 */
public class AddColaboradorFormView extends Parent {
    private final ExplorArtPresenter myPresenter;
    private final ComboBox<Pais> paisComboBox;
    private final ObservableList<Pais> paisObservableList;

    /**
     * Constructs an instance of {@code AddColaboradorFormView}.
     *
     * @param colaborador The collaborator object to which the entered details will be associated.
     */
    public AddColaboradorFormView(Colaborador colaborador) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Nome do Evento
        Label nomeLabel = new Label("Nome do colaborador:");
        TextField nomeField = new TextField();
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(nomeField, 1, 0);

        // email do colaborador
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);

        // Telefone do colaborador
        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField();
        gridPane.add(telefoneLabel, 0, 2);
        gridPane.add(telefoneField, 1, 2);

        // País
        Label paisLabel = new Label("Naturalidade:");
        List<Pais> paisList;
        ExplorArtModel modelAux = new ExplorArtModel();
        try {
            paisList = modelAux.getPaises();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        paisObservableList = FXCollections.observableArrayList();
        paisObservableList.clear();
        paisObservableList.addAll(paisList);
        paisComboBox = new ComboBox<>(paisObservableList);
        gridPane.add(paisLabel, 0, 3);
        gridPane.add(paisComboBox, 1, 3);

        getChildren().add(gridPane);

        // Botão "Adicionar" e respetiva lógica
        Button submitButton = new Button("Adicionar colaborador");
        submitButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Novo colaborador");
                confirmationAlert.setHeaderText("Tem a certeza que deseja adicionar o novo colaborador?");
                confirmationAlert.setContentText("Ao confirmar, o colaborador será adicionado à Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    colaborador.setNome_Colaborador(nomeField.getText());
                    colaborador.setEmail(emailField.getText());
                    colaborador.setTelefone(telefoneField.getText());
                    colaborador.setCodigo_Pais(paisComboBox.getSelectionModel().getSelectedItem().getCodigo_Pais());

                    myPresenter.addColaborador(colaborador);
                    // Se o utilizador confirmar, fecha a janela
                    getScene().getWindow().hide();
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        gridPane.add(submitButton, 0, 5, 2, 1);
    }
}
