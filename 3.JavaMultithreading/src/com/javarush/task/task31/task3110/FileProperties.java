package com.javarush.task.task31.task3110;

/**
 * Created by Данил on 13.05.2017.
 * 1. Создай класс FileProperties
 2. Добавь в него приватные переменные класса:
 2.1. Имя String name
 2.2. Размер в байтах long size
 2.3. Размер после сжатия в байтах long compressedSize
 2.4. Метод сжатия int compressionMethod
 3. Добавь гетеры для них
 4. Добавь конструктор FileProperties(String name, long size, long compressedSize, int compressionMethod)
 5. Добавь метод long getCompressionRatio(), который будет считать степень сжатия по формуле: 100 — ((compressedSize * 100) / size)
 6. Перегрузи метод String toString(), чтобы он возвращал строку по шаблону: “name size Kb
 (compressedSize Kb) сжатие: compressionRatio%”, если размер size больше нуля, иначе он должен
 вернуть только имя файла. Нулевой размер может быть, например, у директории. Не забудь
 перевести байты в килобайты, а их не столько же, сколько граммов в килограмме, и даже не
 столько, сколько блинов у меня на столе… Хм, похоже мне пора перекусить…
 */
public class FileProperties {
    private String name;
    private long size;
    private long compressedSize;
    private int compressionMethod;

    @Override
    public String toString() {
        if (size > 0) return String.format("%s %d Kb (%d Kb) сжатие: %d%%", name, (int)(size / 1024), (int)(compressedSize / 1024), getCompressionRatio());
        else return name;
    }

    public long getCompressionRatio() {
        return  100 - ((compressedSize * 100) / size);
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getCompressedSize() {
        return compressedSize;
    }

    public int getCompressionMethod() {
        return compressionMethod;
    }

    public FileProperties(String name, long size, long compressedSize, int compressionMethod) {

        this.name = name;
        this.size = size;
        this.compressedSize = compressedSize;
        this.compressionMethod = compressionMethod;
    }
}
