package day18_advanced_fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class AdvancedFileOp {
    public static void main(String[] args) {

        // just load the file if it exists
        File file = new File("day18_advanced_fileio/data/Employees.txt");

        // Check if the file is loaded or not - ALWAYS CHECK IF FILE EXISTS OR NOT
        // BEFORE TRYING ANY OPERATIONS
        if (file.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File does not exists");
        }

        // File operations
        System.out.println("File name: " + file.getName());
        System.out.println("File Path :" + file.getPath());
        System.out.println("File absolute Path: " + file.getAbsolutePath());
        System.out.println("File size : " + file.length() + "bytes");
        System.out.println("Is file? " + file.isFile() + "\nis Directory? " + file.isDirectory());

        File directory = new File("day18_advanced_fileio/data");

        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory created successfully");
        }

        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            System.out.println("List of files in Directory");

            for (File f : files) {
                System.out.println("- " + f.getName() + (f.isDirectory() ? "(Folder)" : ""));
            }
        } else {
            System.out.println(directory.getName() + " is Empty Directory !!");
        }

        // READING NON BINARY FILES LIKE IMAGES, AUDIO, VIDEO, COMPILED CLASS,
        // SERIALIZED JAVA OBJECTS

        try (FileInputStream fs = new FileInputStream("day18_advanced_fileio/data/image.jpeg")) {
            int byteValue;
            int totalBytes = 0;

            while ((byteValue = fs.read()) != -1) {
                // -1 means the end of the file
                totalBytes++;
                // System.out.println(byteValue);
            }

            System.out.println("Binary file read for total " + totalBytes / 1024 + " KiloBytes");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Exception in IO " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unkonwn excpetion occured: " + e.getMessage());
        }

        // WRITTING TO THE BINARY FILES
        try (FileOutputStream fos = new FileOutputStream("day18_advanced_fileio/data/output.bin")) {
            byte[] data = { 65, 66, 67, 68 };

            fos.write(data);
            System.out.println("Successfully written to file");

        } catch (IOException e) {
            System.err.println("Errorr writing to the file: " + e.getMessage());
        }

        // COPYING BINARY FILES
        try (FileInputStream fis = new FileInputStream("day18_advanced_fileio/data/image.jpeg");
                FileOutputStream fos = new FileOutputStream("day18_advanced_fileio/data/copyImage.jpeg")) {

            byte[] buffer = new byte[8192];
            int byteData;

            while ((byteData = fis.read(buffer)) != -1) {
                fos.write(buffer);

            }

            System.out.println("File successfully copied");
        } catch (FileNotFoundException e) {
            System.err.println("Can not find the file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error in input output: " + e.getMessage());
        }
        // NIO - NON BLOCKING I/O OR NEW I/O
        // Using Path and FIles

        Path path = Paths.get("day18_advanced_fileio/data/employees.txt");

        try {
            if (Files.exists(path)) {
                System.out.println("Files exists");

                long size = Files.size(path);

                System.out.println("File size:" + size + " Bytes");
                System.out.println("Is file readable? " + Files.isReadable(path));
                System.out.println("Is file Writable? " + Files.isWritable(path));

            } else {
                System.out.println("File does not exist");
            }
        } catch (IOException e) {
            System.err.println("IO expection found while getting file: " + e.getMessage());
        }

        // READING ENTIRE FILE AT ONCE
        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // WRITING ENTIRE FILE AS ONCE
        try {
            List<String> lines = Arrays.asList(
                    "Employee Records",
                    "Rushiraj, 50000", "Rushi, 60000", "Bhumik, 40000");

            Files.write(path, lines);
            System.out.println("Emplyee data saved successfully");

            String content = "Madhav, 40000";
            Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("IO exection occured while saving employee data: " + e.getMessage());
        }

        // READING LARGER FILES IN GBS, NO NEED TO LOAD EVERYTHING INTO MEMORY!
        try {
            Files.lines(path).filter(line -> !line.isEmpty()).map(String::toUpperCase).limit(10)
                    .forEach(System.out::println);
        } catch (IOException e) {
        }

        // CSV processing
        Path csvPath = Paths.get("day18_advanced_fileio/data/Employees copy.txt");
        try {
            Files.lines(csvPath).skip(1).forEach(line -> {
                String[] parts = line.split(",");
                String employeeName = parts[0];
                String employeeAge = parts[1];
                String employeeSalary = parts[2];
                String employeeDept = parts[3];

                System.out.println("Employee name: " + employeeName + ", Employee Age: " + employeeAge
                        + ", Employee Salary: " + employeeSalary + ",Employee Department: " + employeeDept);
            });
        } catch (IOException e) {

        }
    }
}
