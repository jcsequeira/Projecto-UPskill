package controller.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Gson TypeAdapter for serializing and deserializing {@link LocalDate} objects.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Writes a {@link LocalDate} object to JSON as a formatted string.
     *
     * @param jsonWriter The JSON writer.
     * @param date       The {@link LocalDate} object to write.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void write(JsonWriter jsonWriter, LocalDate date) throws IOException {
        // Write LocalDate as a formatted string
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(formatter.format(date));
        }
    }

    /**
     * Reads a {@link LocalDate} object from a JSON string.
     *
     * @param jsonReader The JSON reader.
     * @return The deserialized {@link LocalDate} object.
     * @throws IOException If an I/O error occurs.
     */
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
