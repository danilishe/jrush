package com.javarush.task.task30.task3008;

import java.io.Serializable;

/**
 * Created by Данил on 19.04.2017.
 *
 * Добавь в класс Message:
 1) Поддержку интерфейса Serializable.
 2) final поле типа MessageType type, которое будет содержать тип сообщения.
 3) final поле типа String data, которое будет содержать данные сообщения.
 4) Геттеры для этих полей.
 5) Конструктор, принимающий только MessageType, он должен проинициализировать
 поле type переданным параметром, а поле data оставить равным null.
 6) Конструктор, принимающий MessageType type и String data. Он должен также
 инициализировать все поля класса.
 */
public class Message implements Serializable {
    final private MessageType type;
    final private String data;

    public MessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public Message(MessageType type) {
        this.type = type;
        this.data = null;
    }
    public Message(MessageType type, String data) {
        this.type = type;
        this.data = data;
    }

}
