package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize = 0;
    private int maxSize = 0;
    private List<Path> foundFiles = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean passRequirements = true;
        if (
            (minSize > 0 && attrs.size() < minSize)
            || (maxSize > 0 && attrs.size() > maxSize)
            || (partOfName != null && !file.getFileName().toString().contains(partOfName))
            || (partOfContent != null && !(new String(Files.readAllBytes(file)).contains(partOfContent)))
            )
            passRequirements = false;
        if (passRequirements)
            foundFiles.add(file);
        // System.err.println(new String(Files.readAllBytes(file)) + " contains? - "+ new String(Files.readAllBytes(file)).contains(partOfContent));
        return FileVisitResult.CONTINUE;

    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
