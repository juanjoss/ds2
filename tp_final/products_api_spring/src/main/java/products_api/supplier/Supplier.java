package products_api.supplier;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Supplier extends RepresentationModel<Supplier> {
    private int id = 0;
    private String name = "";
    
    public Supplier() {}
}
