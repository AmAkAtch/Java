package day12_inheritance;

// COMPREHENSIVE INHERITANCE DEMONSTRATION
// This shows how inheritance works in a real business application

// PARENT CLASS (SUPERCLASS) - Contains common functionality
public class Employee {
    // PROTECTED fields - accessible to subclasses but not to outside world
    protected String employeeId;
    protected String fullName;
    protected String department;
    protected double baseSalary;
    protected int yearsOfExperience;
    protected boolean isActive;

    // PRIVATE fields - even subclasses cannot access directly
    private String socialSecurityNumber;
    private String emergencyContact;

    // STATIC field - shared by all employees and subclasses
    private static int nextEmployeeNumber = 1001;

    // CONSTRUCTOR - Sets up the basic employee information
    public Employee(String fullName, String department, double baseSalary) {
        // Generate unique employee ID
        this.employeeId = "EMP" + nextEmployeeNumber++;
        this.fullName = fullName;
        this.department = department;

        // Validation for salary
        if (baseSalary < 0) {
            System.out.println("Error: Base salary cannot be negative. Setting to 0.");
            this.baseSalary = 0;
        } else {
            this.baseSalary = baseSalary;
        }

        this.yearsOfExperience = 0; // New employee starts with 0 experience
        this.isActive = true; // New employees are active by default

        System.out.println("Employee created: " + fullName + " (ID: " + employeeId + ")");
    }

    // OVERLOADED CONSTRUCTOR - More detailed employee creation
    public Employee(String fullName, String department, double baseSalary, int yearsOfExperience) {
        this(fullName, department, baseSalary); // Call the main constructor

        // Additional validation for years of experience
        if (yearsOfExperience < 0) {
            System.out.println("Error: Years of experience cannot be negative. Setting to 0.");
            this.yearsOfExperience = 0;
        } else if (yearsOfExperience > 50) {
            System.out.println("Warning: Unusual years of experience detected: " + yearsOfExperience);
            this.yearsOfExperience = yearsOfExperience;
        } else {
            this.yearsOfExperience = yearsOfExperience;
        }
    }

    // PUBLIC GETTERS - Available to everyone
    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public boolean isActive() {
        return isActive;
    }

    // PUBLIC SETTERS with validation
    public void setDepartment(String newDepartment) {
        if (newDepartment == null || newDepartment.trim().isEmpty()) {
            System.out.println("Error: Department cannot be empty");
            return;
        }

        System.out.println("Transferring " + fullName + " from " + this.department + " to " + newDepartment);
        this.department = newDepartment;
    }

    public void setSalary(double newSalary) {
        if (newSalary < 0) {
            System.out.println("Error: Salary cannot be negative");
            return;
        }

        double changePercent = ((newSalary - this.baseSalary) / this.baseSalary) * 100;
        System.out.println("Salary change for " + fullName + ": " +
                String.format("%.1f", changePercent) + "% " +
                (changePercent >= 0 ? "increase" : "decrease"));

        this.baseSalary = newSalary;
    }

    public void addExperience(int additionalYears) {
        if (additionalYears < 0) {
            System.out.println("Error: Cannot subtract experience");
            return;
        }

        this.yearsOfExperience += additionalYears;
        System.out.println(fullName + " now has " + this.yearsOfExperience + " years of experience");
    }

    // METHODS THAT SUBCLASSES CAN OVERRIDE OR USE

    // Calculate basic salary (can be overridden by subclasses)
    public double calculateTotalSalary() {
        double experienceBonus = yearsOfExperience * 1000; // $1000 per year of experience
        double totalSalary = baseSalary + experienceBonus;

        System.out.println("Salary calculation for " + fullName + ":");
        System.out.println("  Base salary: $" + String.format("%.2f", baseSalary));
        System.out.println("  Experience bonus: $" + String.format("%.2f", experienceBonus) +
                " (" + yearsOfExperience + " years)");
        System.out.println("  Total salary: $" + String.format("%.2f", totalSalary));

        return totalSalary;
    }

    // Work method (can be overridden by subclasses)
    public void work() {
        System.out.println(fullName + " (Employee) is performing general employee duties");
    }

    // Attend meeting method (can be used or overridden by subclasses)
    public void attendMeeting(String meetingType) {
        System.out.println(fullName + " is attending " + meetingType + " meeting");
    }

    // Take vacation method (common to all employees)
    public void takeVacation(int days) {
        if (days <= 0) {
            System.out.println("Error: Vacation days must be positive");
            return;
        }

        System.out.println(fullName + " is taking " + days + " vacation days");
    }

