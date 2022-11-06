package reactor.utils;

import com.google.gson.*;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class SerializationUtils {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer())
            .setPrettyPrinting()
            .create();

    class ZonedDateTimeSerializer implements JsonSerializer< ZonedDateTime > {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");

        @Override
        public JsonElement serialize(ZonedDateTime zonedDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(zonedDateTime));
        }
    }
}
