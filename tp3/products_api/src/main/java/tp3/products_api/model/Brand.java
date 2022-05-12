package tp3.products_api.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Brand {
    @Expose
    private int id = 0;
    @Expose
    private String name = "";
    
    public Brand() {}
}
