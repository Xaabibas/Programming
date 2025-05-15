import network.NetworkManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public final class Client {
    private final int port;

    public Client(int port) {
        this.port = port;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            NetworkManager networkManager = new NetworkManager(port, InetAddress.getByName("localhost"));
            Runner runner = new Runner(scanner, networkManager);

            runner.interactiveMode();
        } catch (UnknownHostException e) {
            System.out.println("[ERROR] Неизвестный хост");
        }
    }
}
