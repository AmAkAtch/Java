package day12_inheritance;

// COMPREHENSIVE METHOD OVERRIDING DEMONSTRATION
// Payment Processing System - Shows real-world overriding scenarios

// PARENT CLASS - Generic Payment Method
class PaymentMethod {
    protected String accountId;
    protected double balance;
    protected String ownerName;
    protected boolean isActive;

    // CONSTRUCTOR
    public PaymentMethod(String accountId, String ownerName, double initialBalance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = Math.max(0, initialBalance); // Ensure non-negative balance
        this.isActive = true;

        System.out.println("Payment method created for: " + ownerName);
    }

    // VIRTUAL METHOD - Designed to be overridden by subclasses
    // This is a generic payment process that each payment type will customize
    public boolean processPayment(double amount, String description) {
        System.out.println("\n=== GENERIC PAYMENT PROCESSING ===");
        System.out.println("Account: " + accountId + " (Owner: " + ownerName + ")");
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Description: " + description);

        // Basic validation common to all payment methods
        if (!isActive) {
            System.out.println("ERROR: Payment method is inactive");
            return false;
        }

        if (amount <= 0) {
            System.out.println("ERROR: Payment amount must be positive");
            return false;
        }

        // This is where subclasses will add their specific logic
        System.out.println("Processing payment using generic method...");
        System.out.println("Payment processed successfully");

        return true;
    }

    // METHOD that subclasses will extend (not completely replace)
    public void generateReceipt(double amount, String description, String transactionId) {
        System.out.println("\n=== PAYMENT RECEIPT ===");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Date: " + java.time.LocalDateTime.now());
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Description: " + description);
        System.out.println("Account: " + accountId);
        System.out.println("Owner: " + ownerName);
        // Subclasses will add payment-method-specific details here
    }

    // FINAL METHOD - Cannot be overridden (security/audit requirement)
    public final void logTransaction(double amount, String type, boolean success) {
        System.out.println("[AUDIT LOG] " + java.time.LocalDateTime.now() +
                " | Account: " + accountId +
                " | Type: " + type +
                " | Amount: $" + amount +
                " | Success: " + success);
    }

    // GETTERS
    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean isActive() {
        return isActive;
    }
}

// CHILD CLASS 1 - Credit Card Payment
class CreditCardPayment extends PaymentMethod {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private double creditLimit;
    private double availableCredit;

    public CreditCardPayment(String accountId, String ownerName, String cardNumber,
            String expiryDate, String cvv, double creditLimit) {
        super(accountId, ownerName, 0); // Credit cards don't have balance like bank accounts

        this.cardNumber = maskCardNumber(cardNumber);
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.creditLimit = creditLimit;
        this.availableCredit = creditLimit;

        System.out.println("Credit card setup completed. Available credit: $" + creditLimit);
    }

    // HELPER METHOD - Mask sensitive card number
    private String maskCardNumber(String fullCardNumber) {
        if (fullCardNumber.length() < 4)
            return "****";
        return "****-****-****-" + fullCardNumber.substring(fullCardNumber.length() - 4);
    }

    // OVERRIDE - Credit card payment process is different from generic payment
    @Override
    public boolean processPayment(double amount, String description) {
        System.out.println("\n=== CREDIT CARD PAYMENT PROCESSING ===");
        System.out.println("Card: " + cardNumber + " (Owner: " + ownerName + ")");
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Description: " + description);

        // CALL PARENT METHOD for basic validation using SUPER
        System.out.println("Performing basic payment validations...");
        if (!super.isActive) {
            System.out.println("ERROR: Credit card is inactive");
            logTransaction(amount, "CREDIT_CARD", false);
            return false;
        }

        if (amount <= 0) {
            System.out.println("ERROR: Payment amount must be positive");
            logTransaction(amount, "CREDIT_CARD", false);
            return false;
        }

        // CREDIT CARD SPECIFIC validations
        if (amount > availableCredit) {
            System.out.println("ERROR: Insufficient credit limit. Available: $" + availableCredit);
            logTransaction(amount, "CREDIT_CARD", false);
            return false;
        }

        // CREDIT CARD SPECIFIC processing steps
        System.out.println("Validating card details...");
        if (!validateCardDetails()) {
            System.out.println("ERROR: Card validation failed");
            logTransaction(amount, "CREDIT_CARD", false);
            return false;
        }

        System.out.println("Contacting payment processor...");
        System.out.println("Authorizing transaction...");

        // Simulate processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        // Process the payment
        availableCredit -= amount;
        System.out.println("Credit card payment authorized!");
        System.out.println("Remaining available credit: $" + String.format("%.2f", availableCredit));

        // Log successful transaction using inherited final method
        logTransaction(amount, "CREDIT_CARD", true);

        return true;
    }

