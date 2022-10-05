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
    static void printProductMenu(ArrayList<Category> categoryList) {
        System.out.println("Which category would you like to use?");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
        System.out.println("e. Back to Product menu");
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
                1. Show products
                2. Add product
                3. Remove product
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

    static void printCustomerMenu() {
        System.out.println("""
                
                Disc Shop
                =========
                1. Show products
                2. Show categories
                3. Search product
                e. Switch user
                """);
    }



    static void quit () {
        System.out.println("Welcome back");
    }
}