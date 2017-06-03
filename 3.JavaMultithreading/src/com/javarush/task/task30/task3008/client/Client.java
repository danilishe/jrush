package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Данил on 25.04.2017.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

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
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Произошла ошибка ожидания ответа.");
        }


        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
            while (clientConnected) {
                String clientMessage = ConsoleHelper.readString();
                if (clientMessage.equals("exit")) break;
                if (shouldSendTextFromConsole()) sendTextMessage(clientMessage);
            }
        } else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

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
        // тестовая строка
//        ConsoleHelper.writeMessage("Введите имя");
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

        try {
            ;
            connection.send(
                    new Message(MessageType.TEXT, text));
        } catch (IOException ioe) {
            ConsoleHelper.writeMessage("Ошибка отправки сообщения.");
            clientConnected = false;
        }
    }

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
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                        String userName = getUserName();
                        Message newMessage = new Message(MessageType.USER_NAME, userName);
                        connection.send(newMessage);
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else
                    throw new IOException("Unexpected MessageType");
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message recievedMessage = connection.receive();
                if (recievedMessage.getType() == MessageType.TEXT)
                    processIncomingMessage(recievedMessage.getData());
                else if (recievedMessage.getType() == MessageType.USER_ADDED)
                    informAboutAddingNewUser(recievedMessage.getData());
                else if (recievedMessage.getType() == MessageType.USER_REMOVED)
                    informAboutDeletingNewUser(recievedMessage.getData());
                else
                    throw new IOException("Unexpected MessageType");
            }
        }

        public void run() {
            String address = getServerAddress();
            int port = getServerPort();
            try (Socket socket = new Socket(address, port)) {
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }  catch (IOException ioe) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException cnfe) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
