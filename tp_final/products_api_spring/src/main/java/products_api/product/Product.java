package products_api.product;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Product extends RepresentationModel<Product> {
    private int id = 0;
    private int barcode = 999999999;
    private String name = "";
    private double price = 0;
    private int brandId = 0;
    private int typeId = 0;
    private int supplierId = 0;

    public Product() {}
}