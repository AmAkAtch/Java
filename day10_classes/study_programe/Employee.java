package day10_classes.study_programe;

// Employee.java - This is our blueprint for all employees
public class Employee {

    // PROPERTIES - What every employee HAS (instance variables)
    private String employeeName; // Employee's full name
    private int employeeID; // Unique identifier
    private double annualSalary; // Yearly salary in dollars
    private String department; // Which department they work in
    private String position; // Job title
    private int yearsOfService; // How long they've worked here

    // CONSTRUCTOR - Special method that creates new employee objects
    public Employee(String name, int id, int salary, String dept, String pos) {
        // Initialize all properties when creating new employee
        this.employeeName = name; // 'this' refers to the current object being created
        this.employeeID = id;
        this.annualSalary = salary;
        this.department = dept;
        this.position = pos;
        this.yearsOfService = 0; // New employees start with 0 years

        System.out.println("New employee created: " + name + " (ID: " + id + ")");
    }

    // METHODS - What every employee CAN DO (behavior)

    /**
     * Calculates annual bonus based on salary and years of service
     * Business rule: 5% of salary + 1% extra for each year of service
     */
    public double calculateAnnualBonus() {
        double baseBonus = annualSalary * 0.05; // 5% base bonus
        double serviceBonus = annualSalary * 0.01 * yearsOfService; // 1% per year
        double totalBonus = baseBonus + serviceBonus;

        System.out.println(employeeName + "'s bonus calculation:");
        System.out.println("- Base bonus (5%): $" + String.format("%.2f", baseBonus));
        System.out.println("- Service bonus: $" + String.format("%.2f", serviceBonus));
        System.out.println("- Total bonus: $" + String.format("%.2f", totalBonus));

        return totalBonus;
    }

    /**
     * Gives employee a salary raise
     */
    public void giveRaise(double raiseAmount) {
        if (raiseAmount > 0) {
            double oldSalary = annualSalary;
            annualSalary += raiseAmount;
            System.out.println(employeeName + " received a raise!");
            System.out.println("Previous salary: $" + String.format("%.2f", oldSalary));
            System.out.println("New salary: $" + String.format("%.2f", annualSalary));
        } else {
            System.out.println("Raise amount must be positive!");
        }
    }

    /**
     * Increments years of service (called during annual review)
     */
    public void incrementYearsOfService() {
        yearsOfService++;
        System.out.println(employeeName + " now has " + yearsOfService + " years of service.");
    }

    /**
     * Displays complete employee information
     */
    public void displayEmployeeInfo() {
        System.out.println("\n=== EMPLOYEE INFORMATION ===");
        System.out.println("Name: " + employeeName);
        System.out.println("ID: " + employeeID);
        System.out.println("Position: " + position);
        System.out.println("Department: " + department);
        System.out.println("Annual Salary: $" + String.format("%.2f", annualSalary));
        System.out.println("Years of Service: " + yearsOfService);
        System.out.println("==========================\n");
    }

    // GETTER METHODS - Allow other classes to READ private properties
    public String getEmployeeName() {
        return employeeName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }
}