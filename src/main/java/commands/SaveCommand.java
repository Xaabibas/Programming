package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import commands.abstraction.Command;
import commands.type.adapter.CoordinatesTypeAdapter;
import commands.type.adapter.LocalDateTimeTypeAdapter;
import commands.type.adapter.PersonTypeAdapter;
import commands.type.adapter.TicketTypeAdapter;
import main.CollectionManager;
import moduls.Coordinates;
import moduls.Person;
import moduls.Ticket;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * Класс команды save - сохранение коллекции в файл
 */
public class SaveCommand extends Command {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().registerTypeAdapter(Ticket.class, new TicketTypeAdapter()).registerTypeAdapter(Person.class, new PersonTypeAdapter()).registerTypeAdapter(Coordinates.class, new CoordinatesTypeAdapter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();

    /**
     * @param cm - менеджер команд
     */
    public SaveCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "save - сохранение коллекции в файл";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "save";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length!=1) {
            return false;
        }
        try {
            PrintWriter printWriter = new PrintWriter(this.getCm().getFile());
            String json = gson.toJson(this.getCm().getCollection(), new TypeToken<LinkedHashMap<Long, Ticket>>() {
            }.getType());
            printWriter.println(json);
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