    // PROTECTED METHOD - Only subclasses can access
    protected void updateEmployeeRecord(String updateType, String details) {
        System.out.println("Employee record update for " + employeeId + ":");
        System.out.println("  Update type: " + updateType);
        System.out.println("  Details: " + details);
        System.out.println("  Updated by: System");
    }

    // Display employee information (can be overridden)
    public void displayInfo() {
        System.out.println("\n=== EMPLOYEE INFORMATION ===");
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + fullName);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: $" + String.format("%.2f", baseSalary));
        System.out.println("Experience: " + yearsOfExperience + " years");
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("============================");
    }

    // toString method for easy object representation
    @Override
    public String toString() {
        return "Employee{" +
                "id='" + employeeId + '\'' +
                ", name='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + baseSalary +
                '}';
    }
}

// CHILD CLASS 1 - Manager extends Employee
// A Manager IS-A Employee with additional management responsibilities
class Manager extends Employee {
    // ADDITIONAL FIELDS specific to managers
    private int teamSize;
    private String managementLevel; // "Senior", "Mid", "Junior"
    private double managementAllowance;

    // CONSTRUCTOR - Must call parent constructor first
    public Manager(String fullName, String department, double baseSalary, int teamSize, String managementLevel) {
        // SUPER KEYWORD - calls the parent class constructor
        super(fullName, department, baseSalary);

        // Validate manager-specific data
        if (teamSize < 1) {
            System.out.println("Warning: Manager should have at least 1 team member. Setting to 1.");
            this.teamSize = 1;
        } else {
            this.teamSize = teamSize;
        }

        if (managementLevel == null || managementLevel.trim().isEmpty()) {
            this.managementLevel = "Junior";
        } else {
            this.managementLevel = managementLevel;
        }

        // Calculate management allowance based on team size and level
        this.managementAllowance = calculateManagementAllowance();

        // Update the employee record using protected method from parent
        updateEmployeeRecord("Role Change", "Promoted to Manager - " + managementLevel);

        System.out.println("Manager specific setup completed for: " + fullName);
    }

    // PRIVATE METHOD - specific to Manager class
    private double calculateManagementAllowance() {
        double baseAllowance = teamSize * 500; // $500 per team member

        // Adjust based on management level
        switch (managementLevel.toLowerCase()) {
            case "senior":
                baseAllowance *= 1.5;
                break;
            case "mid":
                baseAllowance *= 1.2;
                break;
            case "junior":
            default:
                // No additional multiplier for junior managers
                break;
        }

        return baseAllowance;
    }

    // GETTERS for manager-specific fields
    public int getTeamSize() {
        return teamSize;
    }

    public String getManagementLevel() {
        return managementLevel;
    }

    public double getManagementAllowance() {
        return managementAllowance;
    }

    // SETTERS with validation
    public void setTeamSize(int newTeamSize) {
        if (newTeamSize < 1) {
            System.out.println("Error: Manager must have at least 1 team member");
            return;
        }

        System.out.println("Team size for " + fullName + " changed from " + this.teamSize + " to " + newTeamSize);
        this.teamSize = newTeamSize;
        this.managementAllowance = calculateManagementAllowance(); // Recalculate allowance
    }

    // OVERRIDE PARENT METHOD - Manager calculates salary differently
    @Override
    public double calculateTotalSalary() {
        // Call parent method to get base calculation
        double baseTotalSalary = super.calculateTotalSalary();

        // Add manager-specific components
        double totalManagerSalary = baseTotalSalary + managementAllowance;

        System.out.println("Manager salary additions:");
        System.out.println("  Management allowance: $" + String.format("%.2f", managementAllowance));
        System.out.println("  Final manager salary: $" + String.format("%.2f", totalManagerSalary));

        return totalManagerSalary;
    }

    // OVERRIDE PARENT METHOD - Manager works differently
    @Override
    public void work() {
        System.out.println(fullName + " (Manager) is managing team of " + teamSize + " members");
        System.out.println("  - Conducting performance reviews");
        System.out.println("  - Planning team objectives");
        System.out.println("  - Coordinating with other departments");
    }

    // NEW METHODS specific to managers
    public void conductPerformanceReview(String employeeName) {
        System.out.println(fullName + " is conducting performance review for " + employeeName);
        updateEmployeeRecord("Performance Review", "Reviewed " + employeeName);
    }

    public void approveVacation(String employeeName, int days) {
        System.out.println(fullName + " approved " + days + " vacation days for " + employeeName);
    }

    public void holdTeamMeeting(String agenda) {
        System.out.println(fullName + " is holding team meeting with " + teamSize + " members");
        System.out.println("Agenda: " + agenda);

        // Managers can also attend meetings (inherited method)
        attendMeeting("Team Management");
    }

