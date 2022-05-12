package tp3.products_api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tp3.products_api.db.DB;
import tp3.products_api.model.Supplier;

public class SupplierDAO {

    public SupplierDAO() {}

    public List<Supplier> getAll() throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        List<Supplier> suppliers = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM suppliers");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Supplier s = new Supplier();
            s.setId(rs.getInt("id_supplier"));
            s.setName(rs.getString("name"));

            suppliers.add(s);
        }

        return suppliers;
    }

    public Supplier get(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        Supplier s = new Supplier();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM suppliers WHERE id_supplier = " + id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            s.setId(rs.getInt("id_supplier"));
            s.setName(rs.getString("name"));
        }

        return s;
    }

    public void add(Supplier s) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "INSERT INTO suppliers (name) "
                + "VALUES ('"
                + s.getName() + "'"
                + ")"
        );
    }

    public void update(Supplier s) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(""
                + "UPDATE suppliers SET name = '"
                + s.getName() + "'"
                + " WHERE id_supplier = " + s.getId()
        );
    }

    public void delete(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM suppliers WHERE id_supplier = " + id);
    }
}
