package tp3.products_api.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    // DB config
    private String DB_HOST = "jjoss";
    private String DB_NAME = "products";
    private String DB_URL = "jdbc:mysql://" + this.DB_HOST + "/" + this.DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false";
    private String DB_USER = "root";
    private String DB_PASSWORD = "root";

    // connection
    private Connection conn = null;

    // DB singleton
    private static DB db = new DB();

    private DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + this.DB_HOST + "?allowPublicKeyRetrieval=true&useSSL=false",
                    this.DB_USER,
                    this.DB_PASSWORD
            );

            // creating database
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + this.DB_NAME);

            // selecting database
            conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);

            // creating tables
            stmt = conn.createStatement();

            stmt.executeUpdate(""
                    + "CREATE TABLE IF NOT EXISTS suppliers ("
                    + "id_supplier INT NOT NULL AUTO_INCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "PRIMARY KEY(id_supplier) "
                    + ")");

            stmt.executeUpdate(""
                    + "CREATE TABLE IF NOT EXISTS brands ("
                    + "id_brand INT NOT NULL AUTO_INCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "PRIMARY KEY(id_brand)"
                    + ")");

            stmt.executeUpdate(""
                    + "CREATE TABLE IF NOT EXISTS product_types ("
                    + "id_type INT NOT NULL AUTO_INCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "PRIMARY KEY(id_type)"
                    + ")");

            stmt.executeUpdate(""
                    + "CREATE TABLE IF NOT EXISTS products ("
                    + "id_product INT NOT NULL AUTO_INCREMENT, "
                    + "id_brand INT NOT NULL, "
                    + "id_type INT NOT NULL, "
                    + "id_supplier INT NOT NULL, "
                    + "name TEXT NOT NULL, "
                    + "bar_code INT NOT NULL, "
                    + "price FLOAT NOT NULL, "
                    + "PRIMARY KEY(id_product), "
                    + "FOREIGN KEY (id_brand) REFERENCES brands(id_brand) ON DELETE CASCADE, "
                    + "FOREIGN KEY (id_type) REFERENCES product_types(id_type) ON DELETE CASCADE, "
                    + "FOREIGN KEY (id_supplier) REFERENCES suppliers(id_supplier) ON DELETE CASCADE"
                    + ")");
        } catch (SQLException | ClassNotFoundException e) {}
    }

    public static DB getDB() {
        return db;
    }

    public Connection getConn() {
        return conn;
    }
}
