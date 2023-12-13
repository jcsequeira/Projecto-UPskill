package adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public void write(JsonWriter jsonWriter, LocalDate date) throws IOException {
        // Write LocalDate as a formatted string
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(formatter.format(date));
        }
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        // Read LocalDate from the JSON string
        String dateString = jsonReader.nextString();
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
           return null;
        } else {
            return LocalDate.parse(dateString, formatter);
        }
    }
}
