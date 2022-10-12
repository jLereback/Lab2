import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class Menu {

    static void printStartMenu() {
        System.out.println("""
                                
                Welcome to Disc Shop!
                =====================
                Please choose your role for today:
                1. Customer
                2. Admin
                3. Master
                e. Log out
                """);
    }

    static void printMasterMenu() {
        System.out.println("""
                            
                Disc Shop
                =========
                1. Inventory
                2. Categories
                3. Search product
                4. Shop
                e. Switch user
                """);
    }

    static void printMasterInventoryMenu() {
        System.out.println("""
                            
                Product Menu
                ============
                1. Print inventory
                2. Add new product
                3. Edit inventory
                4. Remove product
                e. Main menu
                """);
    }

    static void editChosenProduct(String choice, ArrayList<Product> products) {
        System.out.println("""
                
                Would you like to increase or decrease stock?
                1. Increase
                2. Decrease
                e. Previous menu
                """);
    }

    static void printMasterCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Main menu
                """);
    }

    static void printCustomerMenu() {
        System.out.println("""
                                
                Disc Shop
                =========
                1. Show products
                2. Show categories
                3. Search product
                4. Shop
                e. Switch user
                """);
    }

    static void printShopMenu() {
        System.out.println("""
                                
                Shop Menu
                =========
                1. Add to cart
                2. Show cart
                3. Edit cart
                4. Checkout
                e. Main Menu
                """);
    }

    static void printAdminMenu() {
        System.out.println("""
                            
                Disc Shop
                =========
                1. Product
                2. Categories
                3. products balance
                4. Search product
                e. Switch user
                """);
    }

    static void printAdminProductMenu() {
        System.out.println("""
                            
                Product Menu
                ============
                1. Add product
                2. Remove product
                e. Main menu
                """);
    }

    static void printChosenMenu() {
        System.out.println("""
                            
                Would you like to see products from all categories
                or products from a specific one?
                1. Choose specific category
                2. All categories
                e. Previous menu
                """);
    }

    static void printAdminCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Main menu
                """);
    }

    static void printEdibleProductMenu(Scanner sc, ArrayList<Product> products) {
        System.out.println("""
                                
                What product would you like to edit?""");
        printAllProductNames(products);
        printOptionE();
    }

    static void printProductMenu(ArrayList<Category> categoryList) {
        System.out.println("""
                                
                Which category would you like to use?""");
        printAllCategories(categoryList);
        printOptionE();
    }

    static void printRemoveCategoryMenu(ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("""
                                
                Which category would you like to remove?
                """);
        printAllCategories(categoryList);
        printOptionE();
    }

    private static void printOptionE() {
        System.out.println("e. Previous menu");
    }

    private static void printAllCategories(ArrayList<Category> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
    }

    static void printRemoveProductMenu(ArrayList<Product> products) {
        System.out.println("""
                                
                Which product would you like to remove?
                """);
        printAllProductNames(products);
        printOptionE();
    }

    static void printAddToCartMenu(ArrayList<Product> products) {
        System.out.println("""
                
                Add all products you want to buy in the cart
                """);

        printAllProductNames(products);
        printNewLine();
        printOptionP();
        printOptionV();
        printOptionE();
    }

    private static void printNewLine() {
        System.out.print("\n");
    }
    private static void printOptionV() {
        System.out.println("v. View cart");
    }

    private static void printOptionP() {
        System.out.println("p. Proceed to checkout");
    }

    static void printAllProductNames(ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getName());
        }
    }

    static void quit() {
        System.out.println("Welcome back");
    }
}