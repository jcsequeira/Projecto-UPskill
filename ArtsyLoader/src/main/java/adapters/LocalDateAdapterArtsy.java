package adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * A Gson {@link TypeAdapter} for serializing and deserializing {@link LocalDate} objects
 * based on the Artsy API's date formats.
 */
public class LocalDateAdapterArtsy extends TypeAdapter<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterShows = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * Writes a {@link LocalDate} object to JSON using the specified format.
     *
     * @param jsonWriter The JSON writer.
     * @param date       The {@link LocalDate} object to be written.
     * @throws IOException If an I/O error occurs during writing.
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
     * Reads a {@link LocalDate} object from JSON using the specified format.
     *
     * @param jsonReader The JSON reader.
     * @return The deserialized {@link LocalDate} object.
     * @throws IOException If an I/O error occurs during reading.
     */

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return LocalDate.of(9999,1,1);
        } else {
            String dateString = jsonReader.nextString().trim();
            if (dateString.isEmpty()) {
                return null;
            } else {
                return parseDate(dateString);
            }
        }
    }
    /**
     * Reads a {@link LocalDate} object from JSON using various parsing strategies.
     * <p>
     * If the date string is empty or cannot be parsed, returns {@code null}.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed {@link LocalDate} object or {@code null} if parsing fails.
     */
    private LocalDate parseDate(String dateString) {
        try {
            return parseWithOffsetDateTime(dateString);
        } catch (DateTimeParseException ignored) {
            // Continue with existing parsing strategies
        }

        try {
            return parseWithRangeFormatter(dateString);
        } catch (DateTimeParseException ignored) {
            // Ignore and proceed to the next parsing strategy
        }

        try {
            return parseWithCustomFormatter(dateString);
        } catch (DateTimeParseException ignored) {
            // Ignore and proceed to the next parsing strategy
        }

        try {
            String[] parts = dateString.split(" or ");
            return LocalDate.of(Integer.parseInt(parts[0]), 1, 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
            // Ignore and proceed to the next parsing strategy
        }

        try {
            String[] parts = dateString.split("/");
            return LocalDate.of(Integer.parseInt(parts[0]), 1, 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
            // Ignore and proceed to the next parsing strategy
        }

        try {
            String numericPart = dateString.replaceAll("[^0-9]", "");
            return LocalDate.parse(numericPart + "-01-01", formatter);
        } catch (DateTimeParseException ignored) {
            return null;
        }
    }

    /**
     * Parses a date string using a specific range formatter.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed {@link LocalDate} object.
     */
    private LocalDate parseWithRangeFormatter(String dateString) {
        DateTimeFormatter rangeFormatter = DateTimeFormatter.ofPattern("'ca.' yyyy/MM", Locale.ENGLISH);
        YearMonth yearMonth = YearMonth.parse(dateString, rangeFormatter);
        return yearMonth.atDay(1);
    }

    /**
     * Parses a date string using a custom formatter.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed {@link LocalDate} object.
     */
    private LocalDate parseWithCustomFormatter(String dateString) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        return LocalDate.parse(dateString, customFormatter);
    }

    /**
     * Parses a date string using an {@link OffsetDateTime} formatter.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed {@link LocalDate} object.
     */
    private LocalDate parseWithOffsetDateTime(String dateString) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatterShows);
        return offsetDateTime.toLocalDate();
    }
}