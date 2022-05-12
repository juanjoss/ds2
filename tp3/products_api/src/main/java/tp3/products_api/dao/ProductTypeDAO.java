package tp3.products_api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tp3.products_api.db.DB;
import tp3.products_api.model.ProductType;

public class ProductTypeDAO {

    public ProductTypeDAO() {}

    public List<ProductType> getAll() throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        List<ProductType> types = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM product_types");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ProductType pt = new ProductType();
            pt.setId(rs.getInt("id_type"));
            pt.setName(rs.getString("name"));

            types.add(pt);
        }

        return types;
    }

    public ProductType get(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        ProductType pt = new ProductType();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM product_types WHERE id_type = " + id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            pt.setId(rs.getInt("id_type"));
            pt.setName(rs.getString("name"));
        }

        return pt;
    }

    public void add(ProductType pt) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "INSERT INTO product_types (name) "
                + "VALUES ('"
                + pt.getName() + "'"
                + ")"
        );
    }

    public void update(ProductType pt) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "UPDATE product_types SET name = '"
                + pt.getName() + "'"
                + " WHERE id_type = " + pt.getId()
        );
    }

    public void delete(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM product_types WHERE id_type = " + id);
    }
}
