package org.example.last.task4;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;

public class Work {

    public static void copyFile(String source, String dest) throws IOException {
        try (FileChannel srcChannel = FileChannel.open(Paths.get(source), StandardOpenOption.READ);
             FileChannel destChannel = FileChannel.open(Paths.get(dest), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            srcChannel.transferTo(0, srcChannel.size(), destChannel);
        }
    }

    public static void main(String[] args) {
        try {
            copyFile("large_file.txt", "copied_file.txt");
            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            System.err.println("Ошибка копирования файла: " + e.getMessage());
        }
    }
}
