package products_api.brand;

import java.sql.SQLException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController {

    private BrandDAO brandDAO = new BrandDAO();

    public BrandController() {
    }

    @GetMapping("/api/brands/{id}")
    public HttpEntity<Brand> get(@PathVariable int id) {
        try {
            Brand brand = this.brandDAO.get(id);
            brand.add(linkTo(methodOn(BrandController.class).get(id)).withSelfRel());

		    return new ResponseEntity<Brand>(brand, HttpStatus.OK);
        } catch (SQLException e) {
            return null;
        }
    }
}
