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
import presenter.ExplorArtContract;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * The ExplorArtView class represents the main view of the ExplorArt application.
 * It extends BorderPane and implements the ExplorArtContract.View interface.
 * This class is responsible for displaying the user interface, including menus, lists, and images.
 *
 */

public class ExplorArtView extends BorderPane implements ExplorArtContract.View {
    private ListView<String> listView;

    private ExplorArtPresenter myPresenter;


    /**
     * Constructs an instance of ExplorArtView.
     * Initializes the presenter and calls the doLayout method to set up the user interface.
     */
    public ExplorArtView() {
        myPresenter = new ExplorArtPresenter(this, new ExplorArtModel());

        doLayout();
    }

    /**
     * Sets up the user interface components and their layout.
     * Creates and initializes controls, menus, and layout elements.
     */
    private void doLayout() {
        // Set internal margin for the panel
        setPadding(new Insets(0, 30, 30, 30));


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
        setMargin(bottom, new Insets(10, 0, 0, 0));
        bottom.setAlignment(Pos.CENTER);
        ProgressBar progressbar = new ProgressBar();
        progressbar.setPadding(new Insets(10, 10, 10, 10));
        progressbar.setProgress(0.5);
        bottom.getChildren().addAll(imageView, progressbar);
        setBottom(bottom);
    }

    /**
     * Creates and configures the menu bar with various menus and menu items.
     *
     * @return The configured MenuBar.
     */
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

