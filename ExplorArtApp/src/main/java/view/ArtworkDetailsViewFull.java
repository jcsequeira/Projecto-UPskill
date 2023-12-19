/**
 * The ArtworkDetailsViewFull class represents a detailed view for displaying information about an artwork.
 * It includes details such as the title, image, creation year, price, dimensions, artist, technique, movement,
 * materials, and provides the ability to zoom in on the artwork's image.
 */
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

    /**
     * Constructs an instance of ArtworkDetailsViewFull with the specified artwork details.
     *
     * @param obraArte  The artwork to be displayed.
     * @param artista   The artist associated with the artwork.
     * @param tecnica   The technique used for the artwork.
     * @param movimento The art movement associated with the artwork.
     * @param material  The materials used for the artwork.
     */
    public ArtworkDetailsViewFull(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {
        this.obraArte = obraArte;
        this.artista = artista;
        this.tecnica = tecnica;
        this.movimento = movimento;
        this.material = material;

        doLayout(obraArte);
    }

    /**
     * Configures the layout to display details about the artwork.
     *
     * @param obraArte The artwork whose details will be displayed.
     */
    private void doLayout(Obra_Arte obraArte) {
        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        ImageView imageView;
        Image image = new Image(getClass().getResource("/no_image.png").toExternalForm());

        // Display image
        try {
            imageView = new ImageView(new Image(obraArte.getLink_Imagem()));
        } catch (IllegalArgumentException | NullPointerException e) {
            imageView = new ImageView(image);
        }
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

        // Add labels to the layout
        detailsLayout.getChildren().addAll(imageView, zoomInLabel, titleLabel, artistaLabel, linkImagemLabel, Ano_CriacaoLabel,
                precoLabel, alturaLabel, larguraLabel, profundidadeLabel, diametroLabel, tecnicaLabel, movimentoLabel, materiaisLabel);

        // Zoom in on the image when clicked
        imageView.setOnMouseClicked(event -> zoomImage(this.obraArte));

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }

    /**
     * Zooms in on the image of the artwork, displaying it in a separate window.
     *
     * @param obraArte The artwork to zoom in on.
     */
    private void zoomImage(Obra_Arte obraArte) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Zoom in");

        // Add the artwork image in a larger size
        Image image = new Image(obraArte.getLink_Imagem());
        ImageView imageView = new ImageView(image);

        // Set the actual size of the image
        double realWidth = image.getWidth();
        double realHeight = image.getHeight();

        // Multiply width and height by 3
        imageView.setFitWidth(realWidth * 3);
        imageView.setFitHeight(realHeight * 3);

        // Configure layout
        BorderPane imagePane = new BorderPane();
        imagePane.setCenter(imageView);

        // Configure the scene
        Scene scene = new Scene(imagePane, imageView.getFitWidth(), imageView.getFitHeight());
        stage.setScene(scene);

        // Show the window
        stage.show();
    }

    /**
     * Formats the given LocalDate using a specific pattern.
     *
     * @param date The date to be formatted.
     * @return A formatted string representation of the date.
     */
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
