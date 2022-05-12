package tp3.products_api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTransformer implements spark.ResponseTransformer {

    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
