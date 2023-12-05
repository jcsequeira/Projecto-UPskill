package dataprocessorservice;



import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import model.Cidade;
import model.Pais;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import static Utils.Utils.createCidadeList;


public class DataProcessor {


    //esta classe vai ter metodos para fazer as listas, a classe artistConverter apenas implementa a logica da conversao de um
    //objecto pra outro


        public static <K, T, E> List<E> listProcessor(List<K> inputList, Class<T> clazz) {
            List<E> resultList = new ArrayList<>();

            // Perform operations using the methods of the generic class
            for (K item : inputList) {
                // Assuming the generic class has a method named "process"
                E result = processItem(item, clazz);
                resultList.add(result);
            }

            return resultList;
        }

        public static <K, T, E> E processItem(K item, Class<T> clazz) {
            try {
                T instance = clazz.getDeclaredConstructor().newInstance();
                // Assuming the generic class has a method named "process"
                // You would need to adapt this to your specific use case
                Method method = clazz.getMethod("process", item.getClass());
                E result = (E) method.invoke(instance, item);
                // Replace the above lines with the appropriate reflection code for your case
                return result;
            } catch (Exception e) {
                // Handle exceptions appropriately
                e.printStackTrace();
                return null;
            }
        }






}
