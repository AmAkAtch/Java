package day05_methods.bankingmethods;

import java.util.Scanner;

public class BankingMethods {

    private static final double INTEREST_RATE = 0.025;
    private static final double MINIMUM_BALANCE = 100.00;

    // main method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String acHolderName = "John Smith";
        double initialBalance = 1500.00;
        int choice;

        greetuser(acHolderName);

        do {
            scanner.nextLine();
            System.out.println("Press any Enter to continue...");
            scanner.nextLine();

            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    initialBalance = depositeMoney(scanner, initialBalance);
                    break;
                case 2:
                    initialBalance = withdrawMoney(scanner, initialBalance);
                    break;
                case 3:
                    checkBalance(initialBalance);
                    break;
                case 4:
                    calculateInterest(initialBalance);
                    break;
                case 5:
                    displayUserInfo(acHolderName, initialBalance);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please enter Valid Number");
                    break;
            }
        } while (choice != 6);
        System.out.println("Thanks for Using out Services, Come again!");
    }

    // function to Greet user
    public static void greetuser(String userName) {
        System.out.println("=================================");
        System.out.println("Welcome to the Banking System " + userName);
        System.out.println("=================================");
    }

    // Function to display menu to user
    public static void displayMenu() {
        /* This function will Display Banking menu to make choice later */

        System.out.println("=== Banking Menu ===");
        System.out.println("1. Deposite Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Calculate Interest");
        System.out.println("5. Account Summary");
        System.out.println("6. Exit");
    }

    // function to let user deposite money
    public static double depositeMoney(Scanner scanner, double initialBalance) {
        /*
         * This method accpets scanner and initialBalance to let user add more balance
         * First check if user entered right amount than proceed
         */
        System.out.println("Enter the Deposite amount: ");
        double depAmount = scanner.nextDouble();
        if (depAmount > 0) {
            System.out.println("You have succefully added " + depAmount + "to Your Balance!");

            double newBalance = initialBalance + depAmount;
            System.out.println("Your new balance is $" + newBalance);
            return newBalance;
        } else {
            System.out.println("Invalid deposite amount!");
            return initialBalance;
        }

    }

    // function to let user withdraw money
    public static double withdrawMoney(Scanner scanner, double initialBalance) {
        /*
         * This method accepts scanner and initial balance to let user deduct method
         * Check if the user entered valid amount
         * Then check if the user has enough balance and than proceed
         */
        System.out.println("Enter the amount you want to withdraw: ");
        double withdrawAmount = scanner.nextDouble();

        if (withdrawAmount > 0 && withdrawAmount <= initialBalance) {
            System.out.println("You have successfully withdrawn " + withdrawAmount);
            initialBalance -= withdrawAmount;
            System.out.println("Your new account balance is $" + initialBalance);
            if (initialBalance < MINIMUM_BALANCE) {
                System.out.println("Your Balance is lower than the limit $" + MINIMUM_BALANCE
                        + ", Please Deposite money immediately to avoid charges.");
            }
            return initialBalance;
        } else if (withdrawAmount > initialBalance) {
            System.out.println("Sorry you do not have enough Balance to make withdrawl, try again...");
            return initialBalance;
        } else {
            System.out.println("Invalid Input!");
            return initialBalance;
        }
    }

    // function to let user check Balance
    public static void checkBalance(double initialBalance) {
        // This function accepts initial balance and just displays the current balance
        // for user
        System.out.println("Your Current account Balance is $" + initialBalance);

    }

    // function to let user calculate interest
    public static void calculateInterest(double initialBalance) {
        System.out.println("You currently have $" + initialBalance + " in your account.");
        System.out.println("Based on that Balance and Current Interest rate of " + INTEREST_RATE);
        double yearlyInterest = initialBalance * INTEREST_RATE;
        double monthlyInterest = yearlyInterest / 12;
        System.out.println("Your Monthly interest : $" + monthlyInterest);
        System.out.println("Your Yearly interest : $" + yearlyInterest);
    }

    // function to let user display User info
    public static void displayUserInfo(String username, double initialBalance) {
        System.out.println("Username : " + username);
        System.out.println("Current Account Balance : " + initialBalance);
        printAccountStatus(initialBalance);
        calculateInterest(initialBalance);
    }

    // function to print Account Status
    public static void printAccountStatus(double initialBalance) {
        if (initialBalance > MINIMUM_BALANCE * 10) {
            System.out.println("Status : Premium Account");
        } else if (initialBalance > MINIMUM_BALANCE) {
            System.out.println("Status : Standard Account");
        } else {
            System.out.println("Low Balance Warning, Add Balance to Avoide charges");
        }
    }

}
