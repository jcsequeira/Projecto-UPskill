/**
 * The UpdateArtworkFormView class represents a view for updating artwork information.
 * It allows modifying the artwork's title, image link, creation year, price, dimensions,
 * and associated artist, technique, movement, and materials.
 */
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

public class UpdateArtworkFormView extends Parent {

    private final Obra_Arte obraArte;
    private final Artista artista;
    private final Tecnica tecnica;
    private final Movimento movimento;
    private final Materiais material;

    private ExplorArtPresenter myPresenter;
    private ComboBox<Artista> artistaComboBox;
    private ObservableList<Artista> artistaObservableList;
    private ComboBox<Tecnica> tecnicaComboBox;
    private ObservableList<Tecnica> tecnicaObservableList;
    private ComboBox<Movimento> movimentoComboBox;
    private ObservableList<Movimento> movimentoObservableList;
    private ComboBox<Materiais> materiaisComboBox;
    private ObservableList<Materiais> materiaisObservableList;

    /**
     * Constructs an instance of UpdateArtworkFormView with the specified artwork details.
     *
     * @param obraArte  The artwork for which information is being updated.
     * @param artista   The associated artist.
     * @param tecnica   The associated technique.
     * @param movimento The associated movement.
     * @param material  The associated material.
     */
    public UpdateArtworkFormView(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {
        this.obraArte = obraArte;
        this.artista = artista;
        this.tecnica = tecnica;
        this.movimento = movimento;
        this.material = material;

        doLayout(obraArte);
    }

    /**
     * Configures the layout for updating artwork information.
     *
     * @param obraArte The artwork for which information is being updated.
     */
    private void doLayout(Obra_Arte obraArte) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Título
        Label titleLabel = new Label("Título:");
        TextField titleField = new TextField(obraArte.getTitulo());
        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleField, 1, 0);

        // Link da Imagem
        Label imageLinkLabel = new Label("Link da Imagem:");
        TextField imageLinkField = new TextField(obraArte.getLink_Imagem());
        gridPane.add(imageLinkLabel, 0, 1);
        gridPane.add(imageLinkField, 1, 1);

        // Ano de Criação
        Label creationYearLabel = new Label("Ano de Criação:");
        DatePicker creationYearPicker = new DatePicker(obraArte.getAno_Criacao());
        gridPane.add(creationYearLabel, 0, 2);
        gridPane.add(creationYearPicker, 1, 2);

        // Preço
        Label precoLabel = new Label("Preço:");
        TextField precoField = new TextField(String.valueOf(obraArte.getPreco()));
        gridPane.add(precoLabel, 0, 3);
        gridPane.add(precoField, 1, 3);

        // Altura
        Label alturaLabel = new Label("Altura:");
        TextField alturaField = new TextField(String.valueOf(obraArte.getAltura()));
        gridPane.add(alturaLabel, 0, 4);
        gridPane.add(alturaField, 1, 4);

        // Largura
        Label larguraLabel = new Label("Largura:");
        TextField larguraField = new TextField(String.valueOf(obraArte.getLargura()));
        gridPane.add(larguraLabel, 0, 5);
        gridPane.add(larguraField, 1, 5);

        // Profundidade
        Label profundidadeLabel = new Label("Profundidade:");
        TextField profundidadeField = new TextField(String.valueOf(obraArte.getProfundidade()));
        gridPane.add(profundidadeLabel, 0, 6);
        gridPane.add(profundidadeField, 1, 6);

        // Diâmetro
        Label diametroLabel = new Label("Diâmetro:");
        TextField diametroField = new TextField(String.valueOf(obraArte.getDiametro()));
        gridPane.add(diametroLabel, 0, 7);
        gridPane.add(diametroField, 1, 7);

        // Zoom in
        Label zoomInLabel = new Label("(Clique na imagem para ampliar)");
        gridPane.add(zoomInLabel, 0, 8, 2, 1);

        // ID do Artista
        Label artistIdLabel = new Label("Artista:");
        artistaComboBox = createComboBoxArtista();
        artistaComboBox.getSelectionModel().select(artista);
        gridPane.add(artistIdLabel, 0, 9);
        gridPane.add(artistaComboBox, 1, 9);

