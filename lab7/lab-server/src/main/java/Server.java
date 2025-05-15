import managers.*;
import network.Request;

import network.Response;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public final class Server extends Thread {
    public static final Logger logger = Logger.getLogger("ServerLogger");
    private final RequestManager requestManager;
    private final ResponseManager responseManager;
    private final NetworkManager networkManager;
    private final CommandManager commandManager;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final ConcurrentLinkedQueue<Request> requestQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Response> responseQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Socket> clientQueue = new ConcurrentLinkedQueue<>();

    public Server(RequestManager requestManager, ResponseManager responseManager,
                  NetworkManager networkManager, CommandManager commandManager) {
        this.requestManager = requestManager;
        this.responseManager = responseManager;
        this.networkManager = networkManager;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        new Thread(() -> {
            while (true) {
                if (!requestQueue.isEmpty()) {
                    Request request = requestQueue.poll();
                    AtomicReference<Response> response = new AtomicReference<>(new Response());

                    Thread processThread = new Thread(() -> {
                        response.set(commandManager.processRequest(request));
                        logger.info("Request was processed");
                    });
                    processThread.start();

                    Thread sendThread = new Thread(() -> {
                        try {
                            processThread.join();
                            responseManager.sendToClient(response.get(), clientQueue.poll());
                            logger.info("Response was sent");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    sendThread.start();
                }
            }
        }).start();

        while (true) {
            Socket client = networkManager.connectToClient();

            pool.submit(() -> {
                Request request = requestManager.readRequest(client);
                requestQueue.add(request);
                clientQueue.add(client);
            });
        }
    }











//        while (true) {
//            try {
//                Socket client = networkManager.connectToClient();
//
//                pool.submit(() -> {
//                    Request request = requestManager.readRequest(client);
//                    logger.info("The request from the user was successfully received");
//
//                    AtomicReference<Response> response = new AtomicReference<>(new Response());
//                    Thread t = new Thread(() -> {
//                        response.set(commandManager.processRequest(request)); // Обрабатываем запрос, формируем ответ
//                        logger.info("The request was successfully processed and a response was generated");
//                    });
//
//                    new Thread(() -> {
//                        try {
//                            t.join();
//                            responseManager.sendToClient(response.get(), client);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }).start();
//                });
//            } catch (NullPointerException e) {
//                logger.warning("Couldn't process the request");
//            }
//        }
//    }
}