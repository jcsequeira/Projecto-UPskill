package view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import presenter.*;



import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ExplorArtView extends BorderPane implements ExplorArtContract.View {
    private ListView<String> listView;

    private ExplorArtPresenter myPresenter;


    public ExplorArtView(){
        myPresenter = new ExplorArtPresenter(this, new ExplorArtModel());

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

        listView = new ListView<>();
        setCenter(listView);

        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResource("/ExplorArtLogo.jpg").toExternalForm());
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(100);
        VBox bottom = new VBox();
        setMargin(bottom, new Insets(10,0,0,0));
        bottom.setAlignment(Pos.CENTER);
        ProgressBar progressbar = new ProgressBar();
        progressbar.setPadding(new Insets(10,10,10,10));
        progressbar.setProgress(0.5);
        bottom.getChildren().addAll(imageView, progressbar);
        setBottom(bottom);
    }

    private MenuBar doMenuLayout() {
        MenuBar menuBar = new MenuBar();

        //Menu Explorar
        Menu explorarMenu = new Menu("Explorar");
        MenuItem pesquisarItem = new MenuItem("Pesquisar");
        MenuItem visualizarOAItem = new MenuItem("Visualizar Obras de Arte");
        MenuItem visualizarArtistasItem = new MenuItem("Visualizar Artistas");
        MenuItem visualizarGaleriasItem = new MenuItem("Visualizar Galerias");
        MenuItem visualizarEventosItem = new MenuItem("Visualizar Eventos");
        MenuItem visualizarSlideshowItem = new MenuItem("Visualizar Slideshow");

        explorarMenu.getItems().addAll(pesquisarItem, visualizarOAItem, visualizarArtistasItem,visualizarGaleriasItem,
                visualizarEventosItem, visualizarSlideshowItem);


        //Menu Gerir Obras de Arte
        Menu gerirOAMenu = new Menu("Gerir Obras de Arte");
        MenuItem adicionarOAItem = new MenuItem("Adicionar Obra de Arte");
        MenuItem ativarOAItem = new MenuItem("Ativar Obra de Arte");
        MenuItem modificarOAItem = new MenuItem("Modificar Obra de Arte");
        MenuItem desativarOAItem = new MenuItem("Desativar Obra de Arte");

        gerirOAMenu.getItems().addAll(adicionarOAItem, ativarOAItem, modificarOAItem, desativarOAItem);


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
        MenuItem visualizarGaleristasItem = new MenuItem("Visualizar Galeristas");

        gerirGaleristasMenu.getItems().addAll(adicionarGaleristaItem, modificarGaleristaItem, removerGaleristaItem, visualizarGaleristasItem);

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

        //Menu Admin
        Menu adminMenu = new Menu("Admin");
        MenuItem popularBdItem = new MenuItem("Importar Dados da Artsy API");
        MenuItem limparBdItem = new MenuItem("Limpar Dados da Artsy API");
        MenuItem addColaboradorItem = new MenuItem("Adicionar colaborador");
        MenuItem visualizarColaboradoresItem = new MenuItem("Visualizar colaboradores");
        MenuItem modificarColaboradorItem = new MenuItem("Modificar colaborador");
        adminMenu.getItems().addAll(popularBdItem,limparBdItem, addColaboradorItem, visualizarColaboradoresItem, modificarColaboradorItem);

        //--------------------------------------------------------------------------------------------------------------
        // Events
        //Events menu Exlporar
        visualizarOAItem.setOnAction(event -> {

            try {
                myPresenter.exploreArtworks();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        visualizarArtistasItem.setOnAction(event -> {
            try {
                myPresenter.exploreArtists();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        visualizarEventosItem.setOnAction(event -> {
            try {
                myPresenter.exploreEvents();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        visualizarGaleriasItem.setOnAction(event -> {
            try {
                myPresenter.exploreGalleries();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //TO DO slideshow

        //--------------------------------------------------------------------------------------------------------------
        //Events menu Gerir Obras de Arte
        adicionarOAItem.setOnAction(event -> {
            myPresenter.doAddArtwork();
        });
        modificarOAItem.setOnAction(event -> {
            try {
                myPresenter.doUpdateArtwork();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //Events menu Gerir Artistas
        adicionarArtistaItem.setOnAction(event -> {
            myPresenter.doAddArtist();
        });
        modificarArtistaItem.setOnAction(event -> {
            try {
                myPresenter.doUpdateArtist();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        removerArtistaItem.setOnAction(event -> {
            try {
                myPresenter.doRemoveArtist();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //Events menu Gerir Eventos
        adicionarEventoItem.setOnAction(event -> {
            myPresenter.doAddShow();
        });
        modificarEventoItem.setOnAction(event -> {try {
            myPresenter.doUpdateShow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        });

        //--------------------------------------------------------------------------------------------------------------
        //Events menu Gerir Galeristas
        adicionarGaleristaItem.setOnAction(event -> {
            myPresenter.doAddGalerist();
        });
        modificarGaleristaItem.setOnAction(event -> {try {
            myPresenter.doUpdateGallerist();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        });

        //--------------------------------------------------------------------------------------------------------------
        //Events menu Gerir Galerias
        adicionarGaleriaItem.setOnAction(event -> {
            myPresenter.doAddGaleria();
        });
        modificarGaleriaItem.setOnAction(event -> {try {
            myPresenter.doUpdateGallery();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        });

        //--------------------------------------------------------------------------------------------------------------
        //Events menu Admin
        addColaboradorItem.setOnAction(event -> {
            myPresenter.doAddColaborador();
        });
        popularBdItem.setOnAction(event -> {
           myPresenter.doImportDataFromArtsy();
        });
        visualizarColaboradoresItem.setOnAction(event -> {
           try {
            myPresenter.visualizarColaboradores();
        } catch (IOException e) {
               throw new RuntimeException(e);
           }
        });
        modificarColaboradorItem.setOnAction(event -> {
            try {
                myPresenter.doUpdateColab();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        limparBdItem.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Limpar Artsy Data");
                confirmationAlert.setHeaderText("Tem a certeza que deseja apagar todos os dados provenientes da Artsy API?");
                confirmationAlert.setContentText("Ao confirmar, serão eliminados os seguintes Items: Obras de Arte, Artistas, Eventos e Galerias da Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    myPresenter.deleteArtsyData();
                    // Se o utilizador confirmar, fecha a janela


                    Stage bdCleanConfirmation = new Stage();
                    bdCleanConfirmation.setTitle("Limpeza Efetuada");
                    Scene scene = new Scene(new CleanConfirmationView(), 400, 300);
                    bdCleanConfirmation.setScene(scene);
                    bdCleanConfirmation.setResizable(false);

                    bdCleanConfirmation.initOwner(this.getScene().getWindow());
                    bdCleanConfirmation.initModality(Modality.APPLICATION_MODAL);

                    bdCleanConfirmation.show();
                    
                }
            } catch (NumberFormatException e){
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });


        //--------------------------------------------------------------------------------------------------------------
        //Events- Menu Ajuda
        aboutItem.setOnAction(event -> showAbout());
        exitItem.setOnAction(event -> exitApplication());

        menuBar.getMenus().addAll(explorarMenu, gerirOAMenu, gerirArtistasMenu, gerirEventosMenu,
                gerirGaleristasMenu, gerirGaleriasMenu, adminMenu,helpMenu);

        return menuBar;
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Mostrar Listas - Menu Explorar *******
    @Override
    public void showArtists(List<Artista> artistas) {
        if (artistas.isEmpty()) {
            showEmptyListMessage("Artistas");
        } else {
            listView.getItems().clear();
            for (Artista artista : artistas) {
                listView.getItems().add(artista.getNome_artista());
            }

            // Add an event handler to handle item click
            listView.setOnMouseClicked(event -> {
                String selectedArtistName = listView.getSelectionModel().getSelectedItem();
                if (selectedArtistName != null) {
                    // Find the corresponding Artista object
                    Optional<Artista> selectedArtist = artistas.stream()
                            .filter(artista -> artista.getNome_artista().equals(selectedArtistName))
                            .findFirst();

                    // If the Artista object is found, display its information in a new window
                    //selectedArtist.ifPresent(this::showArtistDetails);
                    myPresenter.doArtistDetails(selectedArtist.get());
                }
            });
        }
    }

    @Override
    public void showArtworks(List<Obra_Arte> obras) {
        if (obras.isEmpty()){
            showEmptyListMessage("Obras de Arte");
        }
        else {
            listView.getItems().clear();
            for (Obra_Arte obraArte : obras) {
                if (obraArte.getIsActive()==1) listView.getItems().add(obraArte.getTitulo());
            }
            // Add an event handler to handle item click
            listView.setOnMouseClicked(event -> {
                String selectedArtworkTitle = listView.getSelectionModel().getSelectedItem();
                if (selectedArtworkTitle != null) {
                    // Find the corresponding Show object
                    Optional<Obra_Arte> selectedArtwork = obras.stream()
                            .filter(obraArte -> obraArte.getTitulo().equals(selectedArtworkTitle))
                            .findFirst();

                    try {
                        myPresenter.doArtworkDetails(selectedArtwork.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }

    }

    @Override
    public void showEvents(List<Evento> eventos) {
        if (eventos.isEmpty()){
            showEmptyListMessage("Eventos");
        }
        else {
            listView.getItems().clear();
            for (Evento evento : eventos) {
                listView.getItems().add(evento.getNome());
            }
            // Add an event handler to handle item click
            listView.setOnMouseClicked(event -> {
                String selectedShowName = listView.getSelectionModel().getSelectedItem();
                if (selectedShowName != null) {
                    // Find the corresponding Show object
                    Optional<Evento> selectedShow = eventos.stream()
                            .filter(evento -> evento.getNome().equals(selectedShowName))
                            .findFirst();

                    myPresenter.doShowDetails(selectedShow.get());
                }
            });
        }
    }

    @Override
    public void showGalleries(List<Galeria> galerias) {
        if (galerias.isEmpty()){
            showEmptyListMessage("Galerias");
        }
        else {
            listView.getItems().clear();
            for (Galeria galeria : galerias) {
                listView.getItems().add(galeria.getNome_Galeria());
            }
            // Add an event handler to handle item click
            listView.setOnMouseClicked(event -> {
                String selectedGalleryName = listView.getSelectionModel().getSelectedItem();
                if (selectedGalleryName != null) {
                    // Find the corresponding Show object
                    Optional<Galeria> selectedGallery = galerias.stream()
                            .filter(galeria -> galeria.getNome_Galeria().equals(selectedGalleryName))
                            .findFirst();

                    try {
                        myPresenter.doGalleryDetails(selectedGallery.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    @Override
    public void showGallerists(List<Galerista> galeristas) {
        // Lógica para exibir galeristas na interface gráfica
    }

    private void showEmptyListMessage(String entityType) {
        BorderPane emptyPane = new BorderPane();
        Label messageLabel = new Label("A lista de " + entityType + " está vazia.");
        emptyPane.setCenter(messageLabel);
        setCenter(emptyPane);
    }

    //------------------------------------------------------------------------------------------------------------------
    //******* Mostrar Detalhes - Menu Explorar *******
    public void showArtistDetails(Artista artista) {
        Stage showArtistFormStage = new Stage();
        showArtistFormStage.setTitle("Detalhes do Artista");

        // Create a new view or dialog to display the details of the Artista
        // You need to implement the details view (e.g., ArtistDetailsView) accordingly
        Scene scene = new Scene(new ArtistDetailsView(artista));

        showArtistFormStage.setScene(scene);
        showArtistFormStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        showArtistFormStage.initOwner(this.getScene().getWindow());
        showArtistFormStage.initModality(Modality.APPLICATION_MODAL);

        showArtistFormStage.sizeToScene();

        showArtistFormStage.show();
    }
    @Override
    public void showShowsDetails(Evento evento, Galeria galeria)  {
        Stage showDetailsStage = new Stage();
        showDetailsStage.setTitle("Detalhes do Evento");

        Scene scene = new Scene(new ShowDetailsViewWithGallery(evento, galeria));

        showDetailsStage.setScene(scene);
        showDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        showDetailsStage.initOwner(this.getScene().getWindow());
        showDetailsStage.initModality(Modality.APPLICATION_MODAL);

        showDetailsStage.sizeToScene();

        showDetailsStage.show();
    }

    @Override
    public void showArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {

        Stage artworkDetailsStage = new Stage();
        artworkDetailsStage.setTitle("Detalhes da Obra de Arte");


        Scene scene = new Scene(new ArtworkDetailsViewFull(obraArte, artista, tecnica, movimento, material));

        artworkDetailsStage.setScene(scene);
        artworkDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        artworkDetailsStage.initOwner(this.getScene().getWindow());
        artworkDetailsStage.initModality(Modality.APPLICATION_MODAL);

        artworkDetailsStage.sizeToScene();

        artworkDetailsStage.show();

    }
    @Override
    public void showGalleryDetails(Galeria galeria) throws IOException {
        Stage showGalleryDetailsStage = new Stage();
        showGalleryDetailsStage.setTitle("Detalhes da Galeria");

        Scene scene = new Scene(new GalleryDetailsView(galeria));

        showGalleryDetailsStage.setScene(scene);
        showGalleryDetailsStage.setResizable(true);

        showGalleryDetailsStage.initOwner(this.getScene().getWindow());
        showGalleryDetailsStage.initModality(Modality.APPLICATION_MODAL);

        showGalleryDetailsStage.sizeToScene();

        showGalleryDetailsStage.show();
    }



    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Gerir Obra Arte *******
    @Override
    public void showAddArtworkForm(Obra_Arte obraArte) {
        Stage addArtworkFormStage = new Stage();
        addArtworkFormStage.setTitle("Nova obra de arte");

        // Create a new view or dialog to display the details of the Art Work
        Scene scene = new Scene(new AddArtworkFormView(obraArte));

        addArtworkFormStage.setScene(scene);
        addArtworkFormStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        addArtworkFormStage.initOwner(this.getScene().getWindow());
        addArtworkFormStage.initModality(Modality.APPLICATION_MODAL);

        addArtworkFormStage.sizeToScene();

        addArtworkFormStage.show();
    }

    @Override
    public void showUpdateArtworks(List<Obra_Arte> obras) {
        if (obras.isEmpty()){
            showEmptyListMessage("Obra de Arte");
        }
        else {
            listView.getItems().clear();
            for (Obra_Arte obraArte : obras) {
                listView.getItems().add(obraArte.getTitulo());
            }
            // Add an event handler to handle item click
            listView.setOnMouseClicked(event -> {
                String selectedArtworkTitle = listView.getSelectionModel().getSelectedItem();
                if (selectedArtworkTitle != null) {
                    // Find the corresponding Show object
                    Optional<Obra_Arte> selectedArtwork = obras.stream()
                            .filter(obraArte -> obraArte.getTitulo().equals(selectedArtworkTitle))
                            .findFirst();

                    try {
                        myPresenter.doUpdateArtworkDetails(selectedArtwork.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }
    }

    @Override
    public void showUpdateArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {
        Stage artworkUpdateDetailsStage = new Stage();
        artworkUpdateDetailsStage.setTitle("Detalhes da Obra de Arte");


        Scene sceneArtworkUpdate = new Scene(new UpdateArtworkFormView(obraArte, artista, tecnica, movimento, material));

        artworkUpdateDetailsStage.setScene(sceneArtworkUpdate);
        artworkUpdateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        artworkUpdateDetailsStage.initOwner(this.getScene().getWindow());
        artworkUpdateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        artworkUpdateDetailsStage.sizeToScene();

        artworkUpdateDetailsStage.show();
    }



    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Gerir Artistas *******
    @Override
    public void showAddArtistForm(Artista artista) {
        Stage addArtistFormStage = new Stage();
        addArtistFormStage.setTitle("Novo artista");


        // Create a new view or dialog to display the details of the Artist
        Scene scene = new Scene(new AddArtistaFormView(artista));

        addArtistFormStage.setScene(scene);
        addArtistFormStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        addArtistFormStage.initOwner(this.getScene().getWindow());
        addArtistFormStage.initModality(Modality.APPLICATION_MODAL);

        addArtistFormStage.sizeToScene();

        addArtistFormStage.show();
    }

    @Override
    public void showUpdateArtist(List<Artista> artistas) {
        if (artistas.isEmpty()){
            showEmptyListMessage("Artista");
        }
        else {
            listView.getItems().clear();
            for (Artista artista : artistas) {
                listView.getItems().add(artista.getNome_artista());
            }
            listView.setOnMouseClicked(event -> {
                String selectedArtistName = listView.getSelectionModel().getSelectedItem();
                if (selectedArtistName != null) {
                    // Find the corresponding Show object
                    Optional<Artista> selectedArtista = artistas.stream()
                            .filter(artista -> artista.getNome_artista().equals(selectedArtistName))
                            .findFirst();

                    try {
                        myPresenter.doUpdateArtistDetails(selectedArtista.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    @Override
    public void showUpdateArtistDetails(Artista artista) {
        Stage artistaUpdateDetailsStage = new Stage();
        artistaUpdateDetailsStage.setTitle("Detalhes do artista");

        Scene sceneArtistaUpdate = new Scene(new UpdateArtistaFormView(artista));

        artistaUpdateDetailsStage.setScene(sceneArtistaUpdate);
        artistaUpdateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        artistaUpdateDetailsStage.initOwner(this.getScene().getWindow());
        artistaUpdateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        artistaUpdateDetailsStage.sizeToScene();

        artistaUpdateDetailsStage.show();
    }

    @Override
    public void showRemoveArtists(List<Artista> artistas) {
        if (artistas.isEmpty()){
            showEmptyListMessage("Artista");
        }
        else {
            listView.getItems().clear();
            for (Artista artista : artistas) {
                listView.getItems().add(artista.getNome_artista());
            }
            listView.setOnMouseClicked(event -> {
                String selectedArtistName = listView.getSelectionModel().getSelectedItem();
                if (selectedArtistName != null) {
                    // Find the corresponding Show object
                    Optional<Artista> selectedArtista = artistas.stream()
                            .filter(artista -> artista.getNome_artista().equals(selectedArtistName))
                            .findFirst();

                    try {
                        myPresenter.doRemoveArtistWindow(selectedArtista.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    @Override
    public void showRemoveArtistWindow(Artista artista) {
        Stage artistaRemoveStage = new Stage();
        artistaRemoveStage.setTitle("Remover artista da Base de Dados");

        Scene sceneArtistaRemove = new Scene(new RemoveArtistaView(artista));

        artistaRemoveStage.setScene(sceneArtistaRemove);
        artistaRemoveStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        artistaRemoveStage.initOwner(this.getScene().getWindow());
        artistaRemoveStage.initModality(Modality.APPLICATION_MODAL);

        artistaRemoveStage.sizeToScene();

        artistaRemoveStage.show();
    }

    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Gerir Eventos *******
    @Override
    public void showAddShowForm(Evento evento){
        Stage addShowFormStage = new Stage();
        addShowFormStage.setTitle("Novo evento");


        // Create a new view or dialog to display the details of the Artist
        Scene scene = new Scene(new AddShowFormView(evento));

        addShowFormStage.setScene(scene);
        addShowFormStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        addShowFormStage.initOwner(this.getScene().getWindow());
        addShowFormStage.initModality(Modality.APPLICATION_MODAL);

        addShowFormStage.sizeToScene();

        addShowFormStage.show();
    }

    @Override
    public void showUpdateShow(List<Evento> eventos) {
        if (eventos.isEmpty()){
            showEmptyListMessage("Evento");
        }
        else {
            listView.getItems().clear();
            for (Evento evento : eventos) {
                listView.getItems().add(evento.getNome());
            }
            listView.setOnMouseClicked(event -> {
                String selectedShowName = listView.getSelectionModel().getSelectedItem();
                if (selectedShowName != null) {
                    // Find the corresponding Show object
                    Optional<Evento> selectedEvento = eventos.stream()
                            .filter(evento -> evento.getNome().equals(selectedShowName))
                            .findFirst();

                    try {
                        myPresenter.doUpdateShowDetails(selectedEvento.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    @Override
    public void showUpdateShowDetails(Evento evento, Galeria galeria) {
        Stage eventoUpdateDetailsStage = new Stage();
        eventoUpdateDetailsStage.setTitle("Detalhes do evento");


        Scene sceneEventoUpdate = new Scene(new UpdateEventoFormView(evento, galeria));

        eventoUpdateDetailsStage.setScene(sceneEventoUpdate);
        eventoUpdateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        eventoUpdateDetailsStage.initOwner(this.getScene().getWindow());
        eventoUpdateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        eventoUpdateDetailsStage.sizeToScene();

        eventoUpdateDetailsStage.show();
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Galerista *******
    @Override
    public void showAddGalleristForm(Galerista galerista) {
        Stage addGalleristFormStage = new Stage();
        addGalleristFormStage.setTitle("Novo galerista");


        // Create a new view or dialog to display the details of the Artist
        Scene scene = new Scene(new AddGalleristFormView(galerista));

        addGalleristFormStage.setScene(scene);
        addGalleristFormStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        addGalleristFormStage.initOwner(this.getScene().getWindow());
        addGalleristFormStage.initModality(Modality.APPLICATION_MODAL);

        addGalleristFormStage.sizeToScene();

        addGalleristFormStage.show();
    }

    @Override
    public void showUpdateGallerist(List<Galerista> galeristas) {
        if (galeristas.isEmpty()){
            showEmptyListMessage("Galerista");
        }
        else {
            listView.getItems().clear();
            for (Galerista galerista : galeristas) {
                listView.getItems().add(galerista.toString());
            }
            listView.setOnMouseClicked(event -> {
                String selectedShowName = listView.getSelectionModel().getSelectedItem();
                if (selectedShowName != null) {
                    // Find the corresponding Show object
                    Optional<Galerista> selectedGalerista = galeristas.stream()
                            .filter(galerista -> galerista.toString().equals(selectedShowName))
                            .findFirst();

                    try {
                        myPresenter.doUpdateGalleristDetails(selectedGalerista.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    @Override
    public void showUpdateGalleristDetails(Galerista galerista) {
        Stage galeristaUpdateDetailsStage = new Stage();
        galeristaUpdateDetailsStage.setTitle("Detalhes do galerista");


        Scene sceneGaleristaUpdate = new Scene(new UpdateGaleristaFormView(galerista));

        galeristaUpdateDetailsStage.setScene(sceneGaleristaUpdate);
        galeristaUpdateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        galeristaUpdateDetailsStage.initOwner(this.getScene().getWindow());
        galeristaUpdateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        galeristaUpdateDetailsStage.sizeToScene();

        galeristaUpdateDetailsStage.show();
    }



    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Gerir Galeria *******
    @Override
    public void showAddGalleryForm(Galeria galeria) {
        Stage addGalleryFormStage = new Stage();
        addGalleryFormStage.setTitle("Nova galeria");


        // Create a new view or dialog to display the details of the Artist
        Scene scene = new Scene(new AddGalleryFormView(galeria));

        addGalleryFormStage.setScene(scene);
        addGalleryFormStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        addGalleryFormStage.initOwner(this.getScene().getWindow());
        addGalleryFormStage.initModality(Modality.APPLICATION_MODAL);

        addGalleryFormStage.sizeToScene();

        addGalleryFormStage.show();
    }

    @Override
    public void showUpdateGallery(List<Galeria> galerias) {
        if (galerias.isEmpty()){
            showEmptyListMessage("Galerias");
        }
        else {
            listView.getItems().clear();
            for (Galeria galeria : galerias) {
                listView.getItems().add(galeria.toString());
            }
            listView.setOnMouseClicked(event -> {
                String selectedShowName = listView.getSelectionModel().getSelectedItem();
                if (selectedShowName != null) {
                    // Find the corresponding Show object
                    Optional<Galeria> selectedGaleria = galerias.stream()
                            .filter(galeria -> galeria.toString().equals(selectedShowName))
                            .findFirst();

                    try {
                        myPresenter.doUpdateGalleryDetails(selectedGaleria.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    @Override
    public void showUpdateGalleryDetails(Galeria galeria) throws IOException {
        Stage galleryUpdateDetailsStage = new Stage();
        galleryUpdateDetailsStage.setTitle("Detalhes da galeria");


        Scene sceneGalleryUpdate = new Scene(new UpdateGaleriaFormView(galeria));

        galleryUpdateDetailsStage.setScene(sceneGalleryUpdate);
        galleryUpdateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        galleryUpdateDetailsStage.initOwner(this.getScene().getWindow());
        galleryUpdateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        galleryUpdateDetailsStage.sizeToScene();

        galleryUpdateDetailsStage.show();
    }



    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Admin *******
    @Override
    public void showAddColaboradorForm(Colaborador colaborador) {
        Stage addColaboradorFormStage = new Stage();
        addColaboradorFormStage.setTitle("Novo colaborador");


        // Create a new view or dialog to display the details of the Artist
        Scene scene = new Scene(new AddColaboradorFormView(colaborador));

        addColaboradorFormStage.setScene(scene);
        addColaboradorFormStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        addColaboradorFormStage.initOwner(this.getScene().getWindow());
        addColaboradorFormStage.initModality(Modality.APPLICATION_MODAL);

        addColaboradorFormStage.sizeToScene();

        addColaboradorFormStage.show();
    }

    @Override
    public void visualizarColaboradores(List<Colaborador> colaboradores) {
        if (colaboradores.isEmpty()){
            showEmptyListMessage("Colaboradores");
        }
        else {
            listView.getItems().clear();
            for (Colaborador colaborador : colaboradores) {
                listView.getItems().add(colaborador.getNome_Colaborador());
            }
            // Add an event handler to handle item click
            listView.setOnMouseClicked(event -> {
                String selectedColaboradorName = listView.getSelectionModel().getSelectedItem();
                if (selectedColaboradorName != null) {
                    // Find the corresponding Show object
                    Optional<Colaborador> selectedColaborador = colaboradores.stream()
                            .filter(colaborador -> colaborador.getNome_Colaborador().equals(selectedColaboradorName))
                            .findFirst();

                    try {
                        myPresenter.doColaboradorDetails(selectedColaborador.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            });
        }
    }


    @Override
    public void showColaboradorDetails(Colaborador colaborador) throws IOException {
        Stage showColaboradorFormStage = new Stage();
        showColaboradorFormStage.setTitle("Detalhes do colaborador");

        Scene scene = new Scene(new ColaboradorDetailsView(colaborador));

        showColaboradorFormStage.setScene(scene);
        showColaboradorFormStage.setResizable(true);

        showColaboradorFormStage.initOwner(this.getScene().getWindow());
        showColaboradorFormStage.initModality(Modality.APPLICATION_MODAL);

        showColaboradorFormStage.sizeToScene();

        showColaboradorFormStage.show();
    }

    @Override
    public void showUpdateColab(List<Colaborador> colaboradores) {
        if (colaboradores.isEmpty()){
            showEmptyListMessage("Colaboradores");
        }
        else {
            listView.getItems().clear();
            for (Colaborador colaborador : colaboradores) {
                listView.getItems().add(colaborador.toString());
            }
            listView.setOnMouseClicked(event -> {
                String selectedShowName = listView.getSelectionModel().getSelectedItem();
                if (selectedShowName != null) {
                    // Find the corresponding Show object
                    Optional<Colaborador> selectedcolaborador = colaboradores.stream()
                            .filter(colaborador -> colaborador.toString().equals(selectedShowName))
                            .findFirst();

                    myPresenter.doUpdateColabDetails(selectedcolaborador.get());
                }
            });
        }
    }

    @Override
    public void showUpdateColabDetails(Colaborador colaborador) {
        Stage colabUpdateDetailsStage = new Stage();
        colabUpdateDetailsStage.setTitle("Detalhes do colaborador");


        Scene sceneColabUpdate = new Scene(new UpdateColabFormView(colaborador));

        colabUpdateDetailsStage.setScene(sceneColabUpdate);
        colabUpdateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        colabUpdateDetailsStage.initOwner(this.getScene().getWindow());
        colabUpdateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        colabUpdateDetailsStage.sizeToScene();

        colabUpdateDetailsStage.show();
    }




    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Ajuda *******
    public void exitApplication() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair da ExplorArt");
        alert.setHeaderText("Está prestes a encerrar a aplicação.");
        alert.setContentText("Deseja continuar?");

        alert.initOwner(this.getScene().getWindow());

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                //Platform.exit(); // Ends UI thread, allowing other threads to finish gracefully
                System.exit(0);
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

    public void showAbout(){
        Stage bdCleanConfirmation = new Stage();
        bdCleanConfirmation.setTitle("Sobre ExplorArt");
        Scene scene = new Scene(new AboutView(), 400, 300);
        bdCleanConfirmation.setScene(scene);
        bdCleanConfirmation.setResizable(false);

        bdCleanConfirmation.initOwner(this.getScene().getWindow());
        bdCleanConfirmation.initModality(Modality.APPLICATION_MODAL);

        bdCleanConfirmation.show();
    }



    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}