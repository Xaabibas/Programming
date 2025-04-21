package managers;

import adapter.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import moduls.Coordinates;
import moduls.Person;
import moduls.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileManager {
    public static final Logger logger = Logger.getLogger("FileLogger");
    private File file;
    private final Gson gson;

    public FileManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .registerTypeAdapter(LinkedHashMap.class, new CollectionDeserializer())
                .registerTypeAdapter(Ticket.class, new TicketTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(Person.class, new PersonTypeAdapter())
                .registerTypeAdapter(Coordinates.class, new CoordinatesTypeAdapter()).create();
    }

    public void registerFileByEnv(String line) {
        try {
            String path = System.getenv(line);
            this.file = new File(path);
        } catch (SecurityException e) {
            logger.severe("Недостаточно прав для чтения файла");
        } catch (NullPointerException e) {
            logger.severe("Переданной переменной " + line + " не существует");
        }
    }

    public File getFileByEnv(String line) {
        try {
            String path = System.getenv(line);
            return new File(path);
        } catch (SecurityException e) {
            logger.severe("Недостаточно прав для чтения файла");
        } catch (NullPointerException e) {
            logger.severe("Переданной переменной не существует");
        }
        return null;
    }

    public LinkedHashMap<Long, Ticket> readCollection() {
        try {
            Scanner scanner = new Scanner(file);

            StringBuilder json = new StringBuilder();
            logger.info("Чтение файла");
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }

            scanner.close();

            return gson.fromJson(json.toString(), LinkedHashMap.class);

        } catch (FileNotFoundException e) {
            logger.severe("Файл не найден");
        } catch (JsonSyntaxException e) {
            logger.severe("В файле ошибка синтаксиса json");
        } catch (IllegalArgumentException e) {
            logger.severe("В файле записаны недопустимые данные");
        } catch (Exception e) {
            logger.severe("Не удалось десериализовать данные");
        } return null;
    }

    public void writeCollection(LinkedHashMap<Long, Ticket> collection) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            String json = gson.toJson(collection, new TypeToken<LinkedHashMap<Long, Ticket>>() {
            }.getType());
            printWriter.println(json);
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.severe("Файл не найден");
        }
    }

    public void clearFile() {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("{}");
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.severe("Файл не найден");
        }
    }
}
