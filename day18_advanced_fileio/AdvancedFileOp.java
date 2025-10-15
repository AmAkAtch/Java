package day18_advanced_fileio;

import java.io.File;

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
    }
}
