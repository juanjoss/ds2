package products_api.product_type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import products_api.db.DB;

public class ProductTypeDAO {
    public ProductTypeDAO() {}

    public ProductType get(int id) throws SQLException {
        DB db = DB.getDB();
        Connection conn = db.getConn();
        ProductType pt = new ProductType();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM product_types WHERE id = " + id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            pt.setId(rs.getInt("id"));
            pt.setName(rs.getString("name"));
        }

        return pt;
    }
}
