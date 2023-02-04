import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {

    private Connection con;


    public Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolistdb",
                    "root", "x");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to create database connection!");
        }
        return con;
    }
}

