import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Master extends Super {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) throws Exception {
        String choice;
        do {
            Print.masterStartMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products, shoppingCart);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) throws Exception {
        switch (choice) {
            case "1" -> inventory(sc, categoryList, products);
            case "2" -> category(sc, categoryList, products);
            case "3" -> search(sc, categoryList, products);
            case "4" -> showShopMenu(sc, categoryList, products, shoppingCart);
            case "e" -> Print.quitMessage();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void inventory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Print.masterInventoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchInventory(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }


    private static void switchInventory(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> printInventory(sc, categoryList, products);
            case "2" -> addProduct(sc, categoryList, products);
            case "3" -> editStock(sc, categoryList, products);
            case "4" -> removeProduct(sc, categoryList, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void printInventory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0) {
            System.out.println("""
                    Please add a product before print it""");
        } else {
            Print.chosenMenu();
            String choice = sc.nextLine().toLowerCase();
            switchProductMenu(choice, sc, categoryList, products);
        }
    }

    private static void showShopMenu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) throws Exception {
        String choice;
        do {
            Print.shopMenu();
            choice = sc.nextLine().toLowerCase();
            switchShopMenu(choice, sc, categoryList, products, shoppingCart);
        } while (!choice.equals("e"));
    }
    private static void switchShopMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) throws Exception {
        switch (choice) {
            case "1" -> addToCart(sc, categoryList, products, shoppingCart);
            case "2" -> viewCart(sc, categoryList, products, shoppingCart);
            case "3" -> editCart(sc, categoryList, products, shoppingCart);
            case "4" -> toCheckout(sc, categoryList, products, shoppingCart);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    public static void category(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Print.masterCategoryMenu();
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