import java.util.ArrayList;

public record Product(String name, Double price, Category category, String brand, String productID) {
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", brand='" + brand + '\'' +
                ", productID='" + productID + '\'' +
                '}';
    }
}
