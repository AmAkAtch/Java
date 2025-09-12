package day11_encapsulation;

// Complete Banking Account System with Proper Encapsulation
// This demonstrates why encapsulation is crucial for data protection

public class BankAccount {
    // PRIVATE variables - These are hidden from outside access
    // Think of these as your vault contents - nobody can directly touch them
    private String accountNumber; // Account identification
    private String customerName; // Account holder name
    private double balance; // Account balance - CRITICAL to protect
    private String pin; // Security PIN - MUST be private
    private boolean isActive; // Account status

    // CONSTRUCTOR - Public so we can create accounts
    // This is like the bank's account opening procedure
    public BankAccount(String accountNumber, String customerName, double initialDeposit, String pin) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;

        // Validation in constructor - professional practice
        if (initialDeposit < 0) {
            System.out.println("Error: Cannot open account with negative balance");
            this.balance = 0;
        } else {
            this.balance = initialDeposit;
        }

        // PIN validation
        if (pin == null || pin.length() != 4) {
            System.out.println("Error: PIN must be exactly 4 digits");
            this.pin = "0000"; // Default PIN
        } else {
            this.pin = pin;
        }

        this.isActive = true; // New accounts are active by default

        System.out.println("Account created successfully for: " + customerName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Initial Balance: $" + balance);
    }

    // GETTER METHODS - These provide READ-ONLY access to private data
    // Think of these as viewing windows - you can see the data but not change it
    // directly

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        // In real banking, you might want to verify identity before showing balance
        return balance;
    }

    public boolean isActive() {
        return isActive;
    }

    // SETTER METHODS with VALIDATION - These provide CONTROLLED write access
    // These are like secure procedures - they check everything before making
    // changes

    public void setCustomerName(String newName) {
        // Validation: Name cannot be empty
        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Error: Customer name cannot be empty");
            return;
        }

        // Additional validation: Name should be reasonable length
        if (newName.length() > 50) {
            System.out.println("Error: Customer name too long (max 50 characters)");
            return;
        }

        System.out.println("Updating customer name from: " + this.customerName + " to: " + newName);
        this.customerName = newName;
    }

    // NOTICE: No direct setter for balance - we use specific methods instead
    // This is crucial for financial security

    // DEPOSIT METHOD - Controlled way to add money
    public void deposit(double amount) {
        // Validation: Amount must be positive
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be positive");
            return;
        }

        // Validation: Account must be active
        if (!isActive) {
            System.out.println("Error: Cannot deposit to inactive account");
            return;
        }

        // Validation: Reasonable deposit limit (prevent money laundering)
        if (amount > 10000) {
            System.out.println("Large deposit detected. Additional verification required.");
            // In real system, this might trigger compliance checks
        }

        balance += amount;
        System.out.println("Deposited: $" + amount);
        System.out.println("New balance: $" + balance);
    }

    // WITHDRAW METHOD - Controlled way to remove money
    public boolean withdraw(double amount, String enteredPin) {
        // Validation 1: PIN verification
        if (!enteredPin.equals(this.pin)) {
            System.out.println("Error: Invalid PIN. Transaction denied.");
            return false;
        }

        // Validation 2: Amount must be positive
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive");
            return false;
        }

        // Validation 3: Account must be active
        if (!isActive) {
            System.out.println("Error: Cannot withdraw from inactive account");
            return false;
        }

        // Validation 4: Sufficient funds
        if (amount > balance) {
            System.out.println("Error: Insufficient funds. Balance: $" + balance);
            return false;
        }

        // All validations passed - process withdrawal
        balance -= amount;
        System.out.println("Withdrawal successful: $" + amount);
        System.out.println("New balance: $" + balance);
        return true;
    }

    // CHANGE PIN METHOD - Secure way to update PIN
    public boolean changePin(String oldPin, String newPin) {
        // Verification: Old PIN must match
        if (!oldPin.equals(this.pin)) {
            System.out.println("Error: Current PIN incorrect");
            return false;
        }

        // Validation: New PIN format
        if (newPin == null || newPin.length() != 4) {
            System.out.println("Error: New PIN must be exactly 4 digits");
            return false;
        }

        // Additional validation: PIN should be numeric
        try {
            Integer.parseInt(newPin);
        } catch (NumberFormatException e) {
            System.out.println("Error: PIN must contain only numbers");
            return false;
        }

        this.pin = newPin;
        System.out.println("PIN changed successfully");
        return true;
    }

    // ACCOUNT MANAGEMENT METHODS
    public void deactivateAccount(String verificationPin) {
        if (!verificationPin.equals(this.pin)) {
            System.out.println("Error: Invalid PIN. Cannot deactivate account");
            return;
        }

        isActive = false;
        System.out.println("Account deactivated successfully");
    }

    public void activateAccount(String verificationPin) {
        if (!verificationPin.equals(this.pin)) {
            System.out.println("Error: Invalid PIN. Cannot activate account");
            return;
        }

        isActive = true;
        System.out.println("Account activated successfully");
    }

    // METHOD TO DISPLAY ACCOUNT SUMMARY
    public void displayAccountSummary() {
        System.out.println("\n=== ACCOUNT SUMMARY ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Current Balance: $" + balance);
        System.out.println("Account Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("======================\n");
    }
}

// DEMONSTRATION CLASS - Shows how encapsulation protects our data
class BankingSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== PROFESSIONAL BANKING SYSTEM DEMONSTRATION ===\n");

        // Create a new bank account
        BankAccount customerAccount = new BankAccount("ACC001", "John Smith", 1000.0, "1234");

        // Show initial account summary
        customerAccount.displayAccountSummary();

        // SAFE OPERATIONS - These work because we use proper methods
        System.out.println("--- SAFE OPERATIONS ---");
        customerAccount.deposit(500.0);
        customerAccount.withdraw(200.0, "1234");

        // UNSAFE ATTEMPTS - These are prevented by encapsulation
        System.out.println("\n--- SECURITY DEMONSTRATIONS ---");

        // Try to withdraw with wrong PIN
        customerAccount.withdraw(100.0, "9999");

        // Try to withdraw more than balance
        customerAccount.withdraw(2000.0, "1234");

        // Try to deposit negative amount
        customerAccount.deposit(-50.0);

        // DIRECT ACCESS ATTEMPTS - These would cause compilation errors
        // Uncomment these lines to see the errors:

        // customerAccount.balance = 999999.0; // ERROR: Cannot access private field
        // customerAccount.pin = "0000"; // ERROR: Cannot access private field
        // System.out.println(customerAccount.balance); // ERROR: Cannot access private
        // field

        // PROPER ACCESS - These work because we use public getter methods
        System.out.println("\n--- PROPER DATA ACCESS ---");
        System.out.println("Account Number: " + customerAccount.getAccountNumber());
        System.out.println("Customer Name: " + customerAccount.getCustomerName());
        System.out.println("Current Balance: $" + customerAccount.getBalance());

        // Change customer name using proper setter with validation
        System.out.println("\n--- NAME UPDATE DEMONSTRATION ---");
        customerAccount.setCustomerName(""); // This will fail validation
        customerAccount.setCustomerName("John Michael Smith"); // This will succeed

        // Final account summary
        customerAccount.displayAccountSummary();

        System.out.println("=== DEMONSTRATION COMPLETE ===");
        System.out.println("Notice how encapsulation prevented all unsafe operations!");
        System.out.println("The private data remains secure and valid at all times.");
    }
}