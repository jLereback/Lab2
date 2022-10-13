import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        var products = new ArrayList<Product>();
        var categoryList = new ArrayList<Category>();
        categoryList.add(new Category("Putt"));
        categoryList.add(new Category("Midrange"));
        categoryList.add(new Category("Driver"));
        products = Json.readFromFile();
        Json.saveToFile(products);

        var shoppingCart = new HashMap<Product, Integer>();



        Start.menu(sc, categoryList, products, shoppingCart);
    }
}