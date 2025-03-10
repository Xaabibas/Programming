package commands.type.adapter;

import com.google.gson.*;
import moduls.Country;
import moduls.EyeColor;
import moduls.HairColor;
import moduls.Person;

import java.lang.reflect.Type;

/**
 * Вспомогательный класс для записи объекта класса Person в формате json и обратно
 */
public class PersonTypeAdapter implements JsonSerializer<Person>, JsonDeserializer<Person> {
    /**
     * Сериализация объекта
     *
     * @param person                   - объект
     * @param type                     - тип
     * @param jsonSerializationContext - контекст
     * @return возвращает JsonElement, соответствующий объекту
     */
    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("eyeColor", person.getEyeColor()==null ? null:person.getEyeColor().toString());
        result.addProperty("hairColor", person.getHairColor()==null ? null:person.getHairColor().toString());
        result.addProperty("nationality", person.getNationality()==null ? null:person.getNationality().toString());

        result.add("birthday", jsonSerializationContext.serialize(person.getBirthday()));

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
    public Person deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Person person = new Person();

        person.setHairColor(jsonObject.get("hairColor").isJsonNull() ? null:HairColor.valueOf(jsonObject.get("hairColor").getAsString()));
        person.setEyeColor(jsonObject.get("eyeColor").isJsonNull() ? null:EyeColor.valueOf(jsonObject.get("eyeColor").getAsString()));
        person.setNationality(jsonObject.get("nationality").isJsonNull() ? null:Country.valueOf(jsonObject.get("nationality").getAsString()));
        person.setBirthday(jsonObject.get("birthday").isJsonNull() ? null:new LocalDateTimeTypeAdapter().deserialize(jsonObject.get("birthday"), type, jsonDeserializationContext));

        return person;
    }
}