    // EXTEND PARENT METHOD - Add credit card specific receipt details
    @Override
    public void generateReceipt(double amount, String description, String transactionId) {
        // CALL PARENT METHOD first using SUPER
        super.generateReceipt(amount, description, transactionId);

        // ADD CREDIT CARD SPECIFIC information
        System.out.println("--- Credit Card Details ---");
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Transaction Type: Credit Card Purchase");
        System.out.println("Available Credit: $" + String.format("%.2f", availableCredit));
        System.out.println("Credit Limit: $" + String.format("%.2f", creditLimit));
        System.out.println("=======================");
    }

    // PRIVATE METHOD - Credit card specific validation
    private boolean validateCardDetails() {
        // Simulate card validation logic
        return expiryDate != null && cvv != null && cardNumber != null;
    }

    // NEW METHOD - Specific to credit cards
    public void makePayment(double amount) {
        if (amount > availableCredit) {
            System.out.println("Payment declined: Exceeds available credit");
            return;
        }
        availableCredit += amount; // Payment increases available credit
        System.out.println("Credit card payment of $" + amount + " processed. Available credit: $" + availableCredit);
    }
}

// CHILD CLASS 2 - Bank Account Payment (Debit)
class BankAccountPayment extends PaymentMethod {
    private String bankName;
    private String accountNumber;
    private String routingNumber;
    private double overdraftLimit;

    public BankAccountPayment(String accountId, String ownerName, double initialBalance,
            String bankName, String accountNumber, String routingNumber) {
        super(accountId, ownerName, initialBalance);

        this.bankName = bankName;
        this.accountNumber = maskAccountNumber(accountNumber);
        this.routingNumber = routingNumber;
        this.overdraftLimit = 500.0; // Default $500 overdraft protection

        System.out.println("Bank account setup completed at " + bankName);
    }

    private String maskAccountNumber(String fullAccountNumber) {
        if (fullAccountNumber.length() < 4)
            return "****";
        return "****" + fullAccountNumber.substring(fullAccountNumber.length() - 4);
    }

    // OVERRIDE - Bank account payment has different rules than generic payment
    @Override
    public boolean processPayment(double amount, String description) {
        System.out.println("\n=== BANK ACCOUNT PAYMENT PROCESSING ===");
        System.out.println("Bank: " + bankName);
        System.out.println("Account: " + accountNumber + " (Owner: " + ownerName + ")");
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Description: " + description);

        // BASIC VALIDATION using parent logic (but not calling super.processPayment)
        if (!isActive) {
            System.out.println("ERROR: Bank account is inactive");
            logTransaction(amount, "BANK_TRANSFER", false);
            return false;
        }

        if (amount <= 0) {
            System.out.println("ERROR: Payment amount must be positive");
            logTransaction(amount, "BANK_TRANSFER", false);
            return false;
        }

        // BANK SPECIFIC validation - check available funds including overdraft
        double availableFunds = balance + overdraftLimit;
        if (amount > availableFunds) {
            System.out.println("ERROR: Insufficient funds. Available (including overdraft): $" +
                    String.format("%.2f", availableFunds));
            logTransaction(amount, "BANK_TRANSFER", false);
            return false;
        }

        // BANK SPECIFIC processing
        System.out.println("Verifying account details with " + bankName + "...");
        System.out.println("Checking account balance: $" + String.format("%.2f", balance));

        // Check if overdraft will be used
        if (amount > balance) {
            double overdraftUsed = amount - balance;
            System.out.println("NOTICE: Using $" + String.format("%.2f", overdraftUsed) + " overdraft protection");
            balance = 0; // Account balance goes to zero
        } else {
            balance -= amount; // Deduct from available balance
        }

        System.out.println("Bank transfer authorized!");
        System.out.println("New account balance: $" + String.format("%.2f", balance));

        logTransaction(amount, "BANK_TRANSFER", true);
        return true;
    }

    // OVERRIDE - Bank receipt has different format
    @Override
    public void generateReceipt(double amount, String description, String transactionId) {
        // CALL PARENT METHOD for common receipt information
        super.generateReceipt(amount, description, transactionId);

        // ADD BANK SPECIFIC details
        System.out.println("--- Bank Transfer Details ---");
        System.out.println("Bank: " + bankName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Routing Number: " + routingNumber);
        System.out.println("Transaction Type: ACH Debit");
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
        System.out.println("============================");
    }

    // NEW METHODS specific to bank accounts
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be positive");
            return;
        }

