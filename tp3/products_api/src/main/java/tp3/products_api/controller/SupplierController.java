package tp3.products_api.controller;

import java.sql.SQLException;
import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;
import tp3.products_api.dao.SupplierDAO;
import tp3.products_api.model.Supplier;
import tp3.products_api.util.JsonTransformer;

public class SupplierController {

    private SupplierDAO supplierDAO = null;

    public SupplierController(final SupplierDAO supplierDAO, JsonTransformer jsonTransformer) {
        this.supplierDAO = supplierDAO;

        path("/api", () -> {
            path("/suppliers", () -> {
                // get all suppliers
                get("/all", (req, res) -> {
                    try {
                        return this.supplierDAO.getAll();
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // get supplier
                get("/:id", (req, res) -> {
                    try {
                        return this.supplierDAO.get(Integer.parseInt(req.params("id")));
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // add supplier
                post("/add", (req, res) -> {
                    try {
                        Supplier s = new Supplier();
                        s.setName(req.queryParams("name"));

                        this.supplierDAO.add(s);
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                    }

                    return res.body();
                });

                // update supplier
                put("/:id", (req, res) -> {
                    try {
                        Supplier s = new Supplier();
                        s.setId(Integer.parseInt(req.params("id")));
                        s.setName(req.queryParams("name"));

                        this.supplierDAO.update(s);
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
                        this.supplierDAO.delete(Integer.parseInt(req.params("id")));
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
