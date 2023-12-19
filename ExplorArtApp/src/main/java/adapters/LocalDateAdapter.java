package adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TypeAdapter for serializing and deserializing LocalDate objects to and from JSON using the specified date format.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    /**
     * The date format used for serialization and deserialization.
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Writes a LocalDate object to JSON using the specified date format.
     *
     * @param jsonWriter The JSON writer to which the LocalDate object will be written.
     * @param date       The LocalDate object to be written.
     * @throws IOException If an I/O error occurs while writing to the JSON writer.
     */
    @Override
    public void write(JsonWriter jsonWriter, LocalDate date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(formatter.format(date));
        }
    }

    /**
     * Reads a LocalDate object from JSON using the specified date format.
     *
     * @param jsonReader The JSON reader from which the LocalDate object will be read.
     * @return The deserialized LocalDate object.
     * @throws IOException If an I/O error occurs while reading from the JSON reader.
     */
    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        String dateString = jsonReader.nextString();
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else {
            return LocalDate.parse(dateString, formatter);
        }
    }
}

