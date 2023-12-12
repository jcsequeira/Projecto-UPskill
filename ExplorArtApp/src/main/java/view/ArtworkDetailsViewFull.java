package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

        // Display details using labels
        Label titleLabel = new Label("Título: " + obraArte.getTitulo());
        Label linkImagemLabel = new Label("Link Imagem: " + obraArte.getLink_Imagem());
        Label Ano_CriacaoLabel = new Label("Data: " + formatDate(obraArte.getAno_Criacao()));
        Label precoLabel = new Label("Preço: " + obraArte.getPreco() + " $");
        Label alturaLabel = new Label("Altura: " + obraArte.getAltura() + " cm");
        Label larguraLabel = new Label("Largura: " + obraArte.getLargura() + " cm");
        Label profundidadeLabel = new Label("Profundidade: " + obraArte.getProfundidade() + " cm");
        Label diametroLabel = new Label("Diâmetro: " + obraArte.getDiametro() + " cm");

        Label artistaLabel = new Label("Autor: " + (artista != null ? artista.getNome_artista() : "<erro!>"));
        // TODO logica do artista/tecnica/movimento/materiais




        // Add labels to the layout
        detailsLayout.getChildren().addAll(titleLabel,linkImagemLabel,Ano_CriacaoLabel,precoLabel,alturaLabel,larguraLabel,profundidadeLabel,diametroLabel,
                artistaLabel);

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }


    private String formatDate(LocalDate date) {
        // Format the date using a DateTimeFormatter
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        } else {
            return "N/A";
        }
    }
}
