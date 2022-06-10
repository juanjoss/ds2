package tp3.products_api.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;
import tp3.products_api.dao.ProductDAO;
import tp3.products_api.model.Product;
import tp3.products_api.util.JsonTransformer;

public class ProductController {

    private final ProductDAO productDAO;

    public ProductController(final ProductDAO productDAO, JsonTransformer jsonTransformer) {
        this.productDAO = productDAO;

        path("/api", () -> {
            path("/products", () -> {
                // get all products
                get("", (req, res) -> {
                    try {
                        return this.productDAO.getAll();
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // get product
                get("/:id", (req, res) -> {
                    try {
                        return this.productDAO.get(Integer.parseInt(req.params("id")));
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // add product
                post("", (req, res) -> {
                    try {
                        Product product = new Product();

                        product.setBrandId(Integer.parseInt(req.queryParams("id_brand")));
                        product.setTypeId(Integer.parseInt(req.queryParams("id_type")));
                        product.setSupplierId(Integer.parseInt(req.queryParams("id_supplier")));
                        product.setName(req.queryParams("name"));
                        product.setBarcode(Integer.parseInt(req.queryParams("barcode")));
                        product.setPrice(Double.parseDouble(req.queryParams("price")));

                        this.productDAO.add(product);
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                    }

                    return res.body();
                });

                // update product
                put("/:id", (req, res) -> {
                    try {
                        Product product = new Product();

                        product.setId(Integer.parseInt(req.params("id")));
                        product.setBrandId(Integer.parseInt(req.queryParams("id_brand")));
                        product.setTypeId(Integer.parseInt(req.queryParams("id_type")));
                        product.setSupplierId(Integer.parseInt(req.queryParams("id_supplier")));
                        product.setName(req.queryParams("name"));
                        product.setBarcode(Integer.parseInt(req.queryParams("barcode")));
                        product.setPrice(Double.parseDouble(req.queryParams("price")));

                        this.productDAO.update(product);
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                    }

                    return res.body();
                }, jsonTransformer);

                // delete product
                delete("/:id", (req, res) -> {
                    try {
                        this.productDAO.delete(Integer.parseInt(req.params("id")));
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                    }

                    return res.body();
                }, jsonTransformer);
            });
        });

        // response headers
        after((req, res) -> {
            res.type("application/json");
        });
    }
}
