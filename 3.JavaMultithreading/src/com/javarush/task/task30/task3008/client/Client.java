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
/*1) void processIncomingMessage(String message) – должен выводить текст message в консоль.
2) void informAboutAddingNewUser(String userName) – должен выводить в консоль информацию о том,
что участник с именем userName присоединился к чату.
3) void informAboutDeletingNewUser(String userName) – должен выводить в консоль,
что участник с именем userName покинул чат.
4) void notifyConnectionStatusChanged(boolean clientConnected) – этот метод должен:
а) Устанавливать значение поля clientConnected внешнего объекта Client в соответствии с переданным параметром.
б) Оповещать (пробуждать ожидающий) основной поток класса Client.

Подсказка: используй синхронизацию на уровне текущего объекта внешнего класса и метод notify.
Для класса SocketThread внешним классом является класс Client.*/

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
           ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName + " присоединился к чату.");
        }
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник с именем " + userName + " покинул чат.");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            // Метод notifyConnectionStatusChanged должен устанавливать значение поля clientConnected
            // внешнего объекта Client равным полученному параметру.

            // Метод notifyConnectionStatusChanged должен вызвать метод notify на внешнем объекте Client.
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                notify();
            }
        }
    }
}
