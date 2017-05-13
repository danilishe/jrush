package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Paths;

/**
 * Created by Данил on 09.05.2017.
 */
public abstract class ZipCommand implements Command {
        @Override
        public void execute() throws Exception {

        };

/*
2.1. Просить пользователя ввести полный путь файла архива
2.2. Считывать введенный путь в переменную типа String
2.3. Используя введенный String формировать путь Path
2.4. Создать объект ZipFileManager, передав в конструктор полученный путь
2.5. Вернуть созданный объект

*/


        public ZipFileManager getZipFileManager() throws Exception {
                ConsoleHelper.writeMessage("Введите полноый путь для архива: ");

                ZipFileManager zipFileManager = new ZipFileManager(
                        Paths.get(
                                ConsoleHelper.readString()));
                return zipFileManager;
        };

}
