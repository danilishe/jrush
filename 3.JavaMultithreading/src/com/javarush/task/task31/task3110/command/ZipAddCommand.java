package com.javarush.task.task31.task3110.command;
import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Данил on 09.05.2017.
 *
 * 3. Реализуй метод execute() класса ZipAddCommand:
 * все как обычно, но не забудь спросить у пользователя в какой архив
 * и какой файл он хочет добавить, обработай исключение PathIsNotFoundException,
 * которое может кинуть метод addFile().
 */
public class ZipAddCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Добавление файла в архив.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите путь к добавляемому файлу: ");
            Path addingFile = Paths.get(ConsoleHelper.readString());

            zipFileManager.addFile(addingFile);
            ConsoleHelper.writeMessage("Успешно.");

        } catch (PathIsNotFoundException pinfe) {
            ConsoleHelper.writeMessage("Ошибка в имени файла. Проверьте данные.");
        }
    }
}
