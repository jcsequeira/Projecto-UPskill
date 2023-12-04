package Utils;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;



import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import artsymodel.ArtsyArtist;

import java.util.List;

public class Utils {

     // Replace with the actual path

    //class com metodos para ler ficheiro ~CSVs com pais e nacionalidades, gera uma lista de objectos Pais
    //same com cidades
    //talvez materiais/tecncias e movimento






        public static Map<Integer, String> createCityMapFromCSV(String csvFilePath) throws IOException {
            try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
                String[] cityNames = csvReader.readAll().stream().flatMap(line -> Arrays.stream(line)).toArray(String[]::new);
                return createCityMap(cityNames);
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        }

        private static Map<Integer, String> createCityMap(String[] cityNames) {
            Map<Integer, String> cityMap = new HashMap<>();
            for (int i = 0; i < cityNames.length; i++) {
                cityMap.put(i + 1, cityNames[i]);
            }
            return cityMap;
        }

    public static long countArtistsWithNullBirthday(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() == null)
                .count();
    }
}
