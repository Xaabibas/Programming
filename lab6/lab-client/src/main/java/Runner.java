import network.NetworkManager;
import enterator.EnterTicket;
import moduls.Ticket;
import network.Request;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    private final Scanner scanner;
    private final NetworkManager networkManager;

    public Runner(Scanner scanner, NetworkManager networkManager) {
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

            try {
                send(commandName, line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void send(String commandName, String line) throws IOException {
        Request request;

        if (commandName.equals("insert") || commandName.equals("update") ||
                commandName.equals("remove_greater") || commandName.equals("remove_lower")) {
            Ticket ticket = new EnterTicket().enter(scanner);
            BufferedReader f = new BufferedReader(new FileReader("scratch.txt"));
            ticket.setName(f.readLine());

            request = new Request(commandName, line, ticket);


            networkManager.sendAndReceive(request);
        } else if (commandName.equals("exit")) {
            request = new Request(commandName, line);
            networkManager.sendAndReceive(request);
            System.out.println("Работа клиентского приложения завершена");
            System.exit(0);
        } else if (commandName.equals("execute_script")) {
            executeScript(line);
        } else {
            request = new Request(commandName, line);

            networkManager.sendAndReceive(request);
        }
    }

    private void executeScript(String line) throws IOException {
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

                send(commandName, fileLine);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Файл не найден");
        }
    }
}