package org.example.last.task3;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Work {
    public static void main(String[] args) throws IOException {
        String inputFile = "large_input.txt";
        String outputFileIO = "output_io.txt";
        String outputFileNIO = "output_nio.txt";

        long start, end;

        start = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileIO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("IO время: " + (end - start) + " мс");

        start = System.currentTimeMillis();
        try (FileChannel inChannel = FileChannel.open(Paths.get(inputFile), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get(outputFileNIO), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("NIO время: " + (end - start) + " мс");
    }

}
