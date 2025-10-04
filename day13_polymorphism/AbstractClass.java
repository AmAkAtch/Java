package day13_polymorphism;

abstract class Employee {
    // protected variables so child of these class can use them
    protected String name;
    protected Double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // public abstract method with no need of defination
    public abstract double calculateSalary();
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }
}

class ContractEmployee extends Employee {

    double hoursWorked, finalSalary;

    public ContractEmployee(String name, double hourleySalary, double hoursWorked) {
        super(name, hourleySalary);
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {

        finalSalary = hoursWorked * baseSalary;
        return finalSalary;
    }

}

public class AbstractClass {
    public static void main(String[] args) {
        Employee emp1 = new FullTimeEmployee("Rushiraj", 80000);
        Employee emp2 = new ContractEmployee("Bhumik", 100, 100);

        System.out.println("Salary for " + emp1.name + " is " + emp1.calculateSalary());
        System.out.println("Salary for " + emp2.name + " is " + emp2.calculateSalary());
    }
}
