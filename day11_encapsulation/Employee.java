package day11_encapsulation;

// COMPREHENSIVE ACCESS MODIFIERS DEMONSTRATION
// This shows all four types of access control in action

// PACKAGE: com.company.hr (imagine this is in the hr package)
public class Employee {

    // PUBLIC - Accessible from anywhere in the application
    public String employeeId; // Employee ID can be seen by anyone
    public String department; // Department information is public

    // PRIVATE - Only accessible within this Employee class
    private double salary; // Salary is confidential - only this class can access
    private String socialSecurityNumber; // SSN is highly sensitive - maximum protection
    private String bankAccountNumber; // Banking info needs maximum security

    // PROTECTED - Accessible within package AND by subclasses (inheritance)
    protected String fullName; // Name can be accessed by Manager, Developer classes
    protected int yearsOfExperience; // Experience accessible to employee subclasses

    // PACKAGE-PRIVATE (no modifier) - Accessible within same package only
    String phoneNumber; // Available to HR package classes only
    String address; // Address visible within HR system only
    boolean isActive; // Status information for HR use

    // CONSTRUCTOR - Usually public so objects can be created
    public Employee(String employeeId, String fullName, String department, double salary) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;

        // Validation for salary
        if (salary < 0) {
            System.out.println("Error: Salary cannot be negative");
            this.salary = 0;
        } else {
            this.salary = salary;
        }

        this.isActive = true; // New employees are active

        System.out.println("Employee created: " + fullName + " (ID: " + employeeId + ")");
    }

    // PUBLIC GETTERS - Anyone can read these values
    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    // PROTECTED GETTER - Subclasses and package classes can access
    protected String getFullName() {
        return fullName;
    }

    // PRIVATE GETTERS - Only this class can use these methods
    // Note: We might make salary getter public with proper authorization checks
    private double getSalary() {
        return salary;
    }

    // PUBLIC METHOD with access control - Smart approach
    public double getSalaryForAuthorizedUser(String authorizationCode) {
        // In real application, this would verify the user's permission level
        if ("HR_ADMIN".equals(authorizationCode) || "PAYROLL_MANAGER".equals(authorizationCode)) {
            return salary;
        } else {
            System.out.println("Access denied: Insufficient privileges to view salary");
            return -1; // Indicates access denied
        }
    }

    // PUBLIC SETTERS with validation and authorization
    public void setSalary(double newSalary, String authorizationCode) {
        // Authorization check
        if (!"HR_ADMIN".equals(authorizationCode)) {
            System.out.println("Error: Only HR Admin can modify salary");
            return;
        }

        // Validation
        if (newSalary < 0) {
            System.out.println("Error: Salary cannot be negative");
            return;
        }

        // Business rule: Salary changes over 50% require special approval
        double changePercentage = Math.abs((newSalary - this.salary) / this.salary) * 100;
        if (changePercentage > 50) {
            System.out.println(
                    "Warning: Large salary change detected (" + String.format("%.1f", changePercentage) + "%)");
            System.out.println("This change requires additional approval in real system");
        }

        System.out.println("Salary updated from $" + this.salary + " to $" + newSalary);
        this.salary = newSalary;
    }

    // PACKAGE-PRIVATE METHOD - Only HR package classes can use this
    void updateContactInfo(String phone, String address) {
        this.phoneNumber = phone;
        this.address = address;
        System.out.println("Contact information updated for: " + fullName);
    }

    // PROTECTED METHOD - Subclasses can override or use this
    protected void calculateBonus() {
        double bonus = salary * 0.1; // 10% bonus base calculation
        System.out.println("Base bonus calculated: $" + bonus + " for " + fullName);
    }

    // PUBLIC METHOD - Display basic public information
    public void displayPublicInfo() {
        System.out.println("\n=== PUBLIC EMPLOYEE INFORMATION ===");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("===================================");
    }

    // PACKAGE-PRIVATE METHOD - Full info for HR package
    void displayHRInfo() {
        System.out.println("\n=== HR EMPLOYEE INFORMATION ===");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Full Name: " + fullName);
        System.out.println("Department: " + department);
        System.out.println("Phone: " + (phoneNumber != null ? phoneNumber : "Not provided"));
        System.out.println("Address: " + (address != null ? address : "Not provided"));
        System.out.println("Years of Experience: " + yearsOfExperience);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("===============================");
    }
}

