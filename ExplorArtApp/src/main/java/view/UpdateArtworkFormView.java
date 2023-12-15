package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UpdateArtworkFormView extends Parent {

    private Obra_Arte obraArte;
    private Artista artista;
    private Tecnica tecnica;
    private Movimento movimento;
    private Materiais material;

    private ComboBox<Artista> artistaComboBox;
    private ObservableList<Artista> artistaObservableList;
    private ComboBox<Tecnica> tecnicaComboBox;
    private ObservableList<Tecnica> tecnicaObservableList;
    private ComboBox<Movimento> movimentoComboBox;
    private ObservableList<Movimento> movimentoObservableList;
    private ComboBox<Materiais> materiaisComboBox;
    private ObservableList<Materiais> materiaisObservableList;

    public UpdateArtworkFormView(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {
        this.obraArte = obraArte;
        this.artista = artista;
        this.tecnica = tecnica;
        this.movimento = movimento;
        this.material = material;

        doLayout(obraArte);
    }

    private void doLayout(Obra_Arte obraArte) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Título
        Label titleLabel = new Label("Títuloooooooooooooooooo:");
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

        // ... (Other fields)

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
        Button updateButton = new Button("Atualizar Obra de Arte");
        updateButton.setOnAction(event -> {
            try {
                // Update the artwork information here
                obraArte.setTitulo(titleField.getText());
                obraArte.setAno_Criacao(creationYearPicker.getValue());
                obraArte.setLink_Imagem(imageLinkField.getText());
                // ... (Update other fields)

                obraArte.setId_artista((int) artistaComboBox.getSelectionModel().getSelectedItem().getId_artista());
                obraArte.setId_Tecnica(tecnicaComboBox.getSelectionModel().getSelectedItem().getId_Tecnica());
                obraArte.setId_Estilo(movimentoComboBox.getSelectionModel().getSelectedItem().getId_Estilo());
                obraArte.setId_Material(materiaisComboBox.getSelectionModel().getSelectedItem().getId_Material());

                // Update the artwork using the presenter or model
                // myPresenter.updateArtwork(obraArte);

                // Close the window
                getScene().getWindow().hide();

            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
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
