package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import commands.type.adapter.*;
import moduls.Coordinates;
import moduls.Person;
import moduls.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Класс для работы с файлами
 */
public class FileManager {
    /**
     * Файл коллекции
     */
    private File file;
    /**
     * gson
     */
    private final Gson gson;

    /**
     * Конструктор
     */
    public FileManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .registerTypeAdapter(LinkedHashMap.class, new CollectionDeserializer())
                .registerTypeAdapter(Ticket.class, new TicketTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(Person.class, new PersonTypeAdapter())
                .registerTypeAdapter(Coordinates.class, new CoordinatesTypeAdapter())
                .create();
    }

    /**
     * Обновляет значение поля file
     *
     * @param line - имя переменной
     * @return возвращает true, если файл был обновлен, и false, если не обновлен
     */
    public boolean registerFileByEnv(String line) {
        try {
            String path = System.getenv(line);
            this.file = new File(path);
            return true;
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав для чтения файла!");
        } catch (NullPointerException e) {
            System.out.println("Такой переменной не существует!");
        }
        return false;
    }

    /**
     * Возвращает файл по имени переменной
     *
     * @param line - имя переменной
     * @return возвращает файл, если все хорошо, и null, в противном случае
     */
    public File getFileByEnv(String line) {
        try {
            String path = System.getenv(line);
            return new File(path);
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав для чтения файла!");
        } catch (NullPointerException e) {
            System.out.println("Такой переменной не существует!");
        }
        return null;
    }

    /**
     * Читает коллекцию из записанного файла
     *
     * @return возвращает коллекцию из файла
     */
    public LinkedHashMap<Long, Ticket> readCollection() {

        try {
            Scanner scanner = new Scanner(file);

            StringBuilder json = new StringBuilder();

            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }

            scanner.close();

            return gson.fromJson(json.toString(), LinkedHashMap.class);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (JsonSyntaxException e) {
            System.out.println("В файле ошибка синтаксиса json!");
        } catch (Exception e) {
            System.out.println("Невозможно десериализовать!");
        }

        return null;
    }

    /**
     * Записывает в файл коллекцию
     *
     * @param collection - записываемая коллекция
     */
    public void writeCollection(LinkedHashMap<Long, Ticket> collection) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            String json = gson.toJson(collection, new TypeToken<LinkedHashMap<Long, Ticket>>() {
            }.getType());
            printWriter.println(json);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл записи коллекции не найден!");
        }
    }

    /**
     * Очистка файла
     */
    public void clearFile() {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("{}");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден1");
        }
    }
}
