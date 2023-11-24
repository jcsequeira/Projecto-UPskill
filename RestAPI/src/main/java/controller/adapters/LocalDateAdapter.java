package controller.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDate date) throws IOException {
        //Gerar data
        if (date == null){
            jsonWriter.value("n/a");
        } else {
            String dateString = String.format("%04d/%02d/%02d",
                    date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            jsonWriter.value(dateString);
        }
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
    //Por fazer
        return LocalDate.of(3000,01,01);
    }
}

