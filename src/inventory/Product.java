package inventory;

import misc.LineUp;

import java.math.BigDecimal;
import java.util.Objects;


public final class Product {
    private final String name;
    private final BigDecimal price;
    private final Category category;
    private final String brand;
    private final int productID;
    private int stock;

    public Product(String name, BigDecimal price, Category category, String brand, int productID, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.productID = productID;
        this.stock = stock;
    }

    public String getName() {

        return name;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public Category getCategory() {

        return category;
    }

    public String getBrand() {

        return brand;
    }

    public int getProductID() {

        return productID;
    }

    public int getStock() {

        return stock;
    }

    public void editStock(int stock) {

        this.stock = stock;
    }

    @Override
    public String toString() {
        var nameSpace = LineUp.name(name.length());
        var priceSpace = LineUp.price(price.toString().length());
        var categorySpace = LineUp.category(category.toString().length());
        var brandSpace = LineUp.brand(brand.length());
        var productIDSpace = LineUp.productID(String.valueOf(productID).length());

        return name + nameSpace + "| $" +
                price + priceSpace + "| " +
                category + categorySpace + "| " +
                brand + brandSpace + "| " +
                productID + productIDSpace + "| " +
                inStock(stock);
    }

    public String printInCart() {
        var nameSpace = LineUp.name(name.length());
        var priceSpace = LineUp.price(price.toString().length());
        var productIDSpace = LineUp.productID(String.valueOf(productID).length());

        return name + nameSpace + "| $" +
                price + priceSpace + "| " +
                productID + productIDSpace + "| ";
    }

    private String inStock(Integer stock) {
        if (stock <= 5)
            return stock.toString();
        else
            return "5+";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.price, that.price) &&
                Objects.equals(this.category, that.category) &&
                Objects.equals(this.brand, that.brand) &&
                Objects.equals(this.productID, that.productID) &&
                this.stock == that.stock;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price, category, brand, productID, stock);
    }
}