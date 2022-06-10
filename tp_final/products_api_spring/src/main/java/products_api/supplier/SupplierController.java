package products_api.supplier;

import java.sql.SQLException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {
    private SupplierDAO supplierDAO = new SupplierDAO();

    public SupplierController() {
    }

    @GetMapping("/api/suppliers/{id}")
    public HttpEntity<Supplier> get(@PathVariable int id) {
        try {
            Supplier supplier = this.supplierDAO.get(id);
            supplier.add(linkTo(methodOn(SupplierController.class).get(id)).withSelfRel());

		    return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
        } catch (SQLException e) {
            return null;
        }
    }
}
