package presenter;

import model.Artista;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ExplorArtPresenterTest {

    @org.junit.Before
    public void setUp() throws Exception {
        Artista artistaNew = new Artista();
        artistaNew.setNome_artista("NewArtist");
        artistaNew.setNacionalidade("Portuguese");
        artistaNew.setBiografia("Artist generated for testing");
        artistaNew.setData_Morte(LocalDate.of(2000,12,1));
        //artistaNew.setData_Nascimento();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void addArtist() {
    }
}