import java.sql.*;
import java.util.Scanner;

public class Booking {
    public void bookFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter passenger Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Passenger Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();

        try(Connection conn = DatabaseConnection.connect()) {
            //Check if the flight exits and has available seats.
            String flightQuery = "SELECT FlightID, SeatsAvailable FROM Flights WHERE FlightNumber = ?";
            PreparedStatement flightStmt = conn.prepareStatement(flightQuery);
            flightStmt.setString(1, flightNumber);
            ResultSet flightRs = flightStmt.executeQuery();
            if(flightRs.next()) {
                int flightID = flightRs.getInt("FlightId");
                int seatsAvailable = flightRs.getInt("SeatsAvailable");
                if(seatsAvailable > 0) {
                    //Insert passenger information
                    String passengerQuery = "INSERT INTO Passengers (Name, Email, Phone) VALUES (?, ?, ?)";

                    PreparedStatement passengerStmt = conn.prepareStatement(passengerQuery, Statement.RETURN_GENERATED_KEYS);
                    passengerStmt.setString(1, name);
                    passengerStmt.setString(2, email);
                    passengerStmt.setString(3, phone);
                    passengerStmt.executeUpdate();

                    ResultSet generatedKeys = passengerStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int passengerID = generatedKeys.getInt(1);

                        //Insert booking information
                        String bookingQuery = "INSERT INTO Bookings (PassengerID, FlightID, BookingDate) VALUES (?, ?, CURDATE())";

                        PreparedStatement bookingStmt = conn.prepareStatement(bookingQuery);
                        bookingStmt.setInt(1, passengerID);
                        bookingStmt.setInt(2, flightID);
                        bookingStmt.executeUpdate();

                        //Update available seats
                        String updateSeatsQuery = "UPDATE Flights SET SeatsAvailable = SeatsAvailable -1 WHERE FlightID =?";
                        PreparedStatement updateSeatsStmt = conn.prepareStatement(updateSeatsQuery);
                        updateSeatsStmt.setInt(1, flightID);
                        updateSeatsStmt.executeUpdate();

                        System.out.println("Booking successful! Passenger ID: "+passengerID);
                    } 

                } else {
                    System.out.println("No seats available for this flight.");
                }
            } else {
                System.out.println("Flight not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cancelBooking() {
        //code to cancel booking by removing it from the database
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Booking ID to cancel: ");
        int bookingID = scanner.nextInt();

        try (Connection conn = DatabaseConnection.connect()) {
            //Check if the booking exists
            String bookingQuery = "SELECT FlightID FROM Bookings WHERE BookingID = ?";
            PreparedStatement bookingStmt = conn.prepareStatement(bookingQuery);
            bookingStmt.setInt(1, bookingID);
            ResultSet bookingRs = bookingStmt.executeQuery();
            if (bookingRs.next()){
                int flightID = bookingRs.getInt("FlightID");

                //Delete the booking
                String deleteBookingQuery = "DELETE FROM Bookings WHERE BookingID = ?";
                PreparedStatement deleteBookingStmt = conn.prepareStatement(deleteBookingQuery);
                deleteBookingStmt.setInt(1, bookingID);

                deleteBookingStmt.executeUpdate();

                //Update available seats

                String updateSeatsQuery = "UPDATE Flights SET SeatsAvailable = SeatsAvaialble +1 WHERE FlightID = ?";
                PreparedStatement updateSeatsStmt = conn.prepareStatement(updateSeatsQuery);
                updateSeatsStmt.setInt(1, flightID);
                updateSeatsStmt.executeUpdate();

                System.out.println("Booking canceled successfully.");

            } else {
                System.out.println("Booking ID not found.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
