package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Данил on 19.04.2017.
 1. Статическое поле Map<String, Connection> connectionMap, где ключом будет имя
 клиента, а значением — соединение с ним.
 2. Инициализацию поля из п.7.1 с помощью подходящего Map из библиотеки
 java.util.concurrent, т.к. работа с этим полем будет происходить из разных потоков и
 нужно обеспечить потокобезопасность.
 3. Статический метод void sendBroadcastMessage(Message message), который должен
 отправлять сообщение message всем соединениям из connectionMap. Если при
 отправке сообщение произойдет исключение IOException, нужно отловить его и
 сообщить пользователю, что не смогли отправить сообщение.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    static void sendBroadcastMessage(Message message) {
        for (Map.Entry entry :
                connectionMap.entrySet()) {

            Connection aConnection = (Connection) entry.getValue();
            String clientName = (String) entry.getKey();

            try {
               aConnection.send(message);
            } catch (IOException e) {
                System.out.println("Ошибка доставки сообщения для " + clientName);
            }
        }
    }
    public static void main(String[] args){
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;


        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен...");
            while (true) {
                Socket connection = serverSocket.accept();
                new Handler(connection).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (Exception e) {

            }
        }


    }

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }
    }
}