        explorarMenu.getItems().addAll(pesquisarItem, visualizarOAItem, visualizarArtistasItem, visualizarGaleriasItem,
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
        //MenuItem removerGaleristaItem = new MenuItem("Remover Galerista");
        MenuItem visualizarGaleristasItem = new MenuItem("Visualizar Galeristas");

        gerirGaleristasMenu.getItems().addAll(adicionarGaleristaItem, modificarGaleristaItem, visualizarGaleristasItem);

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
        adminMenu.getItems().addAll(popularBdItem, limparBdItem, addColaboradorItem, visualizarColaboradoresItem, modificarColaboradorItem);

        //--------------------------------------------------------------------------------------------------------------


        /**
         * Configures and sets up event handlers for various menu items in the ExplorArtView class.
         * Handles actions related to exploring, managing artworks, artists, events, galleries, and administrative tasks.
         */
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
        ativarOAItem.setOnAction(event -> {
            try {
                myPresenter.doAtivateArtwork();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        desativarOAItem.setOnAction(event -> {
            try {
                myPresenter.doDeativateArtwork();
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
        modificarEventoItem.setOnAction(event -> {
            try {
                myPresenter.doUpdateShow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        removerEventoItem.setOnAction(event -> {
            try {
                myPresenter.doRemoveShow();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        //--------------------------------------------------------------------------------------------------------------
        //Events menu Gerir Galeristas
        adicionarGaleristaItem.setOnAction(event -> {
            myPresenter.doAddGalerist();
        });
        modificarGaleristaItem.setOnAction(event -> {
            try {
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
        modificarGaleriaItem.setOnAction(event -> {
            try {
                myPresenter.doUpdateGallery();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        removerGaleriaItem.setOnAction(event -> {
            try {
                myPresenter.doRemoveGallery();
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
                doLimparBD();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });


        //--------------------------------------------------------------------------------------------------------------
        //Events- Menu Ajuda
        aboutItem.setOnAction(event -> showAbout());
        exitItem.setOnAction(event -> exitApplication());

        menuBar.getMenus().addAll(explorarMenu, gerirOAMenu, gerirArtistasMenu, gerirEventosMenu,
                gerirGaleristasMenu, gerirGaleriasMenu, adminMenu, helpMenu);

        return menuBar;
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Mostrar Listas - Menu Explorar *******

    /**
     * Displays a list of artists in the application.
     *
     * @param artistas The list of artists to be displayed.
     */
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

    /**
     * Displays a list of artworks in the application.
     *
     * @param obras The list of artworks to be displayed.
     */
    @Override
    public void showArtworks(List<Obra_Arte> obras) {
        if (obras.isEmpty()) {
            showEmptyListMessage("Obras de Arte");
        } else {
            listView.getItems().clear();
            for (Obra_Arte obraArte : obras) {
                if (obraArte.getIsActive() == 1) listView.getItems().add(obraArte.getTitulo());
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

    /**
     * Displays a list of events in the application.
     *
     * @param eventos The list of events to be displayed.
     */
    @Override
    public void showEvents(List<Evento> eventos) {
        if (eventos.isEmpty()) {
            showEmptyListMessage("Eventos");
        } else {
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

    /**
     * Displays a list of galleries in the application.
     *
     * @param galerias The list of galleries to be displayed.
     */
    @Override
    public void showGalleries(List<Galeria> galerias) {
        if (galerias.isEmpty()) {
            showEmptyListMessage("Galerias");
        } else {
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

    /**
     * Displays a message indicating that the list of a specific entity type is empty.
     *
     * @param entityType The type of entity for which the list is empty (e.g., "Artistas").
     */
    private void showEmptyListMessage(String entityType) {
        BorderPane emptyPane = new BorderPane();
        Label messageLabel = new Label("A lista de " + entityType + " está vazia.");
        emptyPane.setCenter(messageLabel);
        setCenter(emptyPane);
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Mostrar Detalhes - Menu Explorar *******

    /**
     * Displays details about a specific artist in a new stage.
     *
     * @param artista The artist for which details will be displayed.
     */
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

    /**
     * Displays details about a specific event and gallery in a new stage.
     *
     * @param evento  The event for which details will be displayed.
     * @param galeria The gallery associated with the event.
     */
    @Override
    public void showShowsDetails(Evento evento, Galeria galeria) {
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

    /**
     * Displays details about a specific artwork in a new stage.
     *
     * @param obraArte The artwork for which details will be displayed.
     * @param artista  The artist associated with the artwork.
     * @param tecnica  The technique used in the artwork.
     * @param movimento The artistic movement of the artwork.
     * @param material The materials used in the artwork.
     */
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

    /**
     * Displays details about a specific gallery in a new stage.
     *
     * @param galeria The gallery for which details will be displayed.
     * @throws IOException If there is an issue loading the gallery details.
     */
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

    /**
     * Displays the form to add a new artwork in a new stage.
     *
     * @param obraArte The artwork for which the form will be displayed.
     */
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

    /**
     * Displays a list of artworks for update and handles the selected artwork for updating.
     *
     * @param obras The list of artworks available for update.
     */
    @Override
    public void showUpdateArtworks(List<Obra_Arte> obras) {
        if (obras.isEmpty()) {
            showEmptyListMessage("Obra de Arte");
        } else {
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

    /**
     * Displays details about a specific artwork for updating in a new stage.
     *
     * @param obraArte The artwork for which details will be displayed for updating.
     * @param artista  The artist associated with the artwork.
     * @param tecnica  The technique used in the artwork.
     * @param movimento The artistic movement of the artwork.
     * @param material The materials used in the artwork.
     */
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

    /**
     * Displays a list of artworks for activation and handles the selected artwork for activation.
     *
     * @param obras The list of artworks available for activation.
     */
    @Override
    public void showAtivateArtwokrs(List<Obra_Arte> obras) {
        if (obras.isEmpty()) {
            showEmptyListMessage("Obra de Arte");
        } else {
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
                        myPresenter.doAtivateArtworkDetails(selectedArtwork.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }
    }

    /**
     * Displays details about a specific artwork for activation in a new stage.
     *
     * @param obraArte The artwork for which details will be displayed for activation.
     * @param artista  The artist associated with the artwork.
     * @param tecnica  The technique used in the artwork.
     * @param movimento The artistic movement of the artwork.
     * @param material The materials used in the artwork.
     */
    public void showAtivateArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {
        Stage artworkAtivateDetailsStage = new Stage();
        artworkAtivateDetailsStage.setTitle("Ativar Obra de Arte");

        Scene sceneArtworkAtivate = new Scene(new AtivateArtworkFormView(obraArte));

        artworkAtivateDetailsStage.setScene(sceneArtworkAtivate);
        artworkAtivateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        artworkAtivateDetailsStage.initOwner(this.getScene().getWindow());
        artworkAtivateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        artworkAtivateDetailsStage.sizeToScene();

        artworkAtivateDetailsStage.show();
    }

    /**
     * Displays a list of artworks for deactivation and handles the selected artwork for deactivation.
     *
     * @param obras The list of artworks available for deactivation.
     */
    @Override
    public void showDeativateArtwokrs(List<Obra_Arte> obras) {
        if (obras.isEmpty()) {
            showEmptyListMessage("Obra de Arte");
        } else {
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
                        myPresenter.doDeativateArtworkDetails(selectedArtwork.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }
    }

    /**
     * Displays details about a specific artwork for deactivation in a new stage.
     *
     * @param obraArte The artwork for which details will be displayed for deactivation.
     * @param artista  The artist associated with the artwork.
     * @param tecnica  The technique used in the artwork.
     * @param movimento The artistic movement of the artwork.
     * @param material The materials used in the artwork.
     */
    @Override
    public void showDeativateArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material) {
        Stage artworkDeativateDetailsStage = new Stage();
        artworkDeativateDetailsStage.setTitle("Desativar Obra de Arte");

        Scene sceneArtworkDeativate = new Scene(new DeativateArtworkFormView(obraArte));

        artworkDeativateDetailsStage.setScene(sceneArtworkDeativate);
        artworkDeativateDetailsStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        artworkDeativateDetailsStage.initOwner(this.getScene().getWindow());
        artworkDeativateDetailsStage.initModality(Modality.APPLICATION_MODAL);

        artworkDeativateDetailsStage.sizeToScene();

        artworkDeativateDetailsStage.show();
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Gerir Artistas *******

    /**
     * Displays the form to add a new artist in a new stage.
     *
     * @param artista The artist for which the form will be displayed.
     */
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

    /**
     * Displays a list of artists for update and handles the selected artist for updating.
     *
     * @param artistas The list of artists available for update.
     */
    @Override
    public void showUpdateArtist(List<Artista> artistas) {
        if (artistas.isEmpty()) {
            showEmptyListMessage("Artista");
        } else {
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

    /**
     * Displays details about a specific artist for updating in a new stage.
     *
     * @param artista The artist for which details will be displayed for updating.
     */
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

    /**
     * Displays a list of artists for removal and handles the selected artist for removal.
     *
     * @param artistas The list of artists available for removal.
     */
    @Override
    public void showRemoveArtists(List<Artista> artistas) {
        if (artistas.isEmpty()) {
            showEmptyListMessage("Artista");
        } else {
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

    /**
     * Displays a confirmation window for removing a specific artist from the database.
     *
     * @param artista The artist for which the removal confirmation window will be displayed.
     */
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

    /**
     * Displays the form to add a new show in a new stage.
     *
     * @param evento The event for which the form will be displayed.
     */
    @Override
    public void showAddShowForm(Evento evento) {
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

    /**
     * Displays a list of shows for update and handles the selected show for updating.
     *
     * @param eventos The list of shows available for update.
     */
    @Override
    public void showUpdateShow(List<Evento> eventos) {
        if (eventos.isEmpty()) {
            showEmptyListMessage("Evento");
        } else {
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

    /**
     * Displays details about a specific show for updating in a new stage.
     *
     * @param evento The event for which details will be displayed for updating.
     * @param galeria The gallery associated with the event.
     */
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

    /**
     * Displays a list of shows for removal and handles the selected show for removal.
     *
     * @param eventos The list of shows available for removal.
     */
    @Override
    public void showRemoveShows(List<Evento> eventos) {
        if (eventos.isEmpty()) {
            showEmptyListMessage("Evento");
        } else {
            listView.getItems().clear();
            for (Evento evento : eventos) {
                listView.getItems().add(evento.getNome());
            }
            listView.setOnMouseClicked(event -> {
                String selectedEventoName = listView.getSelectionModel().getSelectedItem();
                if (selectedEventoName != null) {
                    // Find the corresponding Show object
                    Optional<Evento> selectedEvento = eventos.stream()
                            .filter(evento -> evento.getNome().equals(selectedEventoName))
                            .findFirst();
                    try {
                        myPresenter.doRemoveShowWindow(selectedEvento.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /**
     * Displays a confirmation window for removing a specific show from the database.
     *
     * @param evento The event for which the removal confirmation window will be displayed.
     * @throws IOException If an error occurs while displaying the removal window.
     */
    @Override
    public void showRemoveShowWindow(Evento evento) throws IOException {
        Stage eventoRemoveStage = new Stage();
        eventoRemoveStage.setTitle("Remover evento da Base de Dados");

        Scene sceneEventoRemove = new Scene(new RemoveEventoView(evento));

        eventoRemoveStage.setScene(sceneEventoRemove);
        eventoRemoveStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        eventoRemoveStage.initOwner(this.getScene().getWindow());
        eventoRemoveStage.initModality(Modality.APPLICATION_MODAL);

        eventoRemoveStage.sizeToScene();

        eventoRemoveStage.show();
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Galerista *******

    /**
     * Displays the form to add a new gallerist in a new stage.
     *
     * @param galerista The gallerist for which the form will be displayed.
     */
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

    /**
     * Displays a list of gallerists for update and handles the selected gallerist for updating.
     *
     * @param galeristas The list of gallerists available for update.
     */
    @Override
    public void showUpdateGallerist(List<Galerista> galeristas) {
        if (galeristas.isEmpty()) {
            showEmptyListMessage("Galerista");
        } else {
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

    /**
     * Displays details about a specific gallerist for updating in a new stage.
     *
     * @param galerista The gallerist for which details will be displayed for updating.
     */
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

    /**
     * Displays the form to add a new gallery in a new stage.
     *
     * @param galeria The gallery for which the form will be displayed.
     */
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

    /**
     * Displays a list of galleries for update and handles the selected gallery for updating.
     *
     * @param galerias The list of galleries available for update.
     */
    @Override
    public void showUpdateGallery(List<Galeria> galerias) {
        if (galerias.isEmpty()) {
            showEmptyListMessage("Galerias");
        } else {
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

    /**
     * Displays details about a specific gallery for updating in a new stage.
     *
     * @param galeria The gallery for which details will be displayed for updating.
     * @throws IOException If an error occurs while displaying the details.
     */
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

    /**
     * Displays a list of galleries for removal and handles the selected gallery for removal.
     *
     * @param galerias The list of galleries available for removal.
     */
    @Override
    public void showRemoveGalleries(List<Galeria> galerias) {
        if (galerias.isEmpty()) {
            showEmptyListMessage("Galeria");
        } else {
            listView.getItems().clear();
            for (Galeria galeria : galerias) {
                listView.getItems().add(galeria.getNome_Galeria());
            }
            listView.setOnMouseClicked(event -> {
                String selectedGaleriaName = listView.getSelectionModel().getSelectedItem();
                if (selectedGaleriaName != null) {
                    // Find the corresponding Show object
                    Optional<Galeria> selectedGaleria = galerias.stream()
                            .filter(galeria -> galeria.getNome_Galeria().equals(selectedGaleriaName))
                            .findFirst();
                    try {
                        myPresenter.doRemoveGalleryWindow(selectedGaleria.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /**
     * Displays a confirmation window for removing a specific gallery from the database.
     *
     * @param galeria The gallery for which the removal confirmation window will be displayed.
     * @throws IOException If an error occurs while displaying the removal window.
     */
    @Override
    public void showRemoveGalleryWindow(Galeria galeria) throws IOException {
        Stage galeriaRemoveStage = new Stage();
        galeriaRemoveStage.setTitle("Remover galeria da Base de Dados");

        Scene sceneGaleriaRemove = new Scene(new RemoveGaleriaView(galeria));

        galeriaRemoveStage.setScene(sceneGaleriaRemove);
        galeriaRemoveStage.setResizable(true);

        // Set the owner and modality to make it a modal dialog
        galeriaRemoveStage.initOwner(this.getScene().getWindow());
        galeriaRemoveStage.initModality(Modality.APPLICATION_MODAL);

        galeriaRemoveStage.sizeToScene();

        galeriaRemoveStage.show();
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Admin *******

    /**
     * Displays the form to add a new collaborator in a new stage.
     *
     * @param colaborador The collaborator for which the form will be displayed.
     */
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

    /**
     * Displays a list of collaborators for viewing and handles the selected collaborator for details.
     *
     * @param colaboradores The list of collaborators available for viewing.
     */
    @Override
    public void visualizarColaboradores(List<Colaborador> colaboradores) {
        if (colaboradores.isEmpty()) {
            showEmptyListMessage("Colaboradores");
        } else {
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

    /**
     * Displays details about a specific collaborator in a new stage.
     *
     * @param colaborador The collaborator for which details will be displayed.
     * @throws IOException If an error occurs while displaying the details window.
     */
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

    /**
     * Displays a list of collaborators for update and handles the selected collaborator for updating.
     *
     * @param colaboradores The list of collaborators available for update.
     */
    @Override
    public void showUpdateColab(List<Colaborador> colaboradores) {
        if (colaboradores.isEmpty()) {
            showEmptyListMessage("Colaboradores");
        } else {
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

    /**
     * Displays details about a specific collaborator for updating in a new stage.
     *
     * @param colaborador The collaborator for which details will be displayed for updating.
     */
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

    /**
     * Initiates the process of cleaning the database and displays a confirmation dialog.
     *
     * @throws IOException If an error occurs while displaying the confirmation window.
     */
    public void doLimparBD() throws IOException {
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
    }


    //------------------------------------------------------------------------------------------------------------------
    //******* Menu Ajuda *******

    /**
     * Exits the application after displaying a confirmation dialog.
     */
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

    /**
     * Displays information about the ExplorArt application in a new stage.
     */
    public void showAbout() {
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