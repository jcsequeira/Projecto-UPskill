package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class ExplorArtView extends BorderPane {
    private TextField textExpression;

    public ExplorArtView(){
        doLayout();
    }

    private void doLayout() {
        // Set internal margin for the panel
        setPadding(new Insets(0,30,30, 30));

        // Initialization of controls (if needed)


        // Layout Top
        MenuBar menuBar = doMenuLayout();
        VBox top = new VBox();
        top.getChildren().add(menuBar);
        setMargin(top, new Insets(0, 0, 20, 0));
        setTop(top);
    }

    private MenuBar doMenuLayout() {
        MenuBar menuBar = new MenuBar();

        //Menu Explorar
        Menu explorarMenu = new Menu("Explorar");
        MenuItem pesquisarItem = new MenuItem("Pesquisar");
        MenuItem visualizarOAItem = new MenuItem("Visualizar Obras de Arte");
        MenuItem visualizarArtistasItem = new MenuItem("Visualizar Artistas");
        MenuItem visualizarEventosItem = new MenuItem("Visualizar Eventos");
        MenuItem visualizarSlideshowItem = new MenuItem("Visualizar Slideshow");

        //SeparatorMenuItem separatorExplorar = new SeparatorMenuItem();
        explorarMenu.getItems().addAll(pesquisarItem, visualizarOAItem, visualizarArtistasItem,
                visualizarEventosItem, visualizarSlideshowItem);


        //Menu Gerir Obras de Arte
        Menu gerirOAMenu = new Menu("Gerir Obras de Arte");
        MenuItem adicionarOAItem = new MenuItem("Adicionar Obra de Arte");
        MenuItem ativarOAItem = new MenuItem("Ativar Obra de Arte");
        MenuItem alterarOAItem = new MenuItem("Alterar Obra de Arte");
        MenuItem desativarOAItem = new MenuItem("Desativar Obra de Arte");

        gerirOAMenu.getItems().addAll(adicionarOAItem, ativarOAItem, alterarOAItem, desativarOAItem);


        //Menu Artistas
        Menu gerirArtistasMenu = new Menu("Gerir Artistas");
        MenuItem adicionarArtistaItem = new MenuItem("Adicionar Artista");
        MenuItem modificarArtistaItem = new MenuItem("Modificar Artista");
        MenuItem removerArtistaItem = new MenuItem("Remover Artista");

        gerirArtistasMenu.getItems().addAll(adicionarArtistaItem, modificarArtistaItem, removerArtistaItem);

        //Menu Gerir Eventos
        Menu gerirEventosMenu = new Menu("Gerir Eventos");
        MenuItem adicionarEventoItem = new MenuItem("Adicionar Evento");
        MenuItem modificarEventoItem = new MenuItem("Modificar Evento");
        MenuItem removerEventoItem = new MenuItem("Remover Evento");

        gerirEventosMenu.getItems().addAll(adicionarEventoItem, modificarEventoItem, removerEventoItem);

        //Menu Galeristas
        Menu gerirGaleristasMenu = new Menu("Gerir Galeristas");
        MenuItem adicionarGaleristaItem = new MenuItem("Adicionar Galerista");
        MenuItem modificarGaleristaItem = new MenuItem("Modificar Galerista");
        MenuItem removerGaleristaItem = new MenuItem("Remover Galerista");

        gerirGaleristasMenu.getItems().addAll(adicionarGaleristaItem, modificarGaleristaItem, removerGaleristaItem);

        //Menu Galerias
        Menu gerirGaleriasMenu = new Menu("Gerir Galerias");
        MenuItem adicionarGaleriaItem = new MenuItem("Adicionar Galeria");
        MenuItem modificarGaleriaItem = new MenuItem("Modificar Galeria");
        MenuItem removerGaleriaItem = new MenuItem("Remover Galeria");

        gerirGaleriasMenu.getItems().addAll(adicionarGaleriaItem, modificarGaleriaItem, removerGaleriaItem);

        //Menu Help
        Menu helpMenu = new Menu("Ajuda");
        MenuItem aboutItem = new MenuItem("Sobre ExplorArt");
        MenuItem exitItem = new MenuItem("Encerrar");

        helpMenu.getItems().addAll(aboutItem, exitItem);

        // Events
        aboutItem.setOnAction(event -> showAbout());
        exitItem.setOnAction(event -> exitApplication());


        menuBar.getMenus().addAll(explorarMenu, gerirOAMenu, gerirArtistasMenu, gerirEventosMenu,
                gerirGaleristasMenu, gerirGaleriasMenu, helpMenu);

        return menuBar;
    }

    private void exitApplication() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair da ExplorArt");
        alert.setHeaderText("Está prestes a encerrar a aplicação.");
        alert.setContentText("Deseja continuar?");

        alert.initOwner(this.getScene().getWindow());

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                Platform.exit(); // Ends UI thread, allowing other threads to finish gracefully
            } else {
                // User chose CANCEL or closed the dialog
                // Perform some action or display a message (e.g., show a message dialog)
                Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
                cancelAlert.setTitle("Encerramento cancelado");
                cancelAlert.setHeaderText("Encerramento abortado.");
                cancelAlert.showAndWait();
            }
        });
    }

    private void showAbout(){
        Stage aboutStage = new Stage();
        aboutStage.setTitle("Sobre ExplorArt");
        Scene scene = new Scene(new AboutView(), 400, 300);
        aboutStage.setScene(scene);
        aboutStage.setResizable(false);

        aboutStage.initOwner(this.getScene().getWindow());
        aboutStage.initModality(Modality.APPLICATION_MODAL);

        aboutStage.show();
    }



}