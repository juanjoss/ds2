package tp3.products_api.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Link {
    @Expose
    private String rel = "";
    @Expose
    private String href = "";
    @Expose
    private String type = "";
    
    public Link(String rel, String href, String type) {
        this.rel = rel;
        this.href = href;
        this.type = type;
    }
}
