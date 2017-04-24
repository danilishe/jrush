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


/*
Добавь приватный метод void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException,
где значение параметров такое же, как и у метода
sendListOfUsers.

Он должен:
1. Принимать сообщение клиента
2. Если принятое сообщение – это текст (тип TEXT), то формировать новое текстовое сообщение путем
конкатенации: имени клиента, двоеточия, пробела и текста сообщения.
Например, если мы получили сообщение с текстом «привет чат» от пользователя «Боб«, то нужно сформировать сообщение «Боб: привет чат«.
3. Отправлять сформированное сообщение всем клиентам с помощью метода sendBroadcastMessage.
4. Если принятое сообщение не является текстом, вывести сообщение об ошибке
5. Организовать бесконечный цикл, внутрь которого перенести функционал пунктов 10.1-10.4.

 */

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            Message receivedMessage;
            while (true) {
                receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.TEXT)
                    sendBroadcastMessage(
                            new Message(MessageType.TEXT, String.format("%s: %s", userName, receivedMessage.getData()))
                    );
                else
                    System.out.println("Ошибка - неверный тип собщения. Ожидается тип TEXT, получен " + receivedMessage.getType());
            }

        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name = "";
            Message receivedMessage;

            do {
                // отправляем запрос имени
                connection.send(new Message(MessageType.NAME_REQUEST));

                // получаем ответ
                receivedMessage = connection.receive();

                // ответ должен быть USER_NAME, иначе идём в начало цикла
                if (receivedMessage.getType() == MessageType.USER_NAME)
                    name = receivedMessage.getData();
                else continue;

                // если имя null или длина == 0, идём в начало цикла
                if (name.length() < 1 || connectionMap.containsKey(name)) continue;

                break;

            } while (true);

            // добавляем имя и соединение в список
            connectionMap.put(name, connection);
            // отправляем клиенту, что сообщение принято
            connection.send(new Message(MessageType.NAME_ACCEPTED));

            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {

            // рассылаем его участникам
            for (Map.Entry<String, Connection> connectionEntry:
            connectionMap.entrySet()) {
                // отправляем, если это не новый человек
                if (!connectionEntry.getKey().equals(userName))
                    connection.send(
                            new Message( MessageType.USER_ADDED, connectionEntry.getKey() )
                    );
            }
        }

    }
}
