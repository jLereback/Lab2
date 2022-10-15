package users;

import resten.Print;
import inventory.Product;
import inventory.Category;

import java.util.List;
import java.util.Scanner;

public class Customer extends Super {

    public static void menu(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
        do {
            Print.customerMenu();
            choice = sc.nextLine().toLowerCase();
            switchCustomerMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchCustomerMenu(String choice, Scanner sc, List<Category> categoryList, List<Product> products) {
        switch (choice) {
            case "1" -> showProducts(sc, categoryList, products);
/*
            case "2" -> printCategoryList(sc, categoryList, products);
*/
            case "3" -> printProductsInCategory(choice, categoryList, products);
            case "4" -> search(sc, categoryList, products);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void showProducts(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
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

/*    private static void printCategoryList(Scanner sc, List<Category> categoryList, List<Product> products) {
        if (categoryList.size() == 0) {
            System.out.println("""
                    There are no categories in this shop at the moment
                    Please come back later""");
            resten.Start.menu(sc, categoryList, products, cart);
        } else
            categoryList.forEach(System.out::println);
    }*/
}
