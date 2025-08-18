package day04_loops.payroll_processor;

public class LoopSpeedTest {
    public static void main(String[] args) {
        System.out.println("Standard for:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Iteration: " + i);
        }

        System.out.println("For-each:");
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.println("Iteration: " + num);
        }

        System.out.println("While (less optimal here):");
        int j = 1;
        while (j <= 5) {
            System.out.println("Iteration: " + j);
            j++;
        }
    }
}
