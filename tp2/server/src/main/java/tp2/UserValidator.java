package tp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "UserValidator")
@Stateless()
public class UserValidator {

    // DB config
    private String DB_HOST = "jjoss";
    private String DB_NAME = "users";
    private String DB_URL = "jdbc:mysql://" + this.DB_HOST + "/" + this.DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false";
    private String DB_USER = "root";
    private String DB_PASSWORD = "root";
    private boolean dbExists = false;

    // queries
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void initDB() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://jjoss?allowPublicKeyRetrieval=true&useSSL=false", 
                    this.DB_USER, 
                    this.DB_PASSWORD
            );

            // creating database
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + this.DB_NAME + ";");

            this.dbExists = true;
            System.out.println("Database " + this.DB_NAME + " created successfully...");

            // selecting users database
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
            
            // creating users table
            stmt = conn.createStatement();
            stmt.executeUpdate(""
                    + "CREATE TABLE IF NOT EXISTS users("
                    + "username VARCHAR(25) NOT NULL,"
                    + "password VARCHAR(25) NOT NULL,"
                    + "PRIMARY KEY(username)"
                    + ")");

            System.out.println("Table users created successfully...");

            // inserting test user
            stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('admin', 'admin')");

            System.out.println("Record user = (admin, admin) created successfully...");
        } catch (SQLException e) {
            System.out.println("Error initializing " + this.DB_NAME + " database...");
            e.printStackTrace();
        }
    }

    @WebMethod(operationName = "validate")
    public Boolean validate(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws ClassNotFoundException {
        if (!this.dbExists) {
            this.initDB();
        }

        try {
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';");
            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }

                rs = null;
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlEx) {
                }

                ps = null;
            }
        }

        return false;
    }
}
