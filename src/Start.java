import misc.Print;
import inventory.Category;
import inventory.Product;
import users.Admin;
import users.Customer;
import users.Master;

import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

public class Start {

    public static void menu(Scanner sc, List<Category> categoryList, List<Product> products,
                            HashMap<Product, Integer> shoppingCart) {
        String choice;
        do {
            Print.startMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products, shoppingCart);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, List<Category> categoryList, List<Product> products,
                                   HashMap<Product, Integer> shoppingCart) {
        switch (choice) {
            case "1" -> Customer.menu(sc, categoryList, products, shoppingCart);
            case "2" -> toAdminMenu(sc, categoryList, products);
            case "3" -> toMasterMenu(sc, categoryList, products, shoppingCart);
            case "e" -> Print.quitMessage();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void toMasterMenu(Scanner sc, List<Category> categoryList, List<Product> products,
                                     HashMap<Product, Integer> shoppingCart) {
        System.out.println("Who is Julia's superhero?");
        if (sc.nextLine().equals("Martin"))
            Master.menu(sc, categoryList, products, shoppingCart);
        else
            System.out.println("You are not allowed to be in the Master menu");
    }

    private static void toAdminMenu(Scanner sc, List<Category> categoryList, List<Product> products) {
        System.out.println("Please write the password");
        if (sc.nextLine().equals("the password"))
            Admin.menu(sc, categoryList, products);
        else
            System.out.println("You are no admin\tCheckout the customer menu instead");
    }
}