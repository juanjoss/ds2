package tp3.products_api.controller;

import java.sql.SQLException;
import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;
import tp3.products_api.dao.ProductTypeDAO;
import tp3.products_api.model.ProductType;
import tp3.products_api.util.JsonTransformer;

public class ProductTypeController {

    private ProductTypeDAO ptDAO = null;

    public ProductTypeController(final ProductTypeDAO ptDAO, JsonTransformer jsonTransformer) {
        this.ptDAO = ptDAO;

        path("/api", () -> {
            path("/productTypes", () -> {
                // get all product types
                get("/all", (req, res) -> {
                    try {
                        return this.ptDAO.getAll();
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // get product type
                get("/:id", (req, res) -> {
                    try {
                        return this.ptDAO.get(Integer.parseInt(req.params("id")));
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // add product type
                post("/add", (req, res) -> {
                    try {
                        ProductType pt = new ProductType();
                        pt.setName(req.queryParams("name"));

                        this.ptDAO.add(pt);
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                    }

                    return res.body();
                });

                // update product type
                put("/:id", (req, res) -> {
                    try {
                        ProductType pt = new ProductType();
                        pt.setId(Integer.parseInt(req.params("id")));
                        pt.setName(req.queryParams("name"));

                        this.ptDAO.update(pt);
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                    }

                    return res.body();
                }, jsonTransformer);

                // delete brand
                delete("/:id", (req, res) -> {
                    try {
                        this.ptDAO.delete(Integer.parseInt(req.params("id")));
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
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
