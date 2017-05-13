package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileProperties;
import com.javarush.task.task31.task3110.ZipFileManager;

/**
 * Created by Данил on 09.05.2017.
 *
 * 1. Выведи сообщение «Просмотр содержимого архива.»
 2. Создай объект класса ZipFileManager с помощью метода getZipFileManager()
 3. Выведи сообщение «Содержимое архива:»
 4. Получи список файлов архива с помощью метода getFilesList()
 5. Выведи свойства каждого файла в консоль. Тут нам и пригодится ранее реализованный метод toString() класса FileProperties
 6. Выведи сообщение «Содержимое архива прочитано.»
 7. Запусти программу и проверь, что команда «просмотреть содержимое архива» работает
 */
public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ZipFileManager zipFileManager = getZipFileManager();
        for (FileProperties fileProps :
                zipFileManager.getFilesList()) {
            ConsoleHelper.writeMessage(fileProps.toString());
        }
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");
    }
}
