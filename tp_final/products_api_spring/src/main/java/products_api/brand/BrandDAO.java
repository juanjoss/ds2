package products_api.brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import products_api.db.DB;

public class BrandDAO {

    public BrandDAO() {}

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
}
