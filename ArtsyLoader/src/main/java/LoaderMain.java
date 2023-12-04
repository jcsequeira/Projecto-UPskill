

import Utils.Utils;
import com.opencsv.exceptions.CsvException;
import controller.Controller;
import dataprocessorservice.DataProcessor;
import model.Cidade;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static controller.Controller.populateCidades;


public class LoaderMain {


    public static void main(String[] args) throws IOException, CsvException {

        //populateCidades();

        //Controller.populateArtistas();

        Map<Integer, String> cityMap = Utils.createCityMap(Utils.readCityNamesFromCsv("ArtsyLoader/src/main/resources/citysPT.csv"));
        System.out.println(cityMap);





    }

    }






