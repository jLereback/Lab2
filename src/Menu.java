import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

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

    static void printMasterMenu() {
        System.out.println("""
                            
                Disc Shop
                =========
                1. Product
                2. Categories
                3. Inventory balance
                4. Search product
                e. Switch user
                """);
    }
    static void printMasterProductMenu() {
        System.out.println("""
                            
                Product Menu
                ============
                1. Show products
                2. Add product
                3. Remove product
                e. Back to Main menu
                """);
    }
    static void printMasterCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Back to Main menu
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
                2. Edit cart
                3. Show cart
                4. Checkout
                e. Switch user
                """);
    }
    static void printProductMenu(ArrayList<Category> categoryList) {
        System.out.println("""
                
                Which category would you like to use?
                """);
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
        System.out.println("""
                e. Main menu""");
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
                e. Back to Main menu
                """);
    }
    static void printChosenMenu() {
        System.out.println("""
            
            Would you like to see products from all categories
            or products from a specific one?
            1. Choose specific category
            2. All categories
            e. Back to Main menu
            """);
    }
    static void printAdminCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Back to Main menu
                """);
    }
    static void printRemoveCategoryMenu(ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("""
                
                Which category would you like to remove?
                """);
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
        System.out.println("""
                e. Main menu""");
    }
    static void printRemoveProductMenu(ArrayList<Product> products) {
        System.out.println("""
                
                Which product would you like to remove?
                """);
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).toString());
        }
        System.out.println("""
                e. Main menu""");
    }
    static void quit () {
        System.out.println("Welcome back");
    }
}