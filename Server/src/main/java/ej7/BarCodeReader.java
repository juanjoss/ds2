package ej7;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BarCodeReader extends UnicastRemoteObject implements RemoteBarCodeReader {

    ArrayList<Product> products = new ArrayList<Product>();

    protected BarCodeReader() throws RemoteException {
        super();
        products.add(new Product(123456789, "Fideos", 90));
        products.add(new Product(789456123, "Atún", 225));
        products.add(new Product(753862845, "Jabón líquido", 762));
        products.add(new Product(456789123, "Agua mineral", 150));
        products.add(new Product(000111547, "Aceite de Oliva", 676));
    }

    @Override
    public String checkPrice(int code) throws RemoteException {
        Product productTmp = new Product();
        String response = "";

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getBarCode() == code) {
                productTmp = products.get(i);
                response = productTmp.getName() + " : $" + String.valueOf(productTmp.getPrice());
                break;
            } else {
                response = "Producto no encontrado";
            }
        }
        return response;
    }

}
