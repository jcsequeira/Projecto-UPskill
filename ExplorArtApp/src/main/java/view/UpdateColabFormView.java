/**
 * The UpdateColabFormView class represents a view for updating collaborator information.
 * It allows modifying the collaborator's name, email, phone number, and nationality.
 */
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
import java.util.Optional;

public class UpdateColabFormView extends Parent {
    private Colaborador colaborador;
    private ExplorArtPresenter myPresenter;
    private ComboBox<Pais> paisComboBox;
    private ObservableList<Pais> paisObservableList;

    /**
     * Constructs an instance of UpdateColabFormView with the specified collaborator details.
     *
     * @param colaborador The collaborator for which information is being updated.
     */
    public UpdateColabFormView(Colaborador colaborador) {
        this.colaborador = colaborador;

        doLayout(colaborador);
    }

    /**
     * Configures the layout for updating collaborator information.
     *
     * @param colaborador The collaborator for which information is being updated.
     */
    private void doLayout(Colaborador colaborador) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Nome do Colaborador
        Label nomeLabel = new Label("Nome do colaborador:");
        TextField nomeField = new TextField(colaborador.getNome_Colaborador());
        gridPane.add(nomeLabel, 0, 0);
        gridPane.add(nomeField, 1, 0);

        // Email do Colaborador
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(colaborador.getEmail());
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);

        // Telefone do Colaborador
        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneField = new TextField(colaborador.getTelefone());
        gridPane.add(telefoneLabel, 0, 2);
        gridPane.add(telefoneField, 1, 2);

        // Nacionalidade
        Label paisLabel = new Label("Naturalidade:");
        List<Pais> paisList;
        ExplorArtModel modelAux = new ExplorArtModel();
        try {
            paisList = modelAux.getPaises();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        Optional<Pais> selectedCountry = paisList.stream()
                .filter(pais -> pais.getCodigo_Pais() == colaborador.getCodigo_Pais())
                .findFirst();

        paisObservableList = FXCollections.observableArrayList();
        paisObservableList.clear();
        paisObservableList.addAll(paisList);
        paisComboBox = new ComboBox<>(paisObservableList);
        paisComboBox.getSelectionModel().select(selectedCountry.get());
        gridPane.add(paisLabel, 0, 3);
        gridPane.add(paisComboBox, 1, 3);

        getChildren().add(gridPane);

        // Botão "Modificar" e respetiva lógica
        Button submitButton = new Button("Modificar colaborador");
        submitButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Modificar colaborador");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar as alterações efetuadas no colaborador?");
                confirmationAlert.setContentText("Ao confirmar, o colaborador será atualizado na Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    colaborador.setNome_Colaborador(nomeField.getText());
                    colaborador.setEmail(emailField.getText());
                    colaborador.setTelefone(telefoneField.getText());
                    colaborador.setCodigo_Pais(paisComboBox.getSelectionModel().getSelectedItem().getCodigo_Pais());

                    myPresenter.modifyColab(colaborador.getId_colaborador(), colaborador);
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
