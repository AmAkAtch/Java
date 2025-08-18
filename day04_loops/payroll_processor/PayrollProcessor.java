package day04_loops.payroll_processor;

public class PayrollProcessor {
    public static void main(String[] args) {
        //declare variables
        // Declaring variables outside loop since they are fixed and dont change with each interation, saves memory
        int hourlyPay = 40;
        int hoursWorked = 20;

        for(int empNum=1; empNum<=1000; empNum++){
            //count the gross pay
            double grossPay = hourlyPay*hoursWorked;
            double tax = (grossPay>=500) ? grossPay*0.5 : 0;
            double finalPay = grossPay-tax;
            System.out.println("Final pay for Employee " + empNum + " is " + finalPay);
        }

        //for each loop runs for each item in list or array
        int firedEmp[] = {1001,1002,1003, 1004,1005,1006};
        for (int emp : firedEmp){
            System.out.println(emp + " is fired so no calculations");
        }

    }
}
