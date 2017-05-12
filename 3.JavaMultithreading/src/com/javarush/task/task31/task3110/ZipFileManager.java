package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*1. Реализуй приватный метод void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception
  в классе ZipFileManager.
Он должен:
1.1. Создавать InputStream, для файла с именем fileName, расположенным в
директории filePath
1.2. Создавать новый элемент архива ZipEntry, в качестве имени используй fileName, преобразовав его в String
1.3. Копировать данные из InputStream (из п.1.1) в переданный zipOutputStream
1.4. Закрывать элемент архива
1.5. Закрывать InputStream, сделай это с помощью try-with-resources
2. Замени часть кода метода createZip вызовом нового метода addNewZipEntry. Передай значение source.getParent() в параметр filePath,
а source.getFileName() в filename.
3. Реализуй приватный метод void copyData(InputStream in, OutputStream out) throws Exception. Он должен читать данные из in и записывать в out, пока не вычитает все.
4. Замени часть кода метода addNewZipEntry на вызов метода copyData
5. Вернемся к createZip:
5.1. В начале метода проверь, что существует директория (zipFile.getParent()), в которой мы
будем создавать zipFile, если ее нет, то создай ее.
5.2. Если source является обычным файлом (для проверки используй Files.isRegularFile), то оставим просто вызов addNewZipEntry
5.3. Если source является директорией (для проверки используй Files.isDirectory), то:
5.3.1. Создай объект класса файловый менеджер FileManager, в конструктор передадим source
5.3.2. Получи список файлов у файлового менеджера, сохраним его в переменную fileNames
5.3.3. Для всех элементов fileNames, вызови метод addNewZipEntry(zipOutputStream, source, fileName)
5.4. Если source не является ни папкой, ни файлом, то кинь исключение PathIsNotFoundException.

*/
public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        while (in.available() > 0) {
            out.write(in.read());
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        try (InputStream inputStream =
                     Files.newInputStream( filePath.resolve(fileName.toString()) )) {

            zipOutputStream.putNextEntry(new ZipEntry(fileName.toString()));

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();
        }
    }

    public void createZip(Path source)  throws Exception{
        if (!Files.exists(zipFile.getParent()))
            Files.createDirectories(zipFile.getParent());
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            if (Files.isDirectory(source)) {
                FileManager fileManager = new FileManager(source);
                List<Path> fileNames = fileManager.getFileList();
                for (Path fileName :
                        fileNames) {
                    addNewZipEntry(zipOutputStream, source, fileName); // !!!
                }
            } else if (Files.isRegularFile(source)) {
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            } else throw new PathIsNotFoundException();
        }
    }
}