        balance += amount;
        System.out.println("Deposited $" + String.format("%.2f", amount) +
                ". New balance: $" + String.format("%.2f", balance));
        logTransaction(amount, "DEPOSIT", true);
    }

    public void setOverdraftLimit(double newLimit) {
        if (newLimit < 0) {
            System.out.println("Error: Overdraft limit cannot be negative");
            return;
        }

        System.out.println("Overdraft limit changed from $" + overdraftLimit + " to $" + newLimit);
        this.overdraftLimit = newLimit;
    }
}

// CHILD CLASS 3 - Digital Wallet Payment
class DigitalWalletPayment extends PaymentMethod {
    private String walletProvider;
    private String phoneNumber;
    private boolean biometricEnabled;
    private String linkedPaymentMethod;

    public DigitalWalletPayment(String accountId, String ownerName, double initialBalance,
            String walletProvider, String phoneNumber) {
        super(accountId, ownerName, initialBalance);

        this.walletProvider = walletProvider;
        this.phoneNumber = phoneNumber;
        this.biometricEnabled = true; // Default to enabled for security
        this.linkedPaymentMethod = "Default Payment Method";

        System.out.println("Digital wallet (" + walletProvider + ") setup completed");
    }

    // OVERRIDE - Digital wallet has unique processing steps
    @Override
    public boolean processPayment(double amount, String description) {
        System.out.println("\n=== DIGITAL WALLET PAYMENT PROCESSING ===");
        System.out.println("Wallet Provider: " + walletProvider);
        System.out.println("Phone: " + phoneNumber + " (Owner: " + ownerName + ")");
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Description: " + description);

        // PARENT VALIDATIONS using super - we want the generic validation logic
        System.out.println("Performing basic payment validations...");
        if (!isActive) {
            System.out.println("ERROR: Digital wallet is inactive");
            logTransaction(amount, "DIGITAL_WALLET", false);
            return false;
        }

        if (amount <= 0) {
            System.out.println("ERROR: Payment amount must be positive");
            logTransaction(amount, "DIGITAL_WALLET", false);
            return false;
        }

        // DIGITAL WALLET SPECIFIC validations and processing
        if (amount > balance) {
            System.out.println("ERROR: Insufficient wallet balance. Available: $" +
                    String.format("%.2f", balance));
            System.out.println("Would you like to use linked payment method: " + linkedPaymentMethod + "?");
            logTransaction(amount, "DIGITAL_WALLET", false);
            return false;
        }

        // Biometric authentication simulation
        if (biometricEnabled) {
            System.out.println("Requesting biometric authentication...");
            System.out.println("✓ Fingerprint verified");
        } else {
            System.out.println("Using PIN verification...");
        }

        System.out.println("Processing through " + walletProvider + " network...");

        // Process payment
        balance -= amount;

        System.out.println("Digital wallet payment completed!");
        System.out.println("Remaining wallet balance: $" + String.format("%.2f", balance));

        logTransaction(amount, "DIGITAL_WALLET", true);
        return true;
    }

    // COMPLETELY DIFFERENT receipt format for digital wallets
    @Override
    public void generateReceipt(double amount, String description, String transactionId) {
        // DIGITAL WALLETS might have completely different receipt format
        // We'll call super but format it differently

        System.out.println("\n=== " + walletProvider.toUpperCase() + " PAYMENT RECEIPT ===");
        System.out.println("📱 Digital Payment Confirmation");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Date/Time: " + java.time.LocalDateTime.now());
        System.out.println("Merchant: " + description);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Payment Method: " + walletProvider + " Wallet");
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Account ID: " + accountId);
        System.out.println("Remaining Balance: $" + String.format("%.2f", balance));
        System.out.println("Authentication: " + (biometricEnabled ? "Biometric" : "PIN"));
        System.out.println("Status: ✅ Completed");
        System.out.println("=======================================");
    }

    // UNIQUE METHODS for digital wallets
    public void addFunds(double amount, String fundingSource) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive");
            return;
        }

        System.out.println("Adding $" + String.format("%.2f", amount) +
                " to wallet from " + fundingSource);
        balance += amount;
        System.out.println("Wallet funded successfully. New balance: $" + String.format("%.2f", balance));

        logTransaction(amount, "WALLET_FUNDING", true);
    }

    public void enableBiometric(boolean enable) {
        this.biometricEnabled = enable;
        System.out.println("Biometric authentication " + (enable ? "enabled" : "disabled"));
    }

    public void linkPaymentMethod(String paymentMethodDescription) {
        this.linkedPaymentMethod = paymentMethodDescription;
        System.out.println("Linked payment method updated: " + paymentMethodDescription);
    }
}

