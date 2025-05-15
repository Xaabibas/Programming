package managers;

import moduls.*;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class DataBaseManager {
    // ssh -p 2222 s467898@se.ifmo.ru -Y -L 5432:helios:5432
    public final Logger logger = Logger.getLogger("DBLogger");
    private final String salt = "3Hge&3";

    public DataBaseManager() {
    }

    public Connection connect() {
        Connection connection;
        try {
            logger.info("Try to connect to DB");
            String url = "jdbc:postgresql://localhost:5432/studs";
            Properties info = new Properties();
            info.load(new FileReader("db.cfg"));

            connection = DriverManager.getConnection(url, info);
            logger.info("Successful connection to DB");
            return connection;
        } catch (IOException e) {
            logger.severe("Config file haven't been found");
            System.exit(1);
        } catch (SQLException e) {
            logger.severe("Couldn't connect to DB");
            System.exit(1);
        }
        return null;
    }

    public ConcurrentHashMap<Long, Ticket> readCollection() throws SQLException{
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets");
            ResultSet result = statement.executeQuery();

            ConcurrentHashMap<Long, Ticket> collection = new ConcurrentHashMap<>();

            while (result.next()) {
                DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss")
                        .appendPattern("[.SSSSSSSSS][.SSSSSSS][.SSSSSS][.SSSSS][.SSS][.SS][.S]").toFormatter();
                Ticket ticket = new Ticket();
                ticket.setName(result.getString("name"));
                ticket.setId(result.getLong("id"));
                ticket.setCoordinates(new Coordinates(result.getFloat("x"), result.getLong("y")));
                ticket.setCreationDate(LocalDateTime.parse(result.getTimestamp("creation").toString(), formatter));
                ticket.setPrice(result.getFloat("price"));
                ticket.setType(result.getString("type")==null ? null:TicketType.valueOf(result.getString("type")));
                ticket.setPerson(new Person(result.getTimestamp("birthday")==null ? null:LocalDateTime.parse(result.getTimestamp("birthday").toString(), formatter), result.getString("eye")==null ? null:EyeColor.valueOf(result.getString("eye")), result.getString("hair")==null ? null:HairColor.valueOf(result.getString("hair")), result.getString("country")==null ? null:Country.valueOf(result.getString("country"))));
                Long key = result.getLong("key");
                collection.put(key, ticket);
            }
            return collection;
        }
    }

    public List<Long> clear(String user) throws SQLException{
        try (Connection connection = connect()){
            PreparedStatement selectStatement = connection.prepareStatement("SELECT key FROM tickets " + "WHERE client = ?");
            selectStatement.setString(1, user);
            ResultSet set = selectStatement.executeQuery();

            List<Long> removeSet = new ArrayList<>();
            while (set.next()) {
                removeSet.add(set.getLong("key"));
            }

            PreparedStatement clearStatement = connection.prepareStatement("DELETE FROM tickets " + "WHERE client = ?");
            clearStatement.setString(1, user);
            clearStatement.execute();

            return removeSet;
        }
    }

    public void insert(Long key, Ticket ticket, String user) throws SQLException {

        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tickets " + "(key, name, x, y, price, type, birthday, eye, hair, country, creation, client) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setLong(1, key);
            statement.setString(2, ticket.getName());
            statement.setFloat(3, ticket.getCoordinates().getX());
            statement.setLong(4, ticket.getCoordinates().getY());
            statement.setFloat(5, ticket.getPrice());
            String type = ticket.getType()==null ? null:ticket.getType().toString();
            statement.setString(6, type);
            Timestamp birthday = ticket.getPerson().getBirthday()==null ? null:Timestamp.valueOf(ticket.getPerson().getBirthday());
            statement.setTimestamp(7, birthday);
            String eye = ticket.getPerson().getEyeColor()==null ? null:ticket.getPerson().getEyeColor().toString();
            statement.setString(8, eye);
            String hair = ticket.getPerson().getHairColor()==null ? null:ticket.getPerson().getHairColor().toString();
            statement.setString(9, hair);
            String country = ticket.getPerson().getNationality()==null ? null:ticket.getPerson().getNationality().toString();
            statement.setString(10, country);
            statement.setTimestamp(11, Timestamp.valueOf(ticket.getCreationDate()));
            statement.setString(12, user);
            statement.execute();
        }
    }

    public boolean update(Long key, String user, Ticket ticket) throws SQLException {
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE tickets " +
                        "SET name = ?, " +
                        "x = ?, " +
                        "y = ?, " +
                        "price = ?, " +
                        "type = ?, " +
                        "birthday = ?, " +
                        "eye = ?, " +
                        "hair = ?, " +
                        "country = ?, " +
                        "creation = ? " +
                            "WHERE key = ? AND " +
                                "client = ?"
            );

            statement.setLong(11, key);
            statement.setString(1, ticket.getName());
            statement.setFloat(2, ticket.getCoordinates().getX());
            statement.setLong(3, ticket.getCoordinates().getY());
            statement.setFloat(4, ticket.getPrice());
            String type = ticket.getType() == null ? null : ticket.getType().toString();
            statement.setString(5, type);
            Timestamp birthday = ticket.getPerson().getBirthday() == null ? null : Timestamp.valueOf(ticket.getPerson().getBirthday());
            statement.setTimestamp(6, birthday);
            String eye = ticket.getPerson().getEyeColor() == null ? null : ticket.getPerson().getEyeColor().toString();
            statement.setString(7, eye);
            String hair = ticket.getPerson().getHairColor() == null ? null : ticket.getPerson().getHairColor().toString();
            statement.setString(8, hair);
            String country = ticket.getPerson().getNationality() == null ? null : ticket.getPerson().getNationality().toString();
            statement.setString(9, country);
            statement.setTimestamp(10, Timestamp.valueOf(ticket.getCreationDate()));
            statement.setString(12, user);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean removeByKey(Long key, String user) throws SQLException{
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM tickets *" +
                            "WHERE key = ? AND " +
                            "client = ?"
            );

            statement.setLong(1, key);
            statement.setString(2, user);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Long> removeByKeySet(Set<Long> removeSet, String user) throws SQLException{
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
            List<Long> removeList = new ArrayList<>();
            for (Long key : removeSet) {
                if (removeByKey(key, user)) {
                    removeList.add(key);
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return removeList;
        }
    }

    public void register(String user, String password) throws SQLException{
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, password) VALUES " + "(?, ?)");

            statement.setString(1, user);
            statement.setBytes(2, MessageDigest.getInstance("SHA-1").digest((password + salt).getBytes(StandardCharsets.UTF_8)));

            statement.execute();
        } catch (NoSuchAlgorithmException ignored) {

        }
    }

    public boolean checkUserPassword(String user, String password) throws SQLException {
        try (Connection connection = connect()) {
            if (user==null) {
                return false;
            }
            PreparedStatement statement = connection.prepareStatement("SELECT password FROM users " + "WHERE name = ?");
            statement.setString(1, user);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                byte[] bytes = set.getBytes("password");
                try {
                    return Arrays.equals(bytes, MessageDigest.getInstance("SHA-1").digest((password + salt).getBytes(StandardCharsets.UTF_8)));
                } catch (NoSuchAlgorithmException ignored) {

                }
            }
            return false;
        }
    }
}
