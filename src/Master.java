import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Master extends Super {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printMasterMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> product(sc, categoryList, products);
            case "2" -> category(sc, categoryList, products);
            case "3" -> productsBalance(sc, categoryList, products);
            case "4" -> search();
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void product(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printMasterProductMenu();
            choice = sc.nextLine().toLowerCase();
            switchProduct(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchProduct(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> showProductMenu(sc, categoryList, products);
            case "2" -> addProduct(sc, categoryList, products);
            case "3" -> removeProduct(sc, categoryList, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void showProductMenu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0) {
            System.out.println("Please add a product before you print it");
        } else {
            String choice;
            Menu.printChosenMenu();
            choice = sc.nextLine().toLowerCase();
            switchProductMenu(choice, sc, categoryList, products);
        }
    }


    public static void category(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;

        do {
            Menu.printMasterCategoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchCategories(categoryList, choice, sc, products);
        } while (!choice.equals("e"));
    }

    private static void switchCategories(ArrayList<Category> categoryList, String choice, Scanner sc, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> addNewCategory(categoryList, sc);
            case "2" -> printCategories(categoryList);
            case "3" -> deleteCategory(sc, categoryList, products);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
}