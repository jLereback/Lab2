public record Product(String name, Double price, Category category, String brand, String productID) {

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