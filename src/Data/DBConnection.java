package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;
    private String username = "root";
    private String password = "root1234";
    private String DBName = "onlinefaculty";
    public String url = "jdbc:mysql://localhost:3306/" + DBName + "?autoReconnect=true&useSSL=false&characterEncoding=utf8";
    public String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private DBConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
			System.out.println("\nConnecting to database...");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!\n");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }

        return instance;
    }
}

