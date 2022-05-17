package tp3.products_api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tp3.products_api.db.DB;
import tp3.products_api.model.Brand;

public class BrandDAO {

    public BrandDAO() {}

    public List<Brand> getAll() throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        List<Brand> brands = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM brands");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Brand brand = new Brand();
            brand.setId(rs.getInt("id"));
            brand.setName(rs.getString("name"));

            brands.add(brand);
        }

        return brands;
    }

    public Brand get(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        Brand brand = new Brand();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM brands WHERE id = " + id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            brand.setId(rs.getInt("id"));
            brand.setName(rs.getString("name"));
        }

        return brand;
    }

    public void add(Brand b) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "INSERT INTO brands (name) "
                + "VALUES ('"
                + b.getName() + "'"
                + ")"
        );
    }

    public void update(Brand b) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "UPDATE brands SET name = '"
                + b.getName() + "'"
                + " WHERE id = " + b.getId()
        );
    }

    public void delete(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM brands WHERE id = " + id);
    }
}
