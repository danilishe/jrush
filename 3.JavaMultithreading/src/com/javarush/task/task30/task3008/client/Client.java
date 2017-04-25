package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

/**
 * Created by Данил on 25.04.2017.

 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

/*
Реализация метода run должна:
а) Создавать новый сокетный поток с помощью метода getSocketThread.
б) Помечать созданный поток как daemon, это нужно для того,
чтобы при выходе из программы вспомогательный поток прервался автоматически.
в) Запустить вспомогательный поток.
г) Заставить текущий поток ожидать, пока он не получит нотификацию из другого потока.
Подсказка: используй wait и синхронизацию на уровне объекта. Если во время ожидания возникнет исключение,
сообщи об этом пользователю и выйди из программы.
д) После того, как поток дождался нотификации, проверь значение clientConnected.
Если оно true – выведи «Соединение установлено. Для выхода наберите команду ‘exit’.«.
Если оно false – выведи «Произошла ошибка во время работы клиента.».
е) Считывай сообщения с консоли пока клиент подключен. Если будет введена команда ‘exit‘, то выйди из цикла.
ж) После каждого считывания, если метод shouldSendTextFromConsole() возвращает true, отправь считанный текст с
помощью метода sendTextMessage().

2. Добавь метод main(). Он должен создавать новый объект типа Client и вызывать у него метод run().

*/
    public static void main(String[] args) {
        new Client().run();
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        try {
            /// блокируется по this??
            // sync блок внутри try!
            synchronized (this) {
                wait();
            }
        }  catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Произошла ошибка ожидания ответа.");
        }


        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
            while (clientConnected) {
                String clientMessage = ConsoleHelper.readString();
                if (clientMessage.equals("exit")) break;
                if (shouldSendTextFromConsole()) sendTextMessage( clientMessage );
            }
        }
        else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");




    }

    protected String getServerAddress() {
        String serverAdress = ConsoleHelper.readString();
        return serverAdress;
    }
    protected int getServerPort() {
        int serverPort = ConsoleHelper.readInt();
        return serverPort;
    }
    protected String getUserName() {
        String userName = ConsoleHelper.readString();
        return userName;
    }
    protected boolean shouldSendTextFromConsole() {
        return true;
    }
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }
    protected void sendTextMessage(String text) {

        try {;
            connection.send(
                    new Message(MessageType.TEXT, text)   );
        } catch (IOException ioe) {
            ConsoleHelper.writeMessage("Ошибка отправки сообщения.");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread {
    }
}
