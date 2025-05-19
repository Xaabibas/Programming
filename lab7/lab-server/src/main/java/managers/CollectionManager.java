package managers;

import moduls.Ticket;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CollectionManager {
    public static final Logger logger = Logger.getLogger("CollectionLogger");
    private ConcurrentHashMap<Long, Ticket> collection;
    private final LocalDateTime time;
    private final DataBaseManager dbManager;

    public CollectionManager(DataBaseManager dbManager) {
        this.time = LocalDateTime.now();
        this.dbManager = dbManager;
    }

    public ConcurrentHashMap<Long, Ticket> getCollection() {
        return collection;
    }

    public void loadCollection() throws SQLException{
        collection = dbManager.readCollection();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void sortByPrice() {
        this.collection = collection.entrySet().stream().sorted(Map.Entry.comparingByValue(Ticket::compareTo))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, ConcurrentHashMap::new)
                );
    }

    public void sortByName() {
        this.collection = collection.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Ticket::getName)))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, ConcurrentHashMap::new)
                );
    }

    public DataBaseManager getDbManager() {
        return dbManager;
    }

    public void clear(String user) throws SQLException {
        List<Long> removeSet = dbManager.clear(user);
        for (Long key : removeSet) {
            collection.remove(key);
        }
    }

    public void insert(Long key, Ticket ticket, String user) throws SQLException {
        dbManager.insert(key, ticket, user);
        collection.put(key, ticket);
    }

    public boolean update(Long key,  String user, Ticket ticket) throws SQLException {
        if (dbManager.update(key, user, ticket)) {
            collection.replace(key, ticket);
            return true;
        }
        return false;
    }

    public boolean removeByKey(Long key, String user) throws SQLException{
        if (dbManager.removeByKey(key, user)) {
            collection.remove(key);
            return true;
        }
        return false;
    }

    public void removeByKeySet(Set<Long> removeSet, String user) throws SQLException {
        List<Long> removeList = dbManager.removeByKeySet(removeSet, user);
        for (Long key : removeList) {
            collection.remove(key);
        }
    }
}
