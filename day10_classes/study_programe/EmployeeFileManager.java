package day10_classes.study_programe;

import java.io.*;
import java.util.Scanner;

public class EmployeeFileManager {

    private static final String EMPLOYEE_FILE = "employee_objects.txt";

    public static void main(String[] args) {
        System.out.println("=== EMPLOYEE OBJECT FILE MANAGEMENT SYSTEM ===\n");

        // Create some employee objects
        Employee[] employees = createSampleEmployees();

        // Save all employees to file
        saveEmployeesToFile(employees);

        // Read employees back from file
        readEmployeesFromFile();
    }

    /**
     * Creates sample employee objects for demonstration
     */
    public static Employee[] createSampleEmployees() {
        System.out.println("Creating sample employees...\n");

        Employee[] employees = new Employee[4];

        employees[0] = new Employee("Alice Chen", 3001, 95000, "Engineering", "Senior Software Engineer");
        employees[1] = new Employee("Marcus Johnson", 3002, 78000, "Marketing", "Marketing Manager");
        employees[2] = new Employee("Sofia Rodriguez", 3003, 88000, "Finance", "Financial Analyst");
        employees[3] = new Employee("David Kim", 3004, 92000, "Engineering", "DevOps Engineer");

        // Give some employees experience and raises
        employees[0].incrementYearsOfService();
        employees[0].incrementYearsOfService(); // Alice has 2 years
        employees[0].giveRaise(5000);

        employees[2].incrementYearsOfService(); // Sofia has 1 year

        System.out.println("\n✓ All sample employees created and configured.\n");
        return employees;
    }

    /**
     * Saves employee objects to a file
     */
    public static void saveEmployeesToFile(Employee[] employees) {
        System.out.println("Saving employees to file...");

        try {
            FileWriter writer = new FileWriter(EMPLOYEE_FILE, false); // Overwrite existing file

            // Write file header
            writer.write("COMPANY EMPLOYEE DATABASE\n");
            writer.write("Generated: " + java.time.LocalDateTime.now() + "\n");
            writer.write("Total Employees: " + employees.length + "\n");
            writer.write("=".repeat(50) + "\n\n");

            // Write each employee's complete information
            for (int i = 0; i < employees.length; i++) {
                Employee emp = employees[i];

                writer.write("EMPLOYEE #" + (i + 1) + "\n");
                writer.write("Name: " + emp.getEmployeeName() + "\n");
                writer.write("ID: " + emp.getEmployeeID() + "\n");
                writer.write("Position: " + emp.getPosition() + "\n");
                writer.write("Department: " + emp.getDepartment() + "\n");
                writer.write("Salary: $" + String.format("%.2f", emp.getAnnualSalary()) + "\n");
                writer.write("Years of Service: " + emp.getYearsOfService() + "\n");

                // Calculate and save bonus information
                double bonus = emp.calculateAnnualBonus();
                writer.write("Current Bonus: $" + String.format("%.2f", bonus) + "\n");

                writer.write("-".repeat(30) + "\n\n");
            }

            writer.write("END OF EMPLOYEE DATABASE\n");
            writer.close();

            System.out.println("✓ Successfully saved " + employees.length + " employees to " + EMPLOYEE_FILE + "\n");

        } catch (IOException e) {
            System.out.println("✗ Error saving employees to file: " + e.getMessage());
            System.out.println("Please check file permissions and available disk space.\n");
        }
    }

    /**
     * Reads and displays employee information from file
     */
    public static void readEmployeesFromFile() {
        System.out.println("Reading employees from file...");

        File employeeFile = new File(EMPLOYEE_FILE);
        Scanner fileReader = null;

        try {
            if (!employeeFile.exists()) {
                System.out.println("✗ Employee file not found!");
                System.out.println("Please run the save operation first.\n");
                return;
            }

            fileReader = new Scanner(employeeFile);
            System.out.println("✓ File found. Displaying contents:\n");

            System.out.println("=".repeat(60));

            int lineCount = 0;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                System.out.println(line);
                lineCount++;

                // Add extra spacing after employee records for readability
                if (line.contains("Current Bonus:")) {
                    System.out.println(); // Extra blank line
                }
            }

            System.out.println("=".repeat(60));
            System.out.println("✓ Successfully displayed " + lineCount + " lines from employee database.\n");

        } catch (FileNotFoundException e) {
            System.out.println("✗ Employee file not found: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("✗ Unexpected error reading employee file: " + e.getMessage());

        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }
}