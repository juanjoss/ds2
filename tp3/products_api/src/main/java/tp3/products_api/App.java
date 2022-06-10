package tp3.products_api;

import tp3.products_api.controller.BrandController;
import tp3.products_api.controller.ProductTypeController;
import tp3.products_api.controller.ProductController;
import tp3.products_api.controller.SupplierController;

import tp3.products_api.dao.BrandDAO;
import tp3.products_api.dao.ProductDAO;
import tp3.products_api.dao.ProductTypeDAO;
import tp3.products_api.dao.SupplierDAO;

import tp3.products_api.util.JsonTransformer;

public class App {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        JsonTransformer jt = new JsonTransformer();

        new ProductController(new ProductDAO(), jt);
        new BrandController(new BrandDAO(), jt);
        new ProductTypeController(new ProductTypeDAO(), jt);
        new SupplierController(new SupplierDAO(), jt);
    }
}
