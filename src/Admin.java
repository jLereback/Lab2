import java.util.List;
import java.util.Scanner;

public class Admin extends Super {
    static void menu(Scanner sc, List<Category> categoryList, List<Product> products) {
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
            case "2" -> category(sc, categoryList, products);
            case "3" -> productsBalance(sc, categoryList, products);
            case "4" -> search(sc, categoryList, products);
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
            case "1" -> addProduct(sc, categoryList, products);
            case "2" -> removeProduct(sc, categoryList, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    public static void category(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
        do {
            Print.adminCategoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchCategories(categoryList, products, choice, sc);
        } while (!choice.equals("e"));
    }

    private static void switchCategories(List<Category> categoryList, List<Product> products, String choice, Scanner sc) {
        switch (choice) {
            case "1" -> addNewCategory(categoryList, sc);
            case "2" -> printCategories(categoryList);
            case "3" -> deleteCategory(sc, categoryList, products);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
}