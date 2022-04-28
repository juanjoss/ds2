package tp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                    + "from_city VARCHAR(25) NOT NULL,"
                    + "to_city VARCHAR(25) NOT NULL,"
                    + "departure_date DATE NOT NULL,"
                    + "return_date DATE,"
                    + "remaining_spots INT(3) NOT NULL,"
                    + "company_name VARCHAR(25) NOT NULL,"
                    + "PRIMARY KEY(from_city,to_city,departure_date, company_name)"
                    + ")");

            System.out.println("Table ticket created successfully...");

            // inserting test tickets
            stmt.executeUpdate("INSERT INTO tickets (from_city, to_city, departure_date, return_date, remaining_spots, company_name) "
                    + "VALUES ('General Pico', 'Santa Rosa', STR_TO_DATE('28/04/2022','%d/%m/%Y'),STR_TO_DATE('29/04/2022','%d/%m/%Y'),20,'DumasCat');");

            stmt.executeUpdate("INSERT INTO tickets (from_city, to_city, departure_date, remaining_spots, company_name) "
                    + "VALUES ('General Pico', 'Santa Rosa', STR_TO_DATE('28/04/2022','%d/%m/%Y'),10,'Transur');");

            stmt.executeUpdate("INSERT INTO tickets (from_city, to_city, departure_date, return_date, remaining_spots, company_name) "
                    + "VALUES ('General Pico', 'Santa Rosa', STR_TO_DATE('28/04/2022','%d/%m/%Y'),STR_TO_DATE('29/04/2022','%d/%m/%Y'),0,'AlgunaOtraEmpresa');");

            stmt.executeUpdate("INSERT INTO tickets (from_city, to_city, departure_date, return_date, remaining_spots, company_name) "
                    + "VALUES ('Santa Rosa', 'General Pico', STR_TO_DATE('26/04/2022','%d/%m/%Y'),STR_TO_DATE('30/04/2022','%d/%m/%Y'),20,'DumasCat');");

        } catch (SQLException e) {
            System.out.println("Error initializing " + this.DB_NAME + " database...");
            e.printStackTrace();
        }
    }

    @WebMethod(operationName = "ticketSearch")
    public List<Ticket> ticketSearch(@WebParam(name = "from") String from, @WebParam(name = "to") String to, @WebParam(name = "departure_date") String departure_date, @WebParam(name = "return_date") String return_date) throws ClassNotFoundException {
        List<Ticket> response = new ArrayList();
        
        if (!this.dbExists) {
            this.initDB();
        }
        String query;
        if(!(return_date == null)){
            query = "SELECT * FROM tickets WHERE from_city = '" + from + "' AND to_city = '" + to + "' AND departure_date = STR_TO_DATE('" + departure_date + "','%d/%m/%Y') AND return_date = STR_TO_DATE('" + return_date + "','%d/%m/%Y') AND remaining_spots>0;";
        }else{
            query = "SELECT * FROM tickets WHERE from_city = '" + from + "' AND to_city = '" + to + "' AND departure_date = STR_TO_DATE('" + departure_date + "','%d/%m/%Y') AND remaining_spots>0;";
        }
        
        System.out.println(query);

        try {
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket t = null;
                if(!(return_date == null))
                    t = new Ticket(rs.getString("from_city"), rs.getString("to_city"), rs.getDate("departure_date"), rs.getDate("return_date"), rs.getInt("remaining_spots"), rs.getString("company_name"));
                else
                    t = new Ticket(rs.getString("from_city"), rs.getString("to_city"), rs.getDate("departure_date"), rs.getInt("remaining_spots"), rs.getString("company_name"));
                response.add(t);
            }
            return response;

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

        return null;
    }
}
