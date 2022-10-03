import java.util.ArrayList;
import java.util.Scanner;

public class Start {

    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        String choice;
        do {
            printStartMenu(sc, categoryList, inventory);
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchMenu(choice, sc, categoryList, inventory);
        } while (!choice.equals("e"));
    }

    static void printStartMenu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
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

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        switch (choice) {
            case "1" -> Customer.menu(sc, categoryList, inventory);
            case "2" -> Admin.menu(sc, categoryList, inventory);
            case "3" -> Master.menu(sc, categoryList, inventory);
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static class Master {
        static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
            System.out.println("");
        }
    }
}