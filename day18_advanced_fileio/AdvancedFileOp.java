package day18_advanced_fileio;

import java.io.File;

public class AdvancedFileOp {
    public static void main(String[] args) {

        // just load the file if it exists
        File file = new File("Employees.txt");

        // Check if the file is loaded or not
        if (file.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File does not exists");
        }
    }
}
