import inventory.Category;
import inventory.Product;
import json.Json;
import resten.Start;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        List<Category> categoryList = Json.importCategoryListFromFile();
        List<Product> products = Json.importProductsFromFile();
        var shoppingCart = new HashMap<Product, Integer>();
        Start.menu(sc, categoryList, products, shoppingCart);
    }
}