// DEMONSTRATION CLASS - Shows method overriding in action
public class PaymentSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== METHOD OVERRIDING COMPREHENSIVE DEMONSTRATION ===\n");

        // Create different payment methods
        CreditCardPayment creditCard = new CreditCardPayment("CC001", "John Smith",
                "1234567812345678", "12/25", "123", 5000.0);

        BankAccountPayment bankAccount = new BankAccountPayment("BA001", "Jane Doe", 2500.0,
                "First National Bank", "987654321", "123456789");

        DigitalWalletPayment digitalWallet = new DigitalWalletPayment("DW001", "Mike Johnson", 500.0,
                "PayPal", "555-123-4567");

        System.out.println("\n=== POLYMORPHIC BEHAVIOR DEMONSTRATION ===");

        // Array of PaymentMethod references - demonstrates polymorphism
        PaymentMethod[] paymentMethods = { creditCard, bankAccount, digitalWallet };

        // Same method call, different behavior for each payment type
        String[] transactions = {
                "Online Electronics Purchase",
                "Grocery Store Payment",
                "Coffee Shop Payment"
        };

        double[] amounts = { 1200.0, 150.0, 25.0 };

        for (int i = 0; i < paymentMethods.length; i++) {
            PaymentMethod payment = paymentMethods[i];

            System.out.println("\n--- Processing Payment " + (i + 1) + " ---");
            System.out.println("Payment Method Type: " + payment.getClass().getSimpleName());

            // SAME METHOD CALL - DIFFERENT BEHAVIOR (Method Overriding in action!)
            boolean success = payment.processPayment(amounts[i], transactions[i]);

            if (success) {
                // SAME METHOD CALL - DIFFERENT RECEIPTS (Method Overriding!)
                payment.generateReceipt(amounts[i], transactions[i], "TXN" + System.currentTimeMillis());
            }

            System.out.println("Payment " + (success ? "SUCCESS" : "FAILED"));
        }

        System.out.println("\n=== SUBCLASS-SPECIFIC FUNCTIONALITY ===");

        // Credit card specific operations
        System.out.println("\n--- Credit Card Operations ---");
        creditCard.makePayment(200.0); // Increase available credit
        creditCard.processPayment(100.0, "Gas Station");

        // Bank account specific operations
        System.out.println("\n--- Bank Account Operations ---");
        bankAccount.deposit(500.0);
        bankAccount.setOverdraftLimit(1000.0);
        bankAccount.processPayment(2800.0, "Rent Payment"); // Will use overdraft

        // Digital wallet specific operations
        System.out.println("\n--- Digital Wallet Operations ---");
        digitalWallet.addFunds(300.0, "Linked Bank Account");
        digitalWallet.linkPaymentMethod("Chase Debit Card");
        digitalWallet.enableBiometric(false);
        digitalWallet.processPayment(50.0, "Online Subscription");

        System.out.println("\n=== KEY METHOD OVERRIDING CONCEPTS DEMONSTRATED ===");
        System.out.println("1. SAME METHOD NAME: processPayment() exists in all classes");
        System.out.println("2. DIFFERENT IMPLEMENTATIONS: Each payment type processes differently");
        System.out.println("3. POLYMORPHISM: Same method call produces different behaviors");
        System.out.println("4. SUPER USAGE: Child classes can call parent methods when needed");
        System.out.println("5. @OVERRIDE ANNOTATION: Ensures method signatures match parent");
        System.out.println("6. EXTENDING BEHAVIOR: generateReceipt() calls super then adds specific details");
        System.out.println("7. COMPLETE REPLACEMENT: Some overrides completely replace parent behavior");
        System.out.println("8. FINAL METHODS: logTransaction() cannot be overridden (security requirement)");

        System.out.println("\n=== METHOD OVERRIDING RULES FOLLOWED ===");
        System.out.println("✓ Same method signature (name, parameters, return type)");
        System.out.println("✓ Cannot reduce access level (public methods stay public)");
        System.out.println("✓ @Override annotation used for compiler checking");
        System.out.println("✓ super keyword used to access parent functionality");
        System.out.println("✓ Final methods respected (cannot be overridden)");
    }
}