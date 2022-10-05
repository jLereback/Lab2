import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Super {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printCustomerMenu();
            choice = sc.nextLine().toLowerCase();
            switchCustomerMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchCustomerMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> showProducts(sc, categoryList, products);
            case "2" -> printCategoryList(sc, categoryList, products);
            case "3" -> printProductsInCategory(choice, sc, categoryList, products);
            case "4" -> search();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void showProducts(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        printProductInfo(sc, categoryList, products);
        choice = sc.nextLine().toLowerCase();
        switchCustomerProductMenu(choice, sc, categoryList, products);

    }

    private static void switchCustomerProductMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> chooseCategory(sc, categoryList, products);
            case "2" -> printAllProducts(products);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }


    private static void printProductInfo(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0) {
            System.out.println("""
                    There are no products available in this shop at the moment
                    Please come back later""");
            Start.menu(sc, categoryList, products);
        }
        else {
            System.out.println("Would you like to see all products or from a specific category?");
        }
    }

    private static void printCategoryList(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (categoryList.size() == 0) {
            System.out.println("""
                    There are no categories in this shop at the moment
                    Please come back later""");
            Start.menu(sc, categoryList, products);
        }
        else
            categoryList.forEach(System.out::println);
    }
}
