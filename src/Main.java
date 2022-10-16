import inventory.Category;
import inventory.Product;
import files.Files;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        List<Category> categoryList = Files.importCategoryListFromFile();
        List<Product> products = Files.importProductsFromFile();
        var shoppingCart = new HashMap<Product, Integer>();
        Start.menu(sc, categoryList, products, shoppingCart);
    }
}