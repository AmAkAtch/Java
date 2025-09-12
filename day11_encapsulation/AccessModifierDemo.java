package day11_encapsulation;

// DEMONSTRATION CLASS
public class AccessModifierDemo {
    public static void main(String[] args) {
        System.out.println("=== ACCESS MODIFIERS COMPREHENSIVE DEMONSTRATION ===\n");

        // Create employee and manager
        Employee employee = new Employee("EMP001", "Alice Johnson", "Engineering", 75000);
        Manager manager = new Manager("MGR001", "Bob Smith", "Engineering", 90000, 5);

        // PUBLIC access - anyone can do this
        employee.displayPublicInfo();

        // Authorized salary access
        System.out.println("\n--- SALARY ACCESS DEMONSTRATION ---");
        employee.getSalaryForAuthorizedUser("UNAUTHORIZED"); // Will be denied
        double salary = employee.getSalaryForAuthorizedUser("HR_ADMIN"); // Will work

        // Salary modification
        employee.setSalary(80000, "UNAUTHORIZED"); // Will be denied
        employee.setSalary(80000, "HR_ADMIN"); // Will work

        // HR system processing (same package)
        HRSystem hrSystem = new HRSystem();
        hrSystem.processEmployee(employee);

        // Manager-specific operations
        manager.displayManagerInfo();
        manager.calculateBonus();

        // External system processing (limited access)
        ExternalPayrollSystem payrollSystem = new ExternalPayrollSystem();
        payrollSystem.processPayroll(employee);

        System.out.println("\n=== KEY LEARNING POINTS ===");
        System.out.println("1. PRIVATE: Maximum protection - only same class");
        System.out.println("2. PACKAGE-PRIVATE: Same package access");
        System.out.println("3. PROTECTED: Same package + inheritance");
        System.out.println("4. PUBLIC: Universal access");
        System.out.println("5. Always use the most restrictive access that meets your needs");
        System.out.println("6. Combine access control with validation for robust security");
    }
}