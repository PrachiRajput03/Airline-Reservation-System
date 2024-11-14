# Airline-Reservation-System
This is a Java-based Airline Reservation System project built using Core Java, MySQl, HTML, CSS, and JavaScript. The system allows users to book flights, cancel bookings, and manage passenger details with a connection to a MySQL database.

# Features
* Book a flight by providing passenger details and flight information.
* Cancel an existing booking using a booking ID.
* View and manage passenger stored in the database.

# Technologies used
* Programming language: Java(Core Java)
* Database: MySQl
* Frontend: HTML, CSS, JavaScript
* JDBC driver: MySQL Connector/J

# Requirements
1. Java Development kit (JDK) 8 or later.
2. MySQL Server or phpMyAdmin for database management.
3. MySQL Connector/J (JDBC Driver).
4. An IDE (eg. IntelliJ IDEA, Eclipse) or text editor with a terminal.

# Database Configuration
* Database name: AirlineReservationSystem
* Tables: Passengers
          Flights
          Bookings

# Features Walkthrough
1. Book a flight:
   * Select the "Book Flight" option.
   * Enter the following details:
     Passenger name, email, phone
     Flight Number (must match an existing flight in the fligth table)
   * If successfull, the booking is saved in the Bookings Table.
  
2. Canceling a Booking:
   * Select the "Cancel Booking" option.
   * Enter the "Booking ID".
   * The booking is removed from the Bookings Table.

# Future Improvements
* Add a GUI using JavaFX or Swing.
* Implement advanced search and filtering for flights.
* Add authentication and user roles (eg. admin, customer).
* Enable notifications for booking confirmations (via Email or SMS).
