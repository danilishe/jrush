package com.javarush.task.task31.task3110.command;
import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Данил on 09.05.2017.
 * 3. Реализуй метод execute() класса ZipRemoveCommand, создав объект класса ZipFileManager,
 спросив пользователя из какого архива и какой файл будем удалять, и вызвав метод removeFile().
 */
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Удаление файла из архива.");
        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите имя файла для удаления: ");
        Path fileForRemove = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(fileForRemove);
        ConsoleHelper.writeMessage("Файл удалён из архива.");
    }
}
