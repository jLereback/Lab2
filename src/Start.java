import java.util.ArrayList;
import java.util.Scanner;

public class Start {

    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printStartMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> Customer.menu(sc, categoryList, products);
            case "2" -> Admin.menu(sc, categoryList, products);
            case "3" -> Master.menu(sc, categoryList, products);
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }


}
