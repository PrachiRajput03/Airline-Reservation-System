import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/AirlineReservationSystem";
    private static final String USER = "root";
    private static final String PASS = "password";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
}
