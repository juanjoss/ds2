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

@WebService(serviceName = "TicketSearch")
@Stateless()
public class TicketSearch {

    // DB config
    private String DB_HOST = "20.213.47.77";
    private String DB_NAME = "tickets";
    private String DB_URL = "jdbc:mysql://" + this.DB_HOST + "/" + this.DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false";
    private String DB_USER = "admin";
    private String DB_PASSWORD = "adminadmin";
    private boolean dbExists = false;
    
    // queries
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    private void initDB() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + this.DB_HOST + "?allowPublicKeyRetrieval=true&useSSL=false", 
                    this.DB_USER, 
                    this.DB_PASSWORD
            );

            // creating database
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + this.DB_NAME + ";");

            this.dbExists = true;
            System.out.println("Database " + this.DB_NAME + " created successfully...");

            // selecting tickets database
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
            
            // creating companies and tickets table
            stmt = conn.createStatement();
            stmt.executeUpdate(""
                    + "CREATE TABLE IF NOT EXISTS tickets("
                    + "from VARCHAR(25) NOT NULL,"
                    + "to VARCHAR(25) NOT NULL,"
                    + "departure_date DATE NOT NULL,"
                    + "return_date DATE,"
                    + "remaining_spots INT(3) NOT NULL,"
                    + "company_name VARCHAR(25) NOT NULL,"
                    + "PRIMARY KEY(from,to,departure_date),"
                    + ")");

            System.out.println("Tables created successfully...");

            // inserting test tickets
            stmt.executeUpdate("INSERT INTO tickets (from, to, departure_date, return_date, remaining_spots, company_name) VALUES ('General Pico', 'Santa Rosa', )");
            stmt.executeUpdate("INSERT INTO products (barcode, name, price) VALUES (111111111, 'fideos', 200)");
            stmt.executeUpdate("INSERT INTO products (barcode, name, price) VALUES (123456789, 'fideosnt, 200)");
            stmt.executeUpdate("INSERT INTO products (barcode, name, price) VALUES (987654321, 'Alfajor Capitán del Espacio', 150)");

        } catch (SQLException e) {
            System.out.println("Error initializing " + this.DB_NAME + " database...");
            e.printStackTrace();
        }
    }
    @WebMethod(operationName = "ticketSearch")
    public String ticketSearch(@WebParam(name = "from") String from, @WebParam(name = "to") String to, @WebParam(name = "departure_date") String departure, @WebParam(name = "return_date") String return_date) {
        return "Hello " + from + " !";
    }
}
