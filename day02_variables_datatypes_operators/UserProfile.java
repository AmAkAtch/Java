package day02_variables_datatypes_operators;

public class UserProfile {
    public static void main(String[] args){
        //declaring the variable
        String userName = "Bob";
        int age = 30;
        double accountBalance = 250.75;
        boolean isPremiumMember = false;

        //printing user summary
        System.out.println("User: "+ userName);
        System.out.println("Age: "+ age);
        System.out.println("Balance: $"+accountBalance);
        System.out.println("Premium member: "+isPremiumMember);
    }
}
