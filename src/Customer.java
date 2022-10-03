import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        String choice;
        do {
            printMenu();
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchMenu(choice, sc, categoryList, inventory);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        switch (choice) {
            case "1" -> showProduct(inventory);
            case "2" -> printCategories(categoryList);
            case "4" -> search();
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void printMenu() {
        System.out.println("""
                            
                Disc Shop
                =========
                1. Show products
                2. Show categories
                4. Search product
                e. Quit
                """);
    }

    private static void showProduct(ArrayList<Product> inventory) {
        if (inventory.size() == 0) {
            System.out.println("Please add a product before you print it");
        } else {
            for (Product product : inventory) {
                System.out.println(product.printProducts());
            }
        }
    }

    private static void printCategories(ArrayList<Category> list) {
        if (list.size() == 0) {
            System.out.println("Please create a category before you print it");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void search() {
    }


}
