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

@WebService(serviceName = "BarCodeReader")
@Stateless()
public class BarCodeReader {

    // DB config
    private String DB_HOST = "20.213.47.77";
    private String DB_NAME = "products";
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

            // selecting users database
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);

            // creating users table
            stmt = conn.createStatement();
            stmt.executeUpdate(""
                    + "CREATE TABLE IF NOT EXISTS products("
                    + "barcode INT(10) NOT NULL,"
                    + "name VARCHAR(25) NOT NULL,"
                    + "price DECIMAL(10,2) NOT NULL,"
                    + "PRIMARY KEY(name)"
                    + ")");

            System.out.println("Table users created successfully...");

            // inserting test products
            stmt.executeUpdate("INSERT INTO products (barcode, name, price) VALUES (010101010, 'maniSEEEETO', 200)");
            stmt.executeUpdate("INSERT INTO products (barcode, name, price) VALUES (111111111, 'fideos', 200)");
            stmt.executeUpdate("INSERT INTO products (barcode, name, price) VALUES (123456789, 'fideosnt', 200)");
            stmt.executeUpdate("INSERT INTO products (barcode, name, price) VALUES (987654321, 'Alfajor', 150)");

        } catch (SQLException e) {
            System.out.println("Error initializing " + this.DB_NAME + " database...");
            e.printStackTrace();
        }
    }

    @WebMethod(operationName = "search")
    public String barCodeSearch(@WebParam(name = "barCode") String barCode) throws ClassNotFoundException {
        if (!this.dbExists) {
            this.initDB();
        }
        try {
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
            ps = conn.prepareStatement("SELECT * FROM products WHERE barcode = " + barCode + ";");
            rs = ps.executeQuery();
            if(rs.next()){
                String result = rs.getString("name") + ": $" + rs.getString("price");
                return result;
            }else{
                return "producto no encontrado.";
            }
            
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

        return "Error.";
    }
}
