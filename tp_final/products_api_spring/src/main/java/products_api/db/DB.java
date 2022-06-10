package products_api.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    // DB config
    private String DB_HOST = "sd2-server.mysql.database.azure.com:3306";
    private String DB_NAME = "products";
    private String DB_URL = "jdbc:mysql://" + this.DB_HOST + "/" + this.DB_NAME + "?useSSL=true";
    private String DB_USER = "sd2Admin";
    private String DB_PASSWORD = "Admin123.";

    // connection
    private Connection conn = null;

    // DB singleton
    private static DB db = new DB();

    private DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    this.DB_URL,
                    this.DB_USER,
                    this.DB_PASSWORD
            );
            System.out.println("DB Connected");

        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    public static DB getDB() {
        return db;
    }

    public Connection getConn() {
        return conn;
    }
}
