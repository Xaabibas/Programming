package commands.type.adapter;

import com.google.gson.*;
import moduls.Ticket;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Вспомогательный класс для создания коллекции из json-файла
 */
public class CollectionDeserializer implements JsonDeserializer<LinkedHashMap<Long, Ticket>> {
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
    public LinkedHashMap<Long, Ticket> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LinkedHashMap<Long, Ticket> result = new LinkedHashMap<>();

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            Ticket ticket = jsonDeserializationContext.deserialize(entry.getValue(), Ticket.class);
            result.put(Long.parseLong(entry.getKey()), ticket);
        }

        return result;
    }
}

