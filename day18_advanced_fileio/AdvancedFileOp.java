package day18_advanced_fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
                System.out.println(f.getName() + (f.isDirectory() ? "(Folder)" : ""));
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

            System.out.println("Non binary file read for total " + totalBytes / 1024 + " KiloBytes");
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

    }
}