// MANAGER class - Demonstrates PROTECTED access through inheritance
class Manager extends Employee {

    private int teamSize;
    private double managementBonus;

    public Manager(String employeeId, String fullName, String department, double salary, int teamSize) {
        super(employeeId, fullName, department, salary); // Call parent constructor
        this.teamSize = teamSize;
    }

    // This method can access PROTECTED members from Employee class
    public void displayManagerInfo() {
        System.out.println("\n=== MANAGER INFORMATION ===");
        System.out.println("Manager ID: " + employeeId); // PUBLIC - accessible
        System.out.println("Manager Name: " + fullName); // PROTECTED - accessible because we inherit
        System.out.println("Department: " + department); // PUBLIC - accessible
        System.out.println("Experience: " + yearsOfExperience + " years"); // PROTECTED - accessible
        System.out.println("Team Size: " + teamSize);

        // We CANNOT access private members:
        // System.out.println("Salary: " + salary); // ERROR: private not accessible
        // System.out.println("SSN: " + socialSecurityNumber); // ERROR: private not
        // accessible

        System.out.println("===========================");
    }

    // Override the protected calculateBonus method
    @Override
    protected void calculateBonus() {
        // We can access protected members here
        double baseBonus = getSalaryForAuthorizedUser("PAYROLL_MANAGER") * 0.1;
        double managementBonus = teamSize * 1000; // $1000 per team member
        double totalBonus = baseBonus + managementBonus;

        System.out.println("Manager bonus calculation:");
        System.out.println("  Base bonus: $" + baseBonus);
        System.out.println("  Management bonus: $" + managementBonus);
        System.out.println("  Total bonus: $" + totalBonus + " for " + fullName);
    }
}

// HR SYSTEM class - Same package, shows package-private access
class HRSystem {

    public void processEmployee(Employee emp) {
        System.out.println("\n=== HR PROCESSING ===");

        // PUBLIC access - works fine
        System.out.println("Processing employee: " + emp.employeeId);
        System.out.println("Department: " + emp.department);

        // PACKAGE-PRIVATE access - works because we're in same package
        emp.phoneNumber = "555-0123";
        emp.address = "123 Main St, City, State";
        emp.isActive = true;

        // Use package-private method
        emp.updateContactInfo("555-0124", "456 Oak Ave, City, State");

        // Display HR info using package-private method
        emp.displayHRInfo();

        // PROTECTED access works in same package
        System.out.println("Employee name (protected): " + emp.fullName);

        // PRIVATE access - These would cause compilation errors:
        // System.out.println("Salary: " + emp.salary); // ERROR: private
        // System.out.println("SSN: " + emp.socialSecurityNumber); // ERROR: private

        System.out.println("===================");
    }
}

// EXTERNAL SYSTEM class - Different package, limited access
class ExternalPayrollSystem {

    public void processPayroll(Employee emp) {
        System.out.println("\n=== EXTERNAL PAYROLL PROCESSING ===");

        // PUBLIC access only - this would be in a different package
        System.out.println("Processing payroll for: " + emp.employeeId);
        System.out.println("Department: " + emp.department);

        // Try to get salary through authorized method
        double salary = emp.getSalaryForAuthorizedUser("PAYROLL_MANAGER");
        if (salary > 0) {
            System.out.println("Salary retrieved for payroll: $" + salary);
        }

        // These would cause compilation errors in different package:
        // System.out.println("Name: " + emp.fullName); // ERROR: protected not
        // accessible
        // System.out.println("Phone: " + emp.phoneNumber); // ERROR: package-private
        // not accessible
        // emp.updateContactInfo("555-999", "New Address"); // ERROR: package-private
        // method

        System.out.println("===================================");
    }
}