        // ID da Técnica
        Label techniqueIdLabel = new Label("Técnica:");
        tecnicaComboBox = createComboBoxTecnica();
        tecnicaComboBox.getSelectionModel().select(tecnica);
        gridPane.add(techniqueIdLabel, 0, 10);
        gridPane.add(tecnicaComboBox, 1, 10);

        // ID do Estilo
        Label styleIdLabel = new Label("Estilo:");
        movimentoComboBox = createComboBoxMovimento();
        movimentoComboBox.getSelectionModel().select(movimento);
        gridPane.add(styleIdLabel, 0, 11);
        gridPane.add(movimentoComboBox, 1, 11);

        // ID do Material
        Label materialsIdLabel = new Label("Materiais:");
        materiaisComboBox = createComboBoxMateriais();
        materiaisComboBox.getSelectionModel().select(material);
        gridPane.add(materialsIdLabel, 0, 12);
        gridPane.add(materiaisComboBox, 1, 12);

        getChildren().add(gridPane);

        // Botão "Atualizar" e respetiva lógica
        Button updateButton = new Button("Modificar Obra de Arte");
        updateButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Modificar Obra de Arte");
                confirmationAlert.setHeaderText("Tem a certeza que deseja guardar as alterações na obra de arte?");
                confirmationAlert.setContentText("Ao confirmar, a Obra de Arte será atualizada na Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                // Update the artwork information here
                if (result == ButtonType.OK) {
                    obraArte.setTitulo(titleField.getText());
                    obraArte.setAno_Criacao(creationYearPicker.getValue());
                    obraArte.setLink_Imagem(imageLinkField.getText());
                    obraArte.setPreco(Float.parseFloat(precoField.getText()));
                    obraArte.setAltura(Float.parseFloat(alturaField.getText()));
                    obraArte.setLargura(Float.parseFloat(larguraField.getText()));
                    obraArte.setProfundidade(Float.parseFloat(profundidadeField.getText()));
                    obraArte.setDiametro(Float.parseFloat(diametroField.getText()));
                    obraArte.setId_artista((int) artistaComboBox.getSelectionModel().getSelectedItem().getId_artista());
                    obraArte.setId_Tecnica(tecnicaComboBox.getSelectionModel().getSelectedItem().getId_Tecnica());
                    obraArte.setId_Estilo(movimentoComboBox.getSelectionModel().getSelectedItem().getId_Estilo());
                    obraArte.setId_Material(materiaisComboBox.getSelectionModel().getSelectedItem().getId_Material());

                    // Update the artwork using the presenter or model
                    myPresenter.modifyArtwork(obraArte.getId_Obra_Arte(), obraArte);

                    // Close the window
                    getScene().getWindow().hide();
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gridPane.add(updateButton, 0, 13, 2, 1);
    }

    private ComboBox<Artista> createComboBoxArtista() {
        List<Artista> artistaList = getArtistaList();
        artistaObservableList = FXCollections.observableArrayList();
        artistaObservableList.addAll(artistaList);
        return new ComboBox<>(artistaObservableList);
    }

    private ComboBox<Tecnica> createComboBoxTecnica() {
        List<Tecnica> tecnicaList = getTecnicaList();
        tecnicaObservableList = FXCollections.observableArrayList();
        tecnicaObservableList.addAll(tecnicaList);
        return new ComboBox<>(tecnicaObservableList);
    }

    private ComboBox<Movimento> createComboBoxMovimento() {
        List<Movimento> movimentoList = getMovimentoList();
        movimentoObservableList = FXCollections.observableArrayList();
        movimentoObservableList.addAll(movimentoList);
        return new ComboBox<>(movimentoObservableList);
    }

    private ComboBox<Materiais> createComboBoxMateriais() {
        List<Materiais> materiaisList = getMateriaisList();
        materiaisObservableList = FXCollections.observableArrayList();
        materiaisObservableList.addAll(materiaisList);
        return new ComboBox<>(materiaisObservableList);
    }

    private List<Artista> getArtistaList() {
        // Retrieve the list of artists from the model
        try {
            return new ExplorArtModel().getArtists();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Tecnica> getTecnicaList() {
        // Retrieve the list of techniques from the model
        try {
            return new ExplorArtModel().getTechnics();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Movimento> getMovimentoList() {
        // Retrieve the list of movements from the model
        try {
            return new ExplorArtModel().getMovement();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Materiais> getMateriaisList() {
        // Retrieve the list of materials from the model
        try {
            return new ExplorArtModel().getMaterials();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
