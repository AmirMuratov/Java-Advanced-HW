package ru.ifmo.ctddev.Muratov.walk;


import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class RecursiveWalk {
    static private final int x0 = 0x811c9dc5;
    static private final int prime = 16777619;
    static private final int bufferSize = 2048;
    private byte[] buffer = new byte[bufferSize];
    OutputStreamWriter writer;
    boolean accessToOutput;

    void errPrint(String message) {
        System.out.println(message);
    }

    void print(String result) {
        try {
            writer.write(result);
        } catch (IOException e) {
            accessToOutput = false;
        }
    }

    void countHash(File file) {
        String hex = "";
        try {
            InputStream is = new FileInputStream(file);
            int result = x0;
            while (is.available() > 0) {
                int size = is.read(buffer, 0, bufferSize);
                for (int i = 0; i < size; i++) {
                    result = (result * prime) ^ (buffer[i] & 0xff);
                }
            }
            hex = String.format("%08x", result);
        } catch (IOException e) {
            errPrint("Cant read file");
            hex = "00000000";
        } finally {
            print(hex + " " + file.toString() + "\n");
        }
    }

    public void walk(String fileName) {
        try {
            Files.walkFileTree(new File(fileName).toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    countHash(file.toFile());
                    if (accessToOutput) {
                        return FileVisitResult.CONTINUE;
                    } else
                        return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    countHash(file.toFile());
                    if (accessToOutput) {
                        return FileVisitResult.CONTINUE;
                    } else
                        return FileVisitResult.TERMINATE;
                }
            });
        } catch (IOException e) {
        }
    }

    public void run(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            errPrint("Usage: java RecursiveWalk <input file> <output file>");
            return;
        }
        File input = new File(args[0]);
        File output = new File(args[1]);

        try (
                InputStreamReader isr = new InputStreamReader(new FileInputStream(input), "UTF-8");
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(output), "UTF-8");
                BufferedReader br = new BufferedReader(isr)
        ) {
            writer = osw;
            accessToOutput = true;
            String fileName;
            while ((fileName = br.readLine()) != null) {
                walk(fileName);
                if (!accessToOutput) {
                    throw new IOException();
                }
            }
        } catch (UnsupportedEncodingException e) {
            errPrint("Your system doesn't support UTF-8 encoding");
        } catch (FileNotFoundException e) {
            errPrint("Input file not found or can't create output file");
        } catch (IOException e) {
            errPrint("Can't write in output file or Can't read from input file");
        }


    }

    public static void main(String[] args) {
        new RecursiveWalk().run(args);
    }
}
