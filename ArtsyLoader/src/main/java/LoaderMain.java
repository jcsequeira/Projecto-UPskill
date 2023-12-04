

import com.opencsv.exceptions.CsvException;
import controller.Controller;

import java.io.IOException;

import static controller.Controller.populateCidades;


public class LoaderMain {


    public static void main(String[] args) throws IOException, CsvException {

        populateCidades();

        Controller.populateArtistas();






    }

    }






