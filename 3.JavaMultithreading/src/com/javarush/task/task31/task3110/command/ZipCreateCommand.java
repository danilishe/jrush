package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Данил on 09.05.2017.
 */
public class ZipCreateCommand extends ZipCommand {

    /*Для этого нужно:
1. В начале метода добавить вывод сообщения «Создание архива.» Не забудь, что мы работаем с консолью через методы класса ConsoleHelper.
2. Создай новый объект класса ZipFileManager. Т.к. создание этого объекта будет необходимо и другим командам,
вынеси создание в отдельный метод ZipFileManager getZipFileManager() throws Exception в класс ZipCommand.
Этот метод должен:

3. Попроси пользователя ввести полное имя файла или директории для архивации
4. Создай путь Path, используя введенную строку
5. У объекта класса ZipFileManager вызови метод createZip(), передав в него путь из п.4
6. Выведи в консоль сообщение «Архив создан.»
7. Оберни содержимое метода execute() класса ZipCreateCommand в блок try-catch и отлавливай
исключение типа PathIsNotFoundException. Если оно произошло, выведи сообщение «Вы неверно указали имя файла или директории.»
8. Запусти программу и проверь, что команда «упаковать файлы в архив» работает

*/
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Создание архива.");
            ZipFileManager zipFileManager = getZipFileManager();
            ConsoleHelper.writeMessage("Введите полное имя запаковываемого файла/папки: ");
            Path targetPath = Paths.get(ConsoleHelper.readString());
            zipFileManager.createZip(targetPath);
            ConsoleHelper.writeMessage("Архив создан.");
        } catch (PathIsNotFoundException pinfe) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }
}
