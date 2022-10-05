import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Start {

    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        String choice;
        do {
            printStartMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products, categoryProductHashMap);
        } while (!choice.equals("e"));
    }

    static void printStartMenu() {
        System.out.println("""
                Welcome to Disc Shop!
                =====================
                Please choose your role for today:
                1. Customer
                2. Admin
                3. Master
                e. Quit
                """);
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        switch (choice) {
            case "1" -> Customer.menu(sc, categoryList, products, categoryProductHashMap);
            case "2" -> Admin.menu(sc, categoryList, products, categoryProductHashMap);
            case "3" -> Master.menu(sc, categoryList, products, categoryProductHashMap);
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }


}
