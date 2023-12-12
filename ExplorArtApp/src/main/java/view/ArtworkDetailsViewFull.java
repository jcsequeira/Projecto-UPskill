package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ArtworkDetailsViewFull extends Parent {

    private Obra_Arte obraArte;
    private Artista artista;
    private Tecnica tecnica;
    private Movimento movimento;
    private Materiais material;

    public ArtworkDetailsViewFull(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {
        this.obraArte = obraArte;
        this.artista = artista;
        this.tecnica = tecnica;
        this.movimento = movimento;
        this.material = material;

        doLayout(obraArte);
    }

    private void doLayout(Obra_Arte obraArte) {
        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        //Display image
        ImageView imageView = new ImageView(new Image(obraArte.getLink_Imagem()));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(Region.USE_COMPUTED_SIZE);
        imageView.setFitHeight(Region.USE_COMPUTED_SIZE);


        // Display details using labels
        Label titleLabel = new Label("Título: " + obraArte.getTitulo());
        Label linkImagemLabel = new Label("Link Imagem: " + obraArte.getLink_Imagem());
        Label Ano_CriacaoLabel = new Label("Ano: " + formatDate(obraArte.getAno_Criacao()));
        Label precoLabel = new Label("Preço: " + obraArte.getPreco() + " $");
        Label alturaLabel = new Label("Altura: " + obraArte.getAltura() + " cm");
        Label larguraLabel = new Label("Largura: " + obraArte.getLargura() + " cm");
        Label profundidadeLabel = new Label("Profundidade: " + obraArte.getProfundidade() + " cm");
        Label diametroLabel = new Label("Diâmetro: " + obraArte.getDiametro() + " cm");
        Label zoomInLabel = new Label("(Carregue na imagem para ampliar)");

        Label artistaLabel = new Label("Autor: " + (artista != null ? artista.getNome_artista() : "<erro!>"));
        Label tecnicaLabel = new Label("Técnica: " + (tecnica != null ? tecnica.getTipo_Tecnica() : "<erro!>"));
        Label movimentoLabel = new Label("Movimento: " + (movimento != null ? movimento.getNome_Movimento() : "<erro!>"));
        Label materiaisLabel = new Label("Materiais: " + (material != null ? material.getTipo_Material() : "<erro!>"));
        // TODO logica do artista/tecnica/movimento/materiais




        // Add labels to the layout
        detailsLayout.getChildren().addAll(imageView, zoomInLabel, titleLabel, artistaLabel,linkImagemLabel,Ano_CriacaoLabel,
                precoLabel,alturaLabel,larguraLabel,profundidadeLabel,diametroLabel, tecnicaLabel, movimentoLabel, materiaisLabel);


        imageView.setOnMouseClicked(event -> zoomImage(this.obraArte));

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }

    // Zoom Image method
    private void zoomImage(Obra_Arte obraArte) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Zoom in");

        // Adicionar imagem da obra em tamanho grande
        Image image = new Image(obraArte.getLink_Imagem());
        ImageView imageView = new ImageView(image);

        // Definir tamanho real da imagem
        double realWidth = image.getWidth();
        double realHeight = image.getHeight();

        // Multiplicar largura e altura por 3
        imageView.setFitWidth(realWidth * 3);
        imageView.setFitHeight(realHeight * 3);

        // Configurar layout
        BorderPane imagemPane = new BorderPane();
        imagemPane.setCenter(imageView);

        // Configurar a cena
        Scene scene = new Scene(imagemPane, imageView.getFitWidth(), imageView.getFitHeight());
        stage.setScene(scene);

        // Exibir a janela
        stage.show();
    }



    private String formatDate(LocalDate date) {
        // Format the date using a DateTimeFormatter
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            return date.format(formatter);
        } else {
            return "N/A";
        }
    }
}
