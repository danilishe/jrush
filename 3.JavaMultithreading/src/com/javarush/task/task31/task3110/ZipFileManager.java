package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ZipFileManager {
    // Полный путь zip файла
    private final Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    /*1. Добавь публичный метод для удаления файлов из архива void removeFiles(List<Path> pathList) throws Exception
    в класс ZipFileManager. В pathList будет передаваться список относительных путей на файлы внутри архива.
Он должен:
1.1. Бросать исключение WrongZipFileException, если файл архива не существует
1.2. Создать временный файл архива с помощью метода createTempFile() класса Files
1.3. Пройтись по всем файлам оригинального архива, проверить, есть ли текущий файл в списке на удаление.
— Если файл есть в списке, вывести сообщение, что мы удалили файл с таким-то именем и перейти к следующему файлу.
— Если файла нет в списке на удаление, переписать его в новый архив
1.4. Заменить оригинальный файл архива временным, в который мы записали нужные файлы.
Это нужно сделать с помощью метода move() класса Files
2. Добавь публичный метод void removeFile(Path path) throws Exception в класс ZipFileManager,
который будет вызывать метод removeFiles, создавая список из одного элемента.
Это можно сделать с помощью метода singletonList() класса Collections. Посмотри, как он работает.
3. Реализуй метод execute() класса ZipRemoveCommand, создав объект класса ZipFileManager,
спросив пользователя из какого архива и какой файл будем удалять, и вызвав метод removeFile().
Все остальное, как и в других командах. Исключение PathIsNotFoundException можно не ловить, т.к. метод removeFile() не должен его кидать.
4. Запусти программу и проверить, что удаление файла из архива работает.

*/
    public void removeFile(Path path) throws Exception {
        removeFiles(Collections.singletonList(path));
    }
    public void removeFiles(List<Path> pathList) throws Exception {
        if (!Files.isRegularFile(zipFile)) throw new WrongZipFileException();
        // создаём поток во временный файл
        Path tempZipFile =  Files.createTempFile(null, null);
        ZipInputStream origZip = new ZipInputStream(Files.newInputStream(zipFile));
        ZipOutputStream tempZip =  new ZipOutputStream (Files.newOutputStream(tempZipFile));
        ZipEntry origZipEntry;
        while ((origZipEntry = origZip.getNextEntry()) != null) {
            boolean isInList = false;
            for (Path path :
                    pathList) {
                if (path.equals(Paths.get(origZipEntry.getName()))) {
                    isInList = true;
                    ConsoleHelper.writeMessage("'"+path+"' удалён!");
                }
            }
            if (!isInList && !origZipEntry.isDirectory()) {
                tempZip.putNextEntry(
                        new ZipEntry(origZipEntry.getName())
                );
                copyData(origZip, tempZip);
                tempZip.closeEntry();
            }
            origZip.closeEntry();
        }
        origZip.close();
        tempZip.close();

        Files.move(tempZipFile, zipFile, REPLACE_EXISTING);
    }
    public void createZip(Path source) throws Exception {
        // Проверяем, существует ли директория, где будет создаваться архив
        // При необходимости создаем ее
        Path zipDirectory = zipFile.getParent();
        if (Files.notExists(zipDirectory))
            Files.createDirectories(zipDirectory);

        // Создаем zip поток
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if (Files.isDirectory(source)) {
                // Если архивируем директорию, то нужно получить список файлов в ней
                FileManager fileManager = new FileManager(source);
                List<Path> fileNames = fileManager.getFileList();

                // Добавляем каждый файл в архив
                for (Path fileName : fileNames)
                    addNewZipEntry(zipOutputStream, source, fileName);

            } else if (Files.isRegularFile(source)) {

                // Если архивируем отдельный файл, то нужно получить его директорию и имя
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            } else {

                // Если переданный source не директория и не файл, бросаем исключение
                throw new PathIsNotFoundException();
            }
        }
    }

    public void extractAll(Path outputFolder) throws Exception {
        // Проверяем существует ли zip файл
        if (!Files.isRegularFile(zipFile)) {
            throw new WrongZipFileException();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            // Создаем директорию вывода, если она не существует
            if (Files.notExists(outputFolder))
                Files.createDirectories(outputFolder);

            // Проходимся по содержимому zip потока (файла)
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                Path fileFullName = outputFolder.resolve(fileName);

                // Создаем необходимые директории
                Path parent = fileFullName.getParent();
                if (Files.notExists(parent))
                    Files.createDirectories(parent);

                try (OutputStream outputStream = Files.newOutputStream(fileFullName)) {
                    copyData(zipInputStream, outputStream);
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }

    public List<FileProperties> getFilesList() throws Exception {
        // Проверяем существует ли zip файл
        if (!Files.isRegularFile(zipFile)) {
            throw new WrongZipFileException();
        }

        List<FileProperties> files = new ArrayList<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                // Поля "размер" и "сжатый размер" не известны, пока элемент не будет прочитан
                // Давайте вычитаем его в какой-то выходной поток
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                copyData(zipInputStream, baos);

                FileProperties file = new FileProperties(zipEntry.getName(), zipEntry.getSize(), zipEntry.getCompressedSize(), zipEntry.getMethod());
                files.add(file);
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        return files;
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        Path fullPath = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(fullPath)) {
            ZipEntry entry = new ZipEntry(fileName.toString());

            zipOutputStream.putNextEntry(entry);

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();
        }
    }
/*
*  @param in
*  @param out
*  */
    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
