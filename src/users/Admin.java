package users;

import java.util.List;
import java.util.Scanner;

import resten.Print;
import inventory.Category;
import inventory.Product;

public class Admin extends CommonStuff {
    public static void menu(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
        do {
            Print.adminMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, List<Category> categoryList, List<Product> products) {
        switch (choice) {
            case "1" -> product(sc, categoryList, products);
            case "2" -> category(sc, categoryList);
            case "3" -> CommonStuff.productsBalance(products);
            case "4" -> CommonStuff.search(sc, products);
            case "e" -> Print.quitMessage();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void product(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
        do {
            Print.adminProductMenu();
            choice = sc.nextLine().toLowerCase();
            switchProduct(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchProduct(String choice, Scanner sc, List<Category> categoryList, List<Product> products) {
        switch (choice) {
            case "1" -> CommonStuff.addProduct(sc, categoryList, products);
            case "2" -> CommonStuff.removeProduct(sc, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    public static void category(Scanner sc, List<Category> categoryList) {
        String choice;
        do {
            Print.adminCategoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchCategories(categoryList, choice, sc);
        } while (!choice.equals("e"));
    }

    private static void switchCategories(List<Category> categoryList, String choice, Scanner sc) {
        switch (choice) {
            case "1" -> CommonStuff.addNewCategory(categoryList, sc);
            case "2" -> CommonStuff.printCategories(categoryList);
            case "3" -> CommonStuff.deleteCategory(sc, categoryList);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
}