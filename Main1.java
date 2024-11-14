import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        DatabaseConnection.connect();
        Booking bookingSystem  = new Booking();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Book Flight");
        System.out.println("2: Cancel Booking");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                bookingSystem.bookFlight();
                break;
            case 2:
                bookingSystem.cancelBooking();
                break;
            default:
                System.out.println("Invalid choice: ");
        }
    }
}