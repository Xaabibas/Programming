package Network;

import network.Request;
import network.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientNetworkManager {
    private int port;
    private InetAddress host;
    private SocketAddress address;

    public ClientNetworkManager(int port, InetAddress host) {
        this.port = port;
        this.host = host;
        this.address = new InetSocketAddress(host, port);
    }

    private SocketChannel connectToServer() {
        try {
            return SocketChannel.open(address);
        } catch (UnknownHostException e) {
            System.out.println("[ERROR] Неизвестный хост");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("[ERROR] Не удалось подключиться к серверу: " + e.getClass().getSimpleName());
            System.exit(0);
        }
        return null;
    }

    public void sendAndReceive(Request request) {
        try (SocketChannel channel = connectToServer()) {
            byte[] objBytes = serialize(request);
            if (objBytes == null) {
                return;
            }
            ByteBuffer bufToSend = ByteBuffer.wrap(objBytes);
            channel.write(bufToSend);
            bufToSend.clear();

            ByteBuffer len = ByteBuffer.allocate(4);
            channel.read(len);
            len.flip();

            int length = len.getInt();

            ByteBuffer bufToRead = ByteBuffer.allocate(length);
            channel.read(bufToRead);
            bufToRead.flip();

            Response response = (Response) deserialize(bufToRead.array());

            if (!(response == null)) {
                System.out.println(response.getAnswer());
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Не удалось установить соединение с сервером");
        }

    }

    private byte[] serialize(Object o) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(o);
            return bos.toByteArray();
        } catch (IOException e) {
            System.out.println("[ERROR] Не удалось сериализовать данные");
            return null;
        }
    }

    private Object deserialize(byte[] bytes) {
        try (InputStream is = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(is)){
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR] Не удалось десериализовать даныые");
            return null;
        }
    }
}
