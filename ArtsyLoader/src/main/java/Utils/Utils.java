package Utils;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;



import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import artsymodel.ArtsyArtist;
import model.Cidade;
import model.Pais;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    // Replace with the actual path

    //class com metodos para ler ficheiro ~CSVs com pais e nacionalidades, gera uma lista de objectos Pais
    //same com cidades
    //talvez materiais/tecncias e movimento





    public static List<Pais> paisesListGenerator(String csvFilePath, int enShortNameColumnIndex, int nationalityColumnIndex) {
        List<Pais> paisesList = new ArrayList<>();
        Map<Integer, String> countryMap = new HashMap<>();
        Map<Integer, String> nacionalityMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> records = reader.readAll();
            for (int i = 0; i < records.size(); i++) {
                String[] record = records.get(i);

                String enShortName = record[enShortNameColumnIndex];
                String nationality = record[nationalityColumnIndex];

                countryMap.put(i + 1, enShortName);
                nacionalityMap.put(i + 1, nationality);

                Pais pais = new Pais();
                pais.setNome_Pais(enShortName);
                pais.setNacionalidade(nationality);
                paisesList.add(pais);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return paisesList;   }



    public static List<Cidade> createCidadeList(String[] cityNames) {
        return IntStream.range(0, cityNames.length)
                .boxed()
                .map(i -> new Cidade(i + 1, cityNames[i]))
                .collect(Collectors.toList());
    }

    public static long countArtistsWithNullBirthday(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() == null)
                .count();
    }
}
