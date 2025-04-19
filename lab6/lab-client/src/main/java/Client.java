import Network.ClientNetworkManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) throws UnknownHostException {
        Scanner scanner = new Scanner(System.in);
        ClientNetworkManager networkManager = new ClientNetworkManager(46789, InetAddress.getByName("helios.cs.ifmo.ru"));
//        ClientNetworkManager networkManager = new ClientNetworkManager(46789, InetAddress.getByName("localhost"));
        Runner runner = new Runner(scanner, networkManager);

        runner.interactiveMode();
    }
}
