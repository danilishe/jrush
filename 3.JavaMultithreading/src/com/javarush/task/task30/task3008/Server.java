package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Данил on 19.04.2017.
 * 1)	В класс Server приватный статический вложенный класс Handler, унаследованный от
 Thread.
 2)	В класс Handler поле socket типа Socket.
 3)	В класс Handler конструктор, принимающий в качестве параметра Socket и
 инициализирующий им соответствующее поле класса.
 4)	Метод main класса Server, должен:
 а) Запрашивать порт сервера, используя ConsoleHelper.
 б) Создавать серверный сокет java.net.ServerSocket, используя порт из предыдущего пункта.
 в) Выводить сообщение, что сервер запущен.
 г) В бесконечном цикле слушать и принимать входящие сокетные соединения только что созданного
 серверного сокета.
 д) Создавать и запускать новый поток Handler, передавая в конструктор сокет из предыдущего пункта.
 е) После создания потока обработчика Handler переходить на новый шаг цикла.
 ж) Предусмотреть закрытие серверного сокета в случае возникновения исключения.
 з) Если исключение Exception все же произошло, поймать его и вывести сообщение
 об ошибке.
 */
public class Server {
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
