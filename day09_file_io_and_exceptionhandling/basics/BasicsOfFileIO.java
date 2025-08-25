//This program saves and reads employee data from the file

package day09_file_io_and_exceptionhandling.basics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BasicsOfFileIO {
    public static void main(String[] args) {
        saveEmployeeData();
        readEmployeeData();
    }

    public static void saveEmployeeData() {
        try {
            // Create a simple file writer object
            FileWriter fileWriter = new FileWriter("employee_records.txt");

            /*
             * Write the files one by one
             */
            fileWriter.write("Total Employees: 20");
            fileWriter.write("id: 1001\n");
            fileWriter.write("name: Rushiraj\n");
            fileWriter.write("id: 1002\n");
            fileWriter.write("name: Bhumik\n");
            fileWriter.write("id: 1003\n");
            fileWriter.write("name: Madhav\n");
            fileWriter.write("id: 1004\n");
            fileWriter.write("name: Wrath\n");

            fileWriter.close(); // This closes the File Writer and saves the data
        } catch (IOException e) {
            System.out.println("Exception occured while writing file");
        }
    }

    // Method to Read Employee data from the employee_records text document
    public static void readEmployeeData() {
        /*
         * This method takes creates file object for the employee_records.txt
         * Then after confirming if file exists or not create a new scanner to read
         * lines from the file
         * Before reading file we create two 2 arrays of fixed length for testing and to
         * keep a count of employees.
         */
        try {
            File file = new File("employee_records.txt");

            if (!file.exists()) {
                System.out.println("File Doesn't exists, Check the file name and try again!");
                return;
            }

            Scanner scanner = new Scanner(file);
            int count = 0;

            int[] ids = new int[10];
            String[] names = new String[10];

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                String value = parts[0].trim().toUpperCase();

                switch (value) {
                    case "ID":
                        ids[count] = Integer.parseInt(parts[1].trim());
                        break;
                    case "NAME":
                        names[count] = parts[1].trim();
                        count++;
                    default:
                        break;
                }

            }

            System.out.println(Arrays.toString(ids));
            System.out.println(Arrays.toString(names));
            scanner.close();

        } catch (NumberFormatException e) {
            System.out.println("Invalid Number format for one of the employee IDs");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Mac employee count reached, increase the limit of emp array");
        } catch (FileNotFoundException e) {
        }
    }

}
