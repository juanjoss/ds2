package server.ej7;

public class Product {

    public int barCode;
    public String name;
    public double price;

    public Product(int barCode, String name, double price) {
        if (String.valueOf(barCode).length() == 9) {
            this.barCode = barCode;
        }

        if (!name.isBlank()) {
            this.name = name;
        }

        if (price >= 0) {
            this.price = price;
        }
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
