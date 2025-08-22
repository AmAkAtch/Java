package day07_multi_dimentional_array.miniproject;

import java.util.Arrays;

public class MiniProject {

    public static void displyaTicTacToe() {
        char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 1; col++) {
                System.out.print(board[row][col] + "|");
            }
            System.out.println();
        }
    }

    public static void scheduleEmployeeShifts() {
        String[][] shifts = new String[7][3];
        String[] employees = { "employee1", "employee2", "employee3", "employee4" };

        for (int day = 0; day < shifts.length; day++) {
            for (int shift = 0; shift < shifts[day].length; shift++) {

                // commen equation to turn 2d index into 1d index
                int index = (day * shifts[day].length + shift) % employees.length;
                shifts[day][shift] = employees[index];
            }
        }
        System.out.println(Arrays.deepToString(shifts));
    }

    public static void main(String[] args) {
        displyaTicTacToe();
        scheduleEmployeeShifts();
    }
}
