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
 1) Сформировать и отправить команду запроса имени пользователя
 2) Получить ответ клиента
 3) Проверить, что получена команда с именем пользователя
 4) Достать из ответа имя, проверить, что оно не пустое и пользователь с таким именем еще не подключен (используй connectionMap)
 5) Добавить нового пользователя и соединение с ним в connectionMap
 6) Отправить клиенту команду информирующую, что его имя принято
 7) Если какая-то проверка не прошла, заново запросить имя клиента
 8) Вернуть принятое имя в качестве возвращаемого значения


 Требования:
 1. В классе Handler должен присутствовать метод private String serverHandshake(Connection connection).
 2. Метод serverHandshake должен отправлять запрос имени используя метод send класса Connection.
 3. До тех пор, пока тип сообщения полученного в ответ не будет равен MessageType.USER_NAME, запрос имени должен быть выполнен снова.
 4. В случае, если в ответ пришло пустое имя, запрос имени должен быть выполнен снова.
 5. В случае, если имя уже содержится в connectionMap, запрос имени должен быть выполнен снова.
 6. После успешного проведения всех проверок, метод serverHandshake должен добавлять новую пару (имя, соединение) в connectionMap
 и отправлять сообщение о том, что имя было принято.
 7. Метод serverHandshake должен возвращать имя нового клиента с которым было установлено соединение.
 */

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
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

    }
}
