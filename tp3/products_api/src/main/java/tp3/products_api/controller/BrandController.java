package tp3.products_api.controller;

import java.sql.SQLException;
import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;
import tp3.products_api.dao.BrandDAO;
import tp3.products_api.model.Brand;
import tp3.products_api.util.JsonTransformer;

public class BrandController {

    private BrandDAO brandDAO = null;

    public BrandController(final BrandDAO brandDAO, JsonTransformer jsonTransformer) {
        this.brandDAO = brandDAO;

        path("/api", () -> {
            path("/brands", () -> {
                // get all brands
                get("/all", (req, res) -> {
                    try {
                        return this.brandDAO.getAll();
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // get brand
                get("/:id", (req, res) -> {
                    try {
                        return this.brandDAO.get(Integer.parseInt(req.params("id")));
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                        return res.body();
                    }
                }, jsonTransformer);

                // add brand
                post("/add", (req, res) -> {
                    try {
                        Brand brand = new Brand();
                        brand.setName(req.queryParams("name"));

                        this.brandDAO.add(brand);
                        res.status(200);
                        res.body("HTTP 200 OK");
                    } catch (SQLException ex) {
                        res.status(500);
                        res.body("HTTP 500 internal server error");
                    }

                    return res.body();
                });

                // update brand
                put("/:id", (req, res) -> {
                    try {
                        Brand brand = new Brand();
                        brand.setId(Integer.parseInt(req.params("id")));
                        brand.setName(req.queryParams("name"));

                        this.brandDAO.update(brand);
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
                        this.brandDAO.delete(Integer.parseInt(req.params("id")));
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
