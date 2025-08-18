package day04_loops.passwordretrysystem;

import java.util.Scanner;

public class PasswordRetrySystem {
    public static void main(String[] args) {

        // initialize vairables
        Scanner scanner = new Scanner(System.in);
        String correctPassword = "password123";
        int retries = 0;
        int maxRetries = 3;
        boolean validPassword = false;

        do {
            System.out.println("Enter the Password:");
            String password = scanner.nextLine();
            // check input from user for correct password
            if (password.equals(correctPassword)) {
                validPassword = true;
                retries = 0;
            } else {
                if (retries < 2)
                    System.out.println("Wrong Password try again");
                retries++;
            }
        } while (!validPassword && retries != maxRetries); // keep retying until max try or password is true
        // Skips checking second if the first is false - short curcuiting

        // if password is correct
        if (validPassword) {
            System.out.println("You logged in! Welcome");
        } else {
            System.out.println("PassWord wrong bay bay !!!");
        }
    }
}
