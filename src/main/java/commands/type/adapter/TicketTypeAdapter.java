package commands.type.adapter;

import com.google.gson.*;
import moduls.Ticket;
import moduls.TicketType;

import java.lang.reflect.Type;

/**
 * Вспомогательный класс для записи объекта класса Ticket в формате json и обратно
 */
public class TicketTypeAdapter implements JsonSerializer<Ticket>, JsonDeserializer<Ticket> {
    /**
     * Сериализация объекта
     *
     * @param ticket                   - объект
     * @param type                     - тип
     * @param jsonSerializationContext - контекст
     * @return возвращает JsonElement, соответствующий объекту
     */
    @Override
    public JsonElement serialize(Ticket ticket, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("name", ticket.getName());
        result.addProperty("price", ticket.getPrice());
        result.addProperty("type", ticket.getType()==null ? null:ticket.getType().toString());

        result.add("coordinates", jsonSerializationContext.serialize(ticket.getCoordinates()));
        result.add("creationDate", jsonSerializationContext.serialize(ticket.getCreationDate()));
        result.add("person", jsonSerializationContext.serialize(ticket.getPerson()));

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
    public Ticket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Ticket ticket = new Ticket();

        ticket.setName(jsonObject.get("name").getAsString());
        ticket.setPrice(jsonObject.get("price").getAsFloat());
        ticket.setType(jsonObject.get("type").isJsonNull() ? null:TicketType.valueOf(jsonObject.get("type").getAsString()));

        ticket.setPerson(jsonObject.get("person").isJsonNull() ? null:new PersonTypeAdapter().deserialize(jsonObject.get("person"), type, jsonDeserializationContext));
        ticket.setCoordinates(new CoordinatesTypeAdapter().deserialize(jsonObject.get("coordinates"), type, jsonDeserializationContext));
        ticket.setCreationDate(new LocalDateTimeTypeAdapter().deserialize(jsonObject.get("creationDate"), type, jsonDeserializationContext));

        return ticket;
    }
}
