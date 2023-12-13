package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import model.*;
import presenter.ExplorArtContract;
import presenter.ExplorArtPresenter;


import java.io.IOException;
import java.util.List;


public class AddArtworkFormView extends Parent {

    private ExplorArtPresenter myPresenter;
    private ComboBox<Artista> artistaComboBox;
    private ObservableList<Artista> artistaObservableList;
    private ComboBox<Tecnica> tecnicaComboBox;
    private ObservableList<Tecnica> tecnicaObservableList;
    private ComboBox<Movimento> movimentoComboBox;
    private ObservableList<Movimento> movimentoObservableList;
    private ComboBox<Materiais> materiaisComboBox;
    private ObservableList<Materiais> materiaisObservableList;

    private ExplorArtModel model;




    public AddArtworkFormView(Obra_Arte obraArte) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(),new ExplorArtModel());



        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Título
        Label titleLabel = new Label("Título:");
        TextField titleField = new TextField();
        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleField, 1, 0);

        // Link da Imagem
        Label imageLinkLabel = new Label("Link da Imagem:");
        TextField imageLinkField = new TextField();
        gridPane.add(imageLinkLabel, 0, 1);
        gridPane.add(imageLinkField, 1, 1);

        // Ano de Criação
        Label creationYearLabel = new Label("Ano de Criação:");
        DatePicker creationYearPicker = new DatePicker();
        gridPane.add(creationYearLabel, 0, 2);
        gridPane.add(creationYearPicker, 1, 2);

        // Preço
        Label priceLabel = new Label("Preço:");
        TextField priceField = new TextField();
        gridPane.add(priceLabel, 0, 3);
        gridPane.add(priceField, 1, 3);

        // Altura
        Label heightLabel = new Label("Altura:");
        TextField heightField = new TextField();
        gridPane.add(heightLabel, 0, 4);
        gridPane.add(heightField, 1, 4);

// Largura
        Label widthLabel = new Label("Largura:");
        TextField widthField = new TextField();
        gridPane.add(widthLabel, 0, 5);
        gridPane.add(widthField, 1, 5);

// Profundidade
        Label depthLabel = new Label("Profundidade:");
        TextField depthField = new TextField();
        gridPane.add(depthLabel, 0, 6);
        gridPane.add(depthField, 1, 6);

// Diâmetro
        Label diameterLabel = new Label("Diâmetro:");
        TextField diameterField = new TextField();
        gridPane.add(diameterLabel, 0, 7);
        gridPane.add(diameterField, 1, 7);

// Ativo
        Label activeLabel = new Label("Ativo:");
        ToggleGroup activeToggleGroup = new ToggleGroup();

        ToggleButton yesButton = new ToggleButton("Sim");
        yesButton.setToggleGroup(activeToggleGroup);

        ToggleButton noButton = new ToggleButton("Não");
        noButton.setToggleGroup(activeToggleGroup);

        HBox activeBox = new HBox(yesButton, noButton);
        gridPane.add(activeLabel, 0, 8);
        gridPane.add(activeBox, 1, 8);

// ID do Artista
        Label artistIdLabel = new Label("Artista:");
        List<Artista> artistaList = null;
        ExplorArtModel modelaux = new ExplorArtModel();
        try {
            artistaList = modelaux.getArtists();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        artistaObservableList = FXCollections.observableArrayList();
        artistaObservableList.clear();
        artistaObservableList.addAll(artistaList);
        artistaComboBox = new ComboBox<>(artistaObservableList);
        gridPane.add(artistIdLabel, 0, 9);
        gridPane.add(artistaComboBox, 1, 9);

// ID da Técnica
        Label techniqueIdLabel = new Label("Técnica:");
        List<Tecnica> tecnicaList = null;
        model = new ExplorArtModel();
        try {
            tecnicaList = model.getTechnics();
        } catch (IOException e){
            throw new RuntimeException();
        }

        tecnicaObservableList = FXCollections.observableArrayList();
        tecnicaObservableList.clear();
        tecnicaObservableList.addAll(tecnicaList);
        tecnicaComboBox = new ComboBox<>(tecnicaObservableList);
        gridPane.add(techniqueIdLabel, 0, 10);
        gridPane.add(tecnicaComboBox, 1, 10);

// ID do Estilo
        Label styleIdLabel = new Label("Estilo:");
        List<Movimento> movimentosList = null;
        model = new ExplorArtModel();
        try {
            movimentosList = model.getMovement();
        } catch (IOException e){
            throw new RuntimeException();
        }

        movimentoObservableList = FXCollections.observableArrayList();
        movimentoObservableList.clear();
        movimentoObservableList.addAll(movimentosList);
        movimentoComboBox = new ComboBox<>(movimentoObservableList);
        gridPane.add(styleIdLabel, 0, 11);
        gridPane.add(movimentoComboBox, 1, 11);

        // ID do Material
        Label materialsIdLabel = new Label("Materiais:");
        List<Materiais> materiaisList = null;
        model = new ExplorArtModel();
        try {
            materiaisList = model.getMaterials();
        } catch (IOException e){
            throw new RuntimeException();
        }

        materiaisObservableList = FXCollections.observableArrayList();
        materiaisObservableList.clear();
        materiaisObservableList.addAll(materiaisList);
        materiaisComboBox = new ComboBox<>(materiaisObservableList);
        gridPane.add(materialsIdLabel, 0, 12);
        gridPane.add(materiaisComboBox, 1, 12);



        getChildren().add(gridPane);

        // Configure a lógica para salvar os dados quando o formulário for submetido
        Button submitButton = new Button("Adicionar Obra de Arte");
        submitButton.setOnAction(event -> {
            try {
                obraArte.setTitulo(titleField.getText());
                obraArte.setAno_Criacao(creationYearPicker.getValue());
                obraArte.setPreco(Float.parseFloat(priceField.getText())); // Convertendo a String do preço para float
                obraArte.setAltura(Float.parseFloat(heightField.getText()));
                obraArte.setLargura(Float.parseFloat(widthField.getText()));
                obraArte.setProfundidade(Float.parseFloat(depthField.getText()));
                obraArte.setDiametro(Float.parseFloat(diameterField.getText()));
                obraArte.setIsActive(yesButton.isSelected()?1:0);
                obraArte.setId_artista((int) artistaComboBox.getSelectionModel().getSelectedItem().getId_artista());
                obraArte.setId_Tecnica(tecnicaComboBox.getSelectionModel().getSelectedItem().getId_Tecnica());
                obraArte.setId_Estilo(movimentoComboBox.getSelectionModel().getSelectedItem().getId_Estilo());
                obraArte.setId_Material(materiaisComboBox.getSelectionModel().getSelectedItem().getId_Material());

                System.out.println(obraArte.getId_Estilo());
                System.out.println(obraArte.getId_Material());

                myPresenter.addArtwork(obraArte);


            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gridPane.add(submitButton, 0, 13, 2, 1);



    }


}
