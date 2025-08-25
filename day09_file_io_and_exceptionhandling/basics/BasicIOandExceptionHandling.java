//This programm calculated salary bonus for employees a real business need
package day09_file_io_and_exceptionhandling.basics;

public class BasicIOandExceptionHandling {
    public static void main(String[] args) {

        String salaryInput = "50000";

        try {
            double baseSalary = Double.parseDouble(salaryInput); // this can throw the error if the string is not
                                                                 // numbers
            double bonus = calculateBonus(baseSalary);
            System.out.println("For Employee with base salary " + baseSalary + " bonus will be " + bonus);
        } catch (NumberFormatException e) {
            double bonus = 0;
            System.out.println("Entered Salary is not Exactly Number!");
            System.out.println("Bonus will be set to $" + bonus + " for manual inspection later!");
        }

    }

    public static double calculateBonus(double baseSalary) {
        return baseSalary * 0.1;
    }
}
