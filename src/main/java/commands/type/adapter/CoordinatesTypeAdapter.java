package commands.type.adapter;

import com.google.gson.*;
import moduls.Coordinates;

import java.lang.reflect.Type;

/**
 * Вспомогательный класс для записи объекта класса Coordinates в формате json и обратно
 */
public class CoordinatesTypeAdapter implements JsonSerializer<Coordinates>, JsonDeserializer<Coordinates> {

    /**
     * Сериализация объекта
     *
     * @param coordinates              - объект
     * @param type                     - тип
     * @param jsonSerializationContext - контекст
     * @return возвращает JsonElement, соответствующий объекту
     */
    @Override
    public JsonElement serialize(Coordinates coordinates, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("x", coordinates.getX());
        result.addProperty("y", coordinates.getY());

        return result;
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
    public Coordinates deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Coordinates coordinates = new Coordinates();

        coordinates.setX(jsonObject.get("x").getAsFloat());
        coordinates.setY(jsonObject.get("y").getAsLong());

        return coordinates;
    }
}
