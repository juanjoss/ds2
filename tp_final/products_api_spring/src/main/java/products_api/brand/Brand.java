package products_api.brand;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Brand extends RepresentationModel<Brand> {
    private int id = 0;
    private String name = "";
    
    public Brand() {}
}
