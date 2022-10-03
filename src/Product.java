import java.util.ArrayList;

/*
public record Product(String name, Double price, Category category, String brand, String productID) {
*/

public class Product {
    String name;
    Double price;
    Category category;
    String brand;
    String productID;

    public Product(String name, Double price, Category category, String brand, String productID) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Product info " + "\n" +
                "Name: " + name + "\n" +
                "Price: " + price + "\n" +
                "Category: " + category + "\n" +
                "Brand: " + brand + "\n" +
                "ProductID: " + productID + "\n";
    }

    public String printProducts() {
        return name;
    }
}