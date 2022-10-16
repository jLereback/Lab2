package users;

import misc.Print;
import inventory.Product;
import inventory.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static users.CommonShop.showShopMenu;

public class Customer extends Common {

    public static void menu(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        String choice;
        do {
            Print.customerMenu();
            choice = sc.nextLine().toLowerCase();
            switchCustomerMenu(choice, sc, categoryList, products, shoppingCart);
        } while (!choice.equals("e"));
    }

    private static void switchCustomerMenu
            (String choice, Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        switch (choice) {
            case "1" -> showProducts(sc, categoryList, products);
            case "2" -> printCategories(categoryList);
            case "3" -> search(sc, products);
            case "4" -> showShopMenu(sc, categoryList, products, shoppingCart);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void showProducts(Scanner sc, List<Category> categoryList, List<Product> products) {
        printProductInfo(sc, categoryList, products);
    }

    private static void printProductInfo(Scanner sc, List<Category> categoryList, List<Product> products) {
        if (products.size() == 0) {
            System.out.println("""
                    There are no products available in this shop at the moment
                    Please come back later""");
        } else {
            Print.alternatives();
            String choice = sc.nextLine().toLowerCase();
            switchProductMenu(choice, sc, categoryList, products);
        }
    }
}
