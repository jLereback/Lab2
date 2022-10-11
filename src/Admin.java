import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Admin extends Super {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printAdminMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> product(sc, categoryList, products);
            case "2" -> category(sc, categoryList, products);
            case "3" -> productsBalance(sc, categoryList, products);
            case "4" -> search(sc, categoryList, products);
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void product(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printAdminProductMenu();
            choice = sc.nextLine().toLowerCase();
            switchProduct(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchProduct(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> addProduct(sc, categoryList, products);
            case "2" -> removeProduct(sc, categoryList, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    public static void category(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printAdminCategoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchCategories(categoryList, products, choice, sc);
        } while (!choice.equals("e"));
    }

    private static void switchCategories(ArrayList<Category> categoryList, ArrayList<Product> products, String choice, Scanner sc) {
        switch (choice) {
            case "1" -> addNewCategory(categoryList, sc);
            case "2" -> printCategories(categoryList);
            case "3" -> deleteCategory(sc, categoryList, products);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
}