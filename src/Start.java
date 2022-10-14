import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

public class Start {

    static void menu(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart) throws Exception {
        String choice;
        do {
            Print.startMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products, shoppingCart);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart) throws Exception {
        switch (choice) {
            case "1" -> Customer.menu(sc, categoryList, products);
            case "2" -> Admin.menu(sc, categoryList, products);
            case "3" -> Master.menu(sc, categoryList, products, shoppingCart);
            case "e" -> Print.quitMessage();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }


}


