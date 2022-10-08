/*
Todo: Gör om record till en klass och lägg till "stock" som en parameter
Todo: Lägg till en köpedel i master
Todo: Implementera köpedelen i customer
Todo: När man köper x varor ska "skock" minskas med samma antal
Todo: Skriva kvitto
Todo:
Todo:
Todo:
Todo:
*/


import java.math.BigDecimal;
import java.util.Objects;

public final class Product {
    private final String name;
    private final BigDecimal price;
    private final Category category;
    private final String brand;
    private final String productID;
    private final int stock;

    public Product(String name, BigDecimal price, Category category, String brand, String productID, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.productID = productID;
        this.stock = stock;
    }

    private String inStock(Integer stock) {
        if (stock < 5)
            return stock.toString();
        else
            return "5+";
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

    public String getProductID() {
        return productID;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        var nameSpace = Super.lineUpName(name.length());
        var priceSpace = Super.lineUpPrice(price.toString().length());
        var categorySpace = Super.lineUpCategory(category.toString().length());
        var brandSpace = Super.lineUpBrand(brand.length());
        var productIDSpace = Super.lineUpProductID(productID.length());

        return name + nameSpace + "| $" +
                price + priceSpace + "| " +
                category + categorySpace + "| " +
                brand + brandSpace + "| " +
                productID + productIDSpace + "| " +
                inStock(stock);
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