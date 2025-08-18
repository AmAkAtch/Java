package day03_conditional_statements.bankwithdrawalchecker;

public class BankWithdrawalChecker {
    public static void main(String[] args) {
        //Declare vvaraibles
        double accountBalance = 322.80;
        double withdrawalAmmount = 122;
        int menuChoice = 1;

        //withdraw only if there is moeny in account
        if(accountBalance>=withdrawalAmmount){
            //adjust the account balance
            System.out.println("Account Balance before Withdrawal: "+accountBalance);
            accountBalance = accountBalance-withdrawalAmmount;
            System.out.println(accountBalance);
        }else{
            System.out.println("Not enought account balance for Withdrawal");
        }

        switch(menuChoice){
            case 1: 
                System.out.println(accountBalance + " is your remaining account balance");
                break;
            case 2:
                System.out.println("Your last withdrawl amount : " + withdrawalAmmount);
                break;
            default:
                System.out.println("Invalid menu choice");
        }
    }
}
