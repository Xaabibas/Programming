package main;

import moduls.Ticket;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * Класс управления коллекцией
 */
public class CollectionManager {
    /**
     * Коллекция
     */
    private LinkedHashMap<Long, Ticket> collection;
    /**
     * Время создания
     */
    private final LocalDateTime time;

    /**
     * Менеджер файла
     */
    private final FileManager fm;

    /**
     * Конструктор
     */
    public CollectionManager() {
        this.fm = new FileManager();
        this.time = LocalDateTime.now();
    }

    /**
     * @return возвращает значение поля collection
     */
    public LinkedHashMap<Long, Ticket> getCollection() {
        return collection;
    }

    /**
     * @param collection - коллекция
     */
    public void setCollection(LinkedHashMap<Long, Ticket> collection) {
        this.collection = collection;
    }

    /**
     * @return возвращает значение поля time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @return возвращает значение поля fm
     */
    public FileManager getFm() {
        return fm;
    }
}
