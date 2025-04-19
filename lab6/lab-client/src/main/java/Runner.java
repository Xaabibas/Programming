import Network.ClientNetworkManager;
import network.Request;
import enterator.EnterTicket;
import moduls.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    final private Scanner scanner;
    final private ClientNetworkManager networkManager;

    public Runner(Scanner scanner, ClientNetworkManager networkManager) {
        this.scanner = scanner;
        this.networkManager = networkManager;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void interactiveMode() {
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");

            String commandName = tokens[0].toLowerCase();

            sendUserCommand(commandName, line);
        }
    }

    private void sendUserCommand(String commandName, String line) {
        Request request;

        if (commandName.equals("insert") || commandName.equals("update")
                || commandName.equals("remove_greater") || commandName.equals("remove_lower")) {
            Ticket ticket = new EnterTicket().enter(scanner);

            request = new Request(commandName, line, ticket);

            networkManager.sendAndReceive(request);
        }
        else if (commandName.equals("exit")) {
            request = new Request(commandName, line);
            networkManager.sendAndReceive(request);
            System.out.println("Работа клиентского приложения завершена");
            System.exit(0);
        }
        else if (commandName.equals("execute_script")) {
            executeScript(line);
        }
        else {
            request = new Request(commandName, line);

            networkManager.sendAndReceive(request);
        }
    }

    private void executeScript(String line) {
        String[] tokens = line.split(" ");

        if (tokens.length < 2) {
            System.out.println("[ERROR] В качестве аргументов команды необходимо передать путь или имя файла");
            return;
        }
        String path = Arrays.toString(Arrays.copyOfRange(tokens, 1, tokens.length));

        File file = new File(path.substring(1, path.length() - 1));

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String[] args = fileLine.split(" ");
                String commandName = args[0];

                if (commandName.equals("execute_script")) {
                    System.out.println("[ERROR] Рекурсия запрещена");
                    continue;
                }

                sendUserCommand(commandName, fileLine);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Файл не найден");
        }
    }
}