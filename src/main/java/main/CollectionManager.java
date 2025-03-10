package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import commands.type.adapter.*;
import moduls.Coordinates;
import moduls.Person;
import moduls.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Scanner;

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
     * Файл, в котором хранится коллекция
     */
    private File file;

    /**
     * Конструктор
     */
    public CollectionManager(Scanner scanner) {
        StringBuilder json = new StringBuilder();
        FileManager fm = new FileManager();
        String line;
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LinkedHashMap.class, new CollectionDeserializer())
                .registerTypeAdapter(Ticket.class, new TicketTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapter(Person.class, new PersonTypeAdapter())
                .registerTypeAdapter(Coordinates.class, new CoordinatesTypeAdapter())
                .create();
        while (true) {
            System.out.print("Введите имя переменной > ");
            line = scanner.nextLine();

            if (line.equals("exit")) {
                this.file = null;
                break;
            }

            this.file = fm.getFileByEnv(line);
            if (this.file == null) {
                continue;
            }

            try {
                Scanner scanner1 = new Scanner(this.file);

                while (scanner1.hasNextLine()) {
                    json.append(scanner1.nextLine());
                }

                this.collection = gson.fromJson(json.toString(), LinkedHashMap.class);

                scanner1.close();

                break;
            } catch (FileNotFoundException e) {
                System.out.println("Такого файла не существует!");
            } catch (JsonSyntaxException e) {
                System.out.println("В веденном файле ошибка синтаксиса!");
            }
        }

        this.time = LocalDateTime.now();
    }

    /**
     * @return возвращает значение поля collection
     */
    public LinkedHashMap<Long, Ticket> getCollection() {
        return collection;
    }

    /**
     * @return возвращает значение поля ime
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @return возвращает значение поля file
     */
    public File getFile() {
        return file;
    }
}
