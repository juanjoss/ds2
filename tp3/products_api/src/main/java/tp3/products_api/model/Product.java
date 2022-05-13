package tp3.products_api.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Product {
    @Expose
    private int id = 0;
    @Expose
    private int barcode = 999999999;
    @Expose
    private String name = "";
    @Expose
    private double price = 0;
    @Expose
    private List<Link> links = new ArrayList();
    
    private int brandId = 0;
    private int typeId = 0;
    private int supplierId = 0;

    public Product() {}
    
    public Product toHATEOAS() {
        if(this.brandId > 0) {
            this.links.add(new Link(
                    "brands", 
                    "http://localhost:4567/api/brands/" + Integer.toString(this.brandId),
                    "GET"
            ));
        }
        
        if(this.supplierId > 0) {
            this.links.add(new Link(
                    "suppliers", 
                    "http://localhost:4567/api/suppliers/" + Integer.toString(this.supplierId),
                    "GET"
            ));
        }
        
        if(this.typeId > 0) {
            this.links.add(new Link(
                    "product_types", 
                    "http://localhost:4567/api/productTypes/" + Integer.toString(this.typeId),
                    "GET"
            ));
        }
        
        return this;
    }
}