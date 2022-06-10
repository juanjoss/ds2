package products_api.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import products_api.db.DB;

public class SupplierDAO {
    public SupplierDAO() {}

    public Supplier get(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        Supplier s = new Supplier();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM suppliers WHERE id = " + id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
        }

        return s;
    }
}
