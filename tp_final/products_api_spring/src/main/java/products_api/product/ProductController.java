package products_api.product;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import products_api.brand.BrandController;
import products_api.product_type.ProductTypeController;
import products_api.supplier.SupplierController;

@RestController
public class ProductController {

    private ProductDAO productDAO = new ProductDAO();

    public ProductController() {}

    @GetMapping("/api/products")
    public HttpEntity<List<Product>> getAll() {
        try {
            List<Product> products = this.productDAO.getAll();

            for (Product p : products) {
                p.add(linkTo(methodOn(ProductController.class).get(p.getId())).withSelfRel());
                p.add(linkTo(methodOn(BrandController.class).get(p.getBrandId())).withRel("brands"));
                p.add(linkTo(methodOn(ProductTypeController.class).get(p.getTypeId())).withRel("product_types"));
                p.add(linkTo(methodOn(SupplierController.class).get(p.getSupplierId())).withRel("suppliers"));
            }

		    return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        } catch (SQLException e) {return null;}
    }

    @GetMapping("/api/products/{id}")
    public HttpEntity<Product> get(@PathVariable int id) {
        try {
            Product product = this.productDAO.get(id);
            product.add(linkTo(methodOn(ProductController.class).get(id)).withSelfRel());
            product.add(linkTo(methodOn(BrandController.class).get(product.getBrandId())).withRel("brands"));
            product.add(linkTo(methodOn(ProductTypeController.class).get(product.getTypeId())).withRel("product_types"));
            product.add(linkTo(methodOn(SupplierController.class).get(product.getSupplierId())).withRel("suppliers"));

		    return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (SQLException e) {return null;}
    }

    @PostMapping("/api/products")
    public void add(@RequestBody Product product) {
        try {
            this.productDAO.add(product);
        } catch (SQLException e) {}
    }

    @PutMapping("/api/products/{id}")
    public void update(@RequestBody Product product, @PathVariable int id) {
        try {
            product.setId(id);
            this.productDAO.update(product);
        } catch (SQLException e) {}
    }

    @DeleteMapping("/api/products/{id}")
    public void delete(@PathVariable int id) {
        try {
            this.productDAO.delete(id);
        } catch (SQLException e) {}
    }
}


