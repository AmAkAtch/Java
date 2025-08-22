package day07_multi_dimentional_array.seatbooking;

public class SeatBooking {

    static boolean[][] seats = new boolean[3][8]; // theater seats layout with 10 rows and 20 columns

    public static void main(String[] args) {
        displaySeats();
        bookSeat(1, 2);
        displaySeats();
        bookSeat(1, 2);
    }

    public static void bookSeat(int bookRow, int bookCol) {

        if (!seats[bookRow][bookCol]) {
            seats[bookRow][bookCol] = true;
            System.out.println("Booking of seats on row " + bookRow + " and col " + bookCol + " is successfull");
        } else {
            System.out.println("Seat is already taken please try different seats");
        }
    }

    // method to display seats
    public static void displaySeats() {
        /*
         * This method iterates over rows and colum to check if the seat is true (taken)
         * else false(open to take) and print X for taken and o for open
         */
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col] ? "X" : "O"); // if the seat in perticual row and column is true print
                                                               // X(taken) else O (open)
            }
            System.out.println();
        }
    }
}
