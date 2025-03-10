package commands.type.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Вспомогательный класс для записи объекта класса LocalDateTime в формате json и обратно
 */
public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    /**
     * Формат записи даты
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss");

    /**
     * Сериализация объекта
     *
     * @param localDateTime            - объект
     * @param type                     - тип
     * @param jsonSerializationContext - контекст
     * @return возвращает JsonElement, соответствующий объекту
     */
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }

    /**
     * Десериализация объекта
     *
     * @param jsonElement                - json элемент
     * @param type                       - тип
     * @param jsonDeserializationContext - контекст
     * @return возвращает объект Coordinates
     * @throws JsonParseException - ошибка парсинга
     */
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(jsonElement.getAsString(), formatter);
    }
}