    // OVERRIDE display method to include manager-specific info
    @Override
    public void displayInfo() {
        // Call parent display method first
        super.displayInfo();

        // Add manager-specific information
        System.out.println("=== MANAGER DETAILS ===");
        System.out.println("Team Size: " + teamSize + " members");
        System.out.println("Management Level: " + managementLevel);
        System.out.println("Management Allowance: $" + String.format("%.2f", managementAllowance));
        System.out.println("======================");
    }
}

// CHILD CLASS 2 - Developer extends Employee
// A Developer IS-A Employee with additional technical responsibilities
class Developer extends Employee {
    // ADDITIONAL FIELDS specific to developers
    private String programmingLanguage;
    private String projectAssigned;
    private int linesOfCodeWritten;
    private String skillLevel; // "Junior", "Mid", "Senior"

    // CONSTRUCTOR
    public Developer(String fullName, String department, double baseSalary, String programmingLanguage,
            String skillLevel) {
        super(fullName, department, baseSalary);

        this.programmingLanguage = programmingLanguage != null ? programmingLanguage : "Java";
        this.skillLevel = skillLevel != null ? skillLevel : "Junior";
        this.linesOfCodeWritten = 0;
        this.projectAssigned = "Unassigned";

        updateEmployeeRecord("Role Assignment", "Assigned as " + skillLevel + " " + programmingLanguage + " Developer");

        System.out.println("Developer specific setup completed for: " + fullName);
    }

    // GETTERS
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getProjectAssigned() {
        return projectAssigned;
    }

    public int getLinesOfCodeWritten() {
        return linesOfCodeWritten;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    // SETTERS
    public void assignProject(String projectName) {
        if (projectName == null || projectName.trim().isEmpty()) {
            System.out.println("Error: Project name cannot be empty");
            return;
        }

        System.out.println(fullName + " assigned to project: " + projectName);
        this.projectAssigned = projectName;
        updateEmployeeRecord("Project Assignment", "Assigned to " + projectName);
    }

    public void promoteSkillLevel() {
        switch (skillLevel.toLowerCase()) {
            case "junior":
                skillLevel = "Mid";
                setSalary(getBaseSalary() * 1.3); // 30% salary increase
                break;
            case "mid":
                skillLevel = "Senior";
                setSalary(getBaseSalary() * 1.4); // 40% salary increase
                break;
            case "senior":
                System.out.println(fullName + " is already at Senior level");
                return;
        }

        System.out.println(fullName + " promoted to " + skillLevel + " Developer");
        updateEmployeeRecord("Promotion", "Promoted to " + skillLevel + " Developer");
    }

    // OVERRIDE PARENT METHOD - Developer works differently
    @Override
    public void work() {
        System.out.println(fullName + " (Developer) is working on technical tasks:");
        System.out.println("  - Writing " + programmingLanguage + " code");
        System.out.println("  - Project: " + projectAssigned);
        System.out.println("  - Skill level: " + skillLevel);
    }

    // OVERRIDE salary calculation for developers
    @Override
    public double calculateTotalSalary() {
        double baseTotalSalary = super.calculateTotalSalary();

        // Skill level bonus
        double skillBonus = 0;
        switch (skillLevel.toLowerCase()) {
            case "junior":
                skillBonus = baseSalary * 0.1; // 10% bonus
                break;
            case "mid":
                skillBonus = baseSalary * 0.2; // 20% bonus
                break;
            case "senior":
                skillBonus = baseSalary * 0.3; // 30% bonus
                break;
        }

        // Performance bonus based on lines of code (simple metric)
        double performanceBonus = Math.min(linesOfCodeWritten / 1000 * 100, 5000); // Max $5000

        double totalDeveloperSalary = baseTotalSalary + skillBonus + performanceBonus;

        System.out.println("Developer salary additions:");
        System.out.println("  Skill level bonus (" + skillLevel + "): $" + String.format("%.2f", skillBonus));
        System.out.println("  Performance bonus: $" + String.format("%.2f", performanceBonus));
        System.out.println("  Final developer salary: $" + String.format("%.2f", totalDeveloperSalary));

        return totalDeveloperSalary;
    }

    // NEW METHODS specific to developers
    public void writeCode(int linesWritten) {
        if (linesWritten <= 0) {
            System.out.println("Error: Lines of code must be positive");
            return;
        }

        this.linesOfCodeWritten += linesWritten;
        System.out.println(fullName + " wrote " + linesWritten + " lines of " + programmingLanguage + " code");
        System.out.println("Total lines of code: " + this.linesOfCodeWritten);
    }

    public void debugCode(String issueDescription) {
        System.out.println(fullName + " is debugging: " + issueDescription);
        System.out.println("Using " + programmingLanguage + " debugging tools");
    }

    public void codeReview(String developerName) {
        System.out.println(fullName + " is reviewing code written by " + developerName);
    }

    // OVERRIDE display method
    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("=== DEVELOPER DETAILS ===");
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Skill Level: " + skillLevel);
        System.out.println("Current Project: " + projectAssigned);
        System.out.println("Lines of Code Written: " + linesOfCodeWritten);
        System.out.println("=========================");
    }
}

