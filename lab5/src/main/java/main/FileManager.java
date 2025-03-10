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

public class FileManager {
    private File file;
    private final Gson gson;

    public FileManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .registerTypeAdapter(LinkedHashMap.class, new CollectionDeserializer())
                .registerTypeAdapter(Ticket.class, new TicketTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(Person.class, new PersonTypeAdapter())
                .registerTypeAdapter(Coordinates.class, new CoordinatesTypeAdapter())
                .create();
    }

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
        }

        return null;
    }

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
}
