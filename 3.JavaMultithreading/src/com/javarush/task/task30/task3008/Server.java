package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
                ConsoleHelper.writeMessage("Ошибка доставки сообщения для " + clientName);
            }
        }
    }
    public static void main(String[] args){
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;


        try {
            serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("Сервер запущен...");
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
Реализуем метод void run() в классе Handler.

Он должен:
1. Выводить сообщение, что установлено новое соединение с
удаленным адресом, который можно получить с помощью метода getRemoteSocketAddress.
2. Создавать Connection, используя поле socket.
3. Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента.
4. Рассылать всем участникам чата информацию об имени присоединившегося участника
(сообщение с типом USER_ADDED). Подумай, какой метод подойдет для этого лучше всего.
5. Сообщать новому участнику о существующих участниках.
6. Запускать главный цикл обработки сообщений сервером.
7. Обеспечить закрытие соединения при возникновении исключения.
8. Отловить все исключения типа IOException и ClassNotFoundException,
вывести в консоль информацию, что произошла ошибка при обмене данными с удаленным адресом.
9. После того как все исключения обработаны,
если п.11.3 отработал и возвратил нам имя, мы должны удалить запись
для этого имени из connectionMap и разослать всем остальным участникам
сообщение с типом USER_REMOVED и сохраненным именем.

10. Последнее, что нужно сделать в методе run() – вывести сообщение,
информирующее что соединение с удаленным адресом закрыто.

Наш сервер полностью готов. Попробуй его запустить.

 */

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }
        private String userName;

        public void run () {
            ConsoleHelper.writeMessage("Соединение с " + socket.getRemoteSocketAddress() + " установлено.");
            try (Connection connection  = new Connection(socket)){
                                userName = serverHandshake(connection);

                sendBroadcastMessage(
                        new Message(MessageType.USER_ADDED, userName)
                );

                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
            } finally {

                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(
                            new Message(MessageType.USER_REMOVED, userName)
                    );
                }
                ConsoleHelper.writeMessage("Соединение закрыто.");
            }

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
                    ConsoleHelper.writeMessage("Ошибка - неверный тип собщения. Ожидается тип TEXT, получен " + receivedMessage.getType());
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
