package com.javarush.task.task30.task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Данил on 19.04.2017.
 *
 * Добавь в класс Connection:
 1) Final поля:
 а) Socket socket
 б) ObjectOutputStream out
 в) ObjectInputStream in
 2) Конструктор, который должен принимать Socket в качестве параметра и
 инициализировать поля класса. Для инициализации полей in и out используй
 соответствующие потоки сокета. Конструктор может бросать исключение IOException.
 Создать объект класса ObjectOutputStream нужно до того, как будет создаваться объект
 класса ObjectInputStream, иначе может возникнуть взаимная блокировка потоков,
 которые хотят установить соединение через класс Connection. Более подробно об этом
 ты можешь прочитать в спецификации класса ObjectInputStream.
 3) Метод void send(Message message) throws IOException. Он должен записывать
 (сериализовать) сообщение message в ObjectOutputStream. Этот метод будет
 вызываться из нескольких потоков. Позаботься, чтобы запись в объект
 ObjectOutputStream была возможна только одним потоком в определенный момент
 времени, остальные желающие ждали завершения записи. При этом другие методы
 класса Connection не должны быть заблокированы.
 4) Метод Message receive() throws IOException, ClassNotFoundException. Он должен читать
 (десериализовать) данные из ObjectInputStream. Сделай так, чтобы операция чтения
 не могла быть одновременно вызвана несколькими потоками, при этом вызов других
 методы класса Connection не блокировать.
 5) Метод SocketAddress getRemoteSocketAddress(), возвращающий удаленный адрес
 сокетного соединения.
 6) Метод void close() throws IOException, который должен закрывать все ресурсы класса.

 Класс Connection должен поддерживать интерфейс Closeable.
 */
public class Connection implements Closeable {

    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    public Connection(Socket aSocket) throws IOException {
        socket = aSocket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException {
        synchronized (out) {
            out.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        synchronized (in) {
            Message message;
            message = (Message) in.readObject();
            return message;
        }

    }
    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
