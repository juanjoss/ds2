package products_api.product_type;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductType extends RepresentationModel<ProductType> {
    private int id = 0;
    private String name = "";
    
    public ProductType() {}
}