// DEMONSTRATION CLASS - Shows how inheritance works in practice
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE INHERITANCE DEMONSTRATION ===\n");

        // Create base Employee
        Employee basicEmployee = new Employee("Alice Johnson", "HR", 50000, 2);

        // Create Manager (inherits from Employee)
        Manager projectManager = new Manager("Bob Smith", "Engineering", 75000, 8, "Senior");

        // Create Developer (inherits from Employee)
        Developer javaDeveloper = new Developer("Carol Wilson", "Engineering", 65000, "Java", "Mid");

        System.out.println("\n=== POLYMORPHISM DEMONSTRATION ===");

        // Array of Employee references - can hold any Employee subclass
        Employee[] employees = { basicEmployee, projectManager, javaDeveloper };

        // Process all employees uniformly (polymorphism)
        for (Employee emp : employees) {
            System.out.println("\n--- Processing Employee: " + emp.getFullName() + " ---");

            // These methods work for all Employee types
            emp.displayInfo();
            emp.work(); // Different behavior for each type!
            emp.attendMeeting("Company All-Hands");

            double salary = emp.calculateTotalSalary(); // Different calculations!
            System.out.println("Final calculated salary: $" + String.format("%.2f", salary));
        }

        System.out.println("\n=== SUBCLASS-SPECIFIC OPERATIONS ===");

        // Manager-specific operations
        System.out.println("\n--- Manager Operations ---");
        projectManager.holdTeamMeeting("Q3 Planning Session");
        projectManager.conductPerformanceReview("John Doe");
        projectManager.approveVacation("Jane Smith", 5);
        projectManager.setTeamSize(12); // Manager-specific method

        // Developer-specific operations
        System.out.println("\n--- Developer Operations ---");
        javaDeveloper.assignProject("E-commerce Platform");
        javaDeveloper.writeCode(500);
        javaDeveloper.debugCode("NullPointerException in user login");
        javaDeveloper.codeReview("Mike Johnson");
        javaDeveloper.promoteSkillLevel(); // Promotion!

        System.out.println("\n=== INHERITANCE RELATIONSHIPS ===");

        // Demonstrate IS-A relationships
        System.out.println("basicEmployee instanceof Employee: " + (basicEmployee instanceof Employee));
        System.out.println("projectManager instanceof Employee: " + (projectManager instanceof Employee));
        System.out.println("projectManager instanceof Manager: " + (projectManager instanceof Manager));
        System.out.println("javaDeveloper instanceof Employee: " + (javaDeveloper instanceof Employee));
        System.out.println("javaDeveloper instanceof Developer: " + (javaDeveloper instanceof Developer));

        // This would be false - Manager is not a Developer
        System.out.println("projectManager instanceof Developer: " + (projectManager instanceof Developer));

        System.out.println("\n=== SHARED FUNCTIONALITY DEMONSTRATION ===");

        // All employees can use inherited methods
        basicEmployee.takeVacation(3);
        projectManager.takeVacation(5);
        javaDeveloper.takeVacation(7);

        // All employees can have their departments changed
        basicEmployee.setDepartment("Operations");
        projectManager.setDepartment("Product Management");

        System.out.println("\n=== KEY INHERITANCE CONCEPTS DEMONSTRATED ===");
        System.out.println("1. CODE REUSE: Manager and Developer reuse Employee code");
        System.out.println("2. IS-A RELATIONSHIP: Manager IS-A Employee, Developer IS-A Employee");
        System.out.println("3. METHOD OVERRIDING: work() and calculateTotalSalary() behave differently");
        System.out.println("4. SUPER KEYWORD: Child classes call parent constructors and methods");
        System.out.println("5. POLYMORPHISM: Same method calls produce different behaviors");
        System.out.println("6. ENCAPSULATION PRESERVED: Private fields remain private, protected accessible");
        System.out.println("7. SPECIALIZATION: Each subclass adds its own unique features");

        System.out.println("\n=== INHERITANCE HIERARCHY ===");
        System.out.println("Employee (Parent/Superclass)");
        System.out.println("├── Manager (Child/Subclass)");
        System.out.println("└── Developer (Child/Subclass)");
        System.out.println("\nEach child inherits all public and protected members from Employee");
        System.out.println("Each child can add new members and override existing methods");
    }
}