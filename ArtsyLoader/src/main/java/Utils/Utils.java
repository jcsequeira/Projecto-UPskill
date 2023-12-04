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




    public static Map<Integer, String> nacionalityMapGeneratorFromCSV(String csvFilePath) {
        List<String> enShortNameList = new ArrayList<>();
        List<String> nationalityList = new ArrayList<>();

        Map<Integer, String> nacionalityMap = null;
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                // Assuming the last two fields are en_short_name and nationality
                // String enShortName = record[record.length - 2];
                String nationality = record[record.length - 1];

                // enShortNameList.add(enShortName);
                nationalityList.add(nationality);
                nacionalityMap = new HashMap<>();
                for (int i = 0; i < nationalityList.size(); i++) {
                    nacionalityMap.put(i + 1, nationalityList.get(i));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return nacionalityMap;
    }
    public static Map<Integer, String> countryMapGeneratorFromCSV(String csvFilePath) {
        List<String> enShortNameList = new ArrayList<>();
        Map<Integer, String> countryMap = null;
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                String enShortName = record[record.length - 2];
                enShortNameList.add(enShortName);
                countryMap = new HashMap<>();
                for (int i = 0; i < enShortNameList.size(); i++) {
                    countryMap.put(i + 1, enShortNameList.get(i));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return countryMap;
    }




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
