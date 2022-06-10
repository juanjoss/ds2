package products_api.product_type;

import java.sql.SQLException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductTypeController {
    private ProductTypeDAO ptDAO = new ProductTypeDAO();

    public ProductTypeController() {
    }

    @GetMapping("/api/productTypes/{id}")
    public HttpEntity<ProductType> get(@PathVariable int id) {
        try {
            ProductType pt = this.ptDAO.get(id);
            pt.add(linkTo(methodOn(ProductTypeController.class).get(id)).withSelfRel());

		    return new ResponseEntity<ProductType>(pt, HttpStatus.OK);
        } catch (SQLException e) {
            return null;
        }
    }
}
