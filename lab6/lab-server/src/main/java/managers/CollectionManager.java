package managers;

import moduls.Ticket;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectionManager {
    public static final Logger logger = Logger.getLogger("CollectionLogger");
    private LinkedHashMap<Long, Ticket> collection;
    private final LocalDateTime time;
    private final FileManager fm;

    public CollectionManager() {
        this.fm = new FileManager();
        this.time = LocalDateTime.now();
    }

    public LinkedHashMap<Long, Ticket> getCollection() {
        return collection;
    }

    public void setCollection(LinkedHashMap<Long, Ticket> collection) {
        this.collection = collection;
    }

    public void setCollectionFromFile() {
        logger.info("Попытка загрузить коллекцию из файла");
        this.collection = fm.readCollection();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void sort() {
        this.collection = collection.entrySet().stream().sorted().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void sortByName() {
        this.collection = collection.entrySet().stream().sorted(Map.Entry.comparingByValue((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        })).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public FileManager getFm() {
        return fm;
    }
}
