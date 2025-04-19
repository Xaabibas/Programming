package managers;

import network.Response;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ResponseManager {
    public static final Logger logger = Logger.getLogger("ResponseLogger");
    public void sendToClient(Response response, Socket client) {
        logger.info("Попытка отправить ответ пользователю");
        try {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bytesOut);

            out.writeObject(response); // Десериализация
            byte[] data = bytesOut.toByteArray(); // Запись десериализованных данных



            DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());

            dataOut.writeInt(data.length); // Передаем длину сообщения
            dataOut.write(data); // Передаем десериализованный объект

            bytesOut.close();
            out.close();
            dataOut.close();
            logger.info("Ответ был успешно отправлен");
        } catch (IOException e) {
            logger.warning("Не удалось отправить ответ пользователю");
        }
    }
}
