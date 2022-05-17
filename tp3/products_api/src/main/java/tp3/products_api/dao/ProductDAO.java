package tp3.products_api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tp3.products_api.db.DB;
import tp3.products_api.model.Product;

public class ProductDAO {

    public ProductDAO() {}

    public List<Product> getAll() throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        List<Product> products = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setBarcode(rs.getInt("bar_code"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getFloat("price"));
            product.setBrandId(rs.getInt("id_brand"));
            product.setTypeId(rs.getInt("id_type"));
            product.setSupplierId(rs.getInt("id_supplier"));

            products.add(product.toHATEOAS());
        }

        return products;
    }

    public Product get(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        Product product = new Product();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = " + id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            product.setId(rs.getInt("id"));
            product.setBarcode(rs.getInt("bar_code"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getFloat("price"));
            product.setBrandId(rs.getInt("id_brand"));
            product.setTypeId(rs.getInt("id_type"));
            product.setSupplierId(rs.getInt("id_supplier"));
        }

        return product.toHATEOAS();
    }

    public void add(Product p) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "INSERT INTO products (id_brand, id_type, id_supplier, name, bar_code, price) "
                + "VALUES ("
                + p.getBrandId() + ", "
                + p.getTypeId() + ", "
                + p.getSupplierId() + ", '"
                + p.getName() + "', "
                + p.getBarcode() + ", "
                + p.getPrice()
                + ")"
        );
    }

    public void update(Product p) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "UPDATE products SET id_brand = "
                + p.getBrandId() + ", id_type = "
                + p.getTypeId() + ", id_supplier = "
                + p.getSupplierId() + ", name = '"
                + p.getName() + "', bar_code = '"
                + p.getBarcode() + "', price = "
                + p.getPrice()
                + " WHERE id = " + p.getId() + ""
        );
    }

    public void delete(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM products WHERE id = " + id);
    }
}
