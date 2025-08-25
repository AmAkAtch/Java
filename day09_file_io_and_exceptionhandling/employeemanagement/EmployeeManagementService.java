//Business project that reads, saves and loads employee data
package day09_file_io_and_exceptionhandling.employeemanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*Current Problems
 * 1. While Updating Program is not checking for existing details
 * 2. Add comments in Code.
 */

/*Features to add 
 * 1. Give user time before displaying menu like propmt to continue...
 * 2. Automatic Employee Numer assigning. 
*/

public class EmployeeManagementService {

    static String DIVIDER = "===========================================";
    static String[] empNames = new String[100];
    static int[] empIds = new int[100];
    static int currentIndex = 0;
    static File empFile = new File("employee_details.txt");

    public static class EmployeeInput {
        public String empName;
        public int empId;

        public EmployeeInput(String empName, int empId) {
            this.empId = empId;
            this.empName = empName;
        }
    }

    public static void loadEmployees() {
        try {
            Scanner fileScanner = new Scanner(empFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                String type = parts[0];

                switch (type) {
                    case "Employee name":
                        empNames[currentIndex] = parts[1].trim();
                        break;
                    case "Employee id":
                        empIds[currentIndex] = Integer.parseInt(parts[1].trim());
                        currentIndex++;
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Employee Details file loaded successfully...");
            System.out.println(Arrays.toString(empNames));
            System.out.println(Arrays.toString(empIds));
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found while reading the employee File");
        }
    }

    public static void main(String[] args) {

        loadEmployees();

        // declare variables
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        // Check if the file exists
        if (!empFile.exists()) {
            System.out.println("File doesn't exist Please check path");
            return;
        }

        greetUser();

        // Give selection menu to user until they quit
        do {
            try {
                employeeMenu();
                choice = scanner.nextInt();
                scanner.nextLine();
                executeUserChoice(choice, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        } while (choice != 5);

    }

    public static void greetUser() {
        System.out.println("Welcome to Employee management System!");
        System.out.println(DIVIDER);
    }

    public static void employeeMenu() {
        System.out.println("1: Add Employee");
        System.out.println("2: Remove Employee");
        System.out.println("3: Update Employee");
        System.out.println("4: View all Employees");
        System.out.println("5: Exit");
        System.out.println(DIVIDER);
        System.out.println("Enter your choice...");
    }

    public static void executeUserChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                addEmployee(scanner);
                break;
            case 2:
                removeEmployee(scanner);
                break;
            case 3:
                updateSingleEmployee(scanner);
                break;
            case 4:
                viewEmployess();
                break;
            default:
                break;
        }
    }

    public static EmployeeInput enterDetails(Scanner scanner) {
        try {
            System.out.println("Enter the Username:");
            String empName = scanner.nextLine();
            System.out.println("Enter Employee id:");
            int empId = scanner.nextInt();
            scanner.nextLine();

            return new EmployeeInput(empName, empId);

        } catch (InputMismatchException e) {
            System.out.println("Invalid Input | Try again with valid inputs...");
            scanner.nextLine();
            return null;
        }
    }

    public static void addEmployee(Scanner scanner) {
        try {
            EmployeeInput input = enterDetails(scanner);

            validateAndAddToArray(input.empName, input.empId);
            writeEmployee(input.empName, input.empId);

        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

    }

    public static void removeEmployee(Scanner scanner) {
        EmployeeInput input = enterDetails(scanner);
        for (int i = 0; i < empNames.length; i++) {
            if (input.empName.equalsIgnoreCase(empNames[i])) {
                if (input.empId == empIds[i]) {
                    System.out.println("Found Employee you want to delete.");
                    String confirmation;
                    int maxTries = 2;
                    do {
                        System.out.println("Type 'Confirm' to Detele");
                        confirmation = scanner.nextLine();
                        if (confirmation.equalsIgnoreCase("confirm")) {
                            for (int j = i; j < empNames.length - 1; j++) {
                                empNames[j] = empNames[j + 1];
                                empIds[j] = empIds[j + 1];
                            }
                            empNames[empNames.length - 1] = null;
                            empIds[empNames.length - 1] = 0;
                            currentIndex--;
                            updateAllEmployees();
                            System.out.println("Employee deleted successfullly");
                            System.out.println(DIVIDER);
                            return;
                        }
                        maxTries++;
                    } while (maxTries < 3);
                    System.out.println("Nothing to delete");
                    System.out.println(DIVIDER);
                    return;
                }
            }
        }
        System.out.println("No matching Employee with Employee Name: " + input.empName + " and Employee Id: "
                + input.empId + " Found");
    }

    public static void validateAndAddToArray(String empName, int empId) {
        // add employee to session array
        for (int id : empIds) {
            if (empId == id) {
                throw new RuntimeException("Matching employee ID already present. Try again with a different ID.");
            }
        }

        for (String name : empNames) {
            if (empName.equalsIgnoreCase(name)) {
                throw new RuntimeException("Matching employee name already present. Try again with a different Name.");
            }
        }
        empNames[currentIndex] = empName;
        empIds[currentIndex] = empId;
        currentIndex++;
    }

    public static void writeEmployee(String empName, int empId) {
        try {
            FileWriter fileWriter = new FileWriter("employee_details.txt", true);
            fileWriter.write("Employee name: " + empName + "\n");
            fileWriter.write("Employee id: " + empId + "\n");
            fileWriter.close();

            System.out.println("Employee added and written to file successfully");
            System.out.println(DIVIDER);
        } catch (IOException e) {
            System.out.println("File not found or Error writing to file...");
            System.out.println(DIVIDER);
        }
    }

    public static void updateSingleEmployee(Scanner scanner) {
        EmployeeInput input = enterDetails(scanner);

        for (int i = 0; i < currentIndex; i++) {
            if (input.empName.equalsIgnoreCase(empNames[i])) {
                if (input.empId == empIds[i]) {
                    System.out.println(DIVIDER);
                    System.out.println("Enter New Employee Info");
                    EmployeeInput newInput = enterDetails(scanner);
                    empIds[i] = newInput.empId;
                    empNames[i] = newInput.empName.trim();
                    updateAllEmployees();
                    System.out.println(DIVIDER);
                    return;
                }
                System.out.println("Matching Employee with the Employee name " + empNames[i] + "is found");
                System.out.println(
                        "But employee id " + empIds[i] + " from entered employee id " + input.empId
                                + " is found different");
                System.out.println(DIVIDER);
            }
        }
        System.out.println("No employee was perfect match for update, try again with different details");
        System.out.println(DIVIDER);
    }

    public static void updateAllEmployees() {

        try {
            FileWriter fileWriter = new FileWriter("employee_details.txt");
            for (int i = 0; i < currentIndex; i++) {
                fileWriter.write("Employee name: " + empNames[i] + "\n");
                fileWriter.write("Employee id: " + empIds[i] + "\n");
            }
            fileWriter.close();

            System.out.println("Employees added and written to file successfully");
        } catch (IOException e) {
            System.out.println("File not found or Error writing to file...");
        }

    }

    public static void viewEmployess() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.println("Employee Name: " + empNames[i]);
            System.out.println("EmployeeId: " + empIds[i]);
            System.out.println(DIVIDER);
        }
        System.out.println("Totla Number of Employees: " + currentIndex);
        System.out.println(DIVIDER);
    }
}
