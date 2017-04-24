package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Данил on 19.04.2017.
 *
 * 2. Статический метод writeMessage(String message), который должен выводить
 сообщение message в консоль.
 3. Статический метод String readString(), который должен считывать строку с
 консоли. Если во время чтения произошло исключение, вывести пользователю
 сообщение «Произошла ошибка при попытке ввода текста. Попробуйте еще раз.» И
 повторить ввод. Метод не должен пробрасывать исключения IOException наружу.
 Другие исключения не должны обрабатываться.
 4. Статический метод int readInt(). Он должен возвращать введенное число и
 использовать метод readString(). Внутри метода обработать исключение
 NumberFormatException. Если оно произошло вывести сообщение «Произошла ошибка
 при попытке ввода числа. Попробуйте еще раз.» И повторить ввод числа.

 В этой задаче и далее, если не указано дополнительно другого, то все поля класса должны
 быть приватными, а методы публичными.

 */
public class ConsoleHelper {
    private static  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String string = "";
        do {
            try {
                string = bufferedReader.readLine();
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        } while (string.length() < 1);
        return string;
    }

    public static int readInt() {
        boolean numberFound = false;
        int number = 0;
        do {
            try {
                number = Integer.parseInt(readString());
                numberFound = true;
            } catch (NumberFormatException nfe) {
                ConsoleHelper.writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }

        } while (!numberFound);
        return number;
    }
}
