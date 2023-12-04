package dataprocessorservice;



import model.Cidade;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


    public static List<Cidade> cidadeListGenerator(Map<Integer, String> cityMap) {
        List<Cidade> cidadeList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : cityMap.entrySet()) {
            // Assuming id_Cidade starts from 1 and increments by 1
            int id_Cidade = entry.getKey();
            String nome_Cidade = entry.getValue();

            Cidade cidade = new Cidade(id_Cidade, nome_Cidade);
            cidadeList.add(cidade);
        }
        return cidadeList;
    }

}
