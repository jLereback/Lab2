import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class Print {

    static void startMenu() {
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

    static void masterStartMenu() {
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

    static void masterInventoryMenu() {
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

    static void masterCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Main menu
                """);
    }

    static void customerMenu() {
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

    static void shopMenu() {
        System.out.println("""
                                
                Shop Menu
                =========
                1. Add to cart
                2. View cart
                3. Edit cart
                4. Checkout
                e. Main Menu
                """);
    }

    static void adminMenu() {
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

    static void adminProductMenu() {
        System.out.println("""
                            
                Product Menu
                ============
                1. Add product
                2. Remove product
                e. Main menu
                """);
    }

    static void chosenMenu() {
        System.out.println("""
                            
                Would you like to see products from all categories
                or products from a specific one?
                1. Choose specific category
                2. All categories
                e. Previous menu
                """);
    }

    static void adminCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Main menu
                """);
    }

    static void duplicateProductMessage() {
        System.out.println("This product already exists, please try again");
    }

    static void edibleProductMenu(Scanner sc, ArrayList<Product> products) {
        Ask.forProductToEdit();
        Print.allProductNamesWithNumber(products);
        Print.optionE();
    }


    static void productMenu(ArrayList<Category> categoryList) {
        Ask.forCategoryToUse();
        Print.allCategories(categoryList);
        Print.optionE();
    }


    static void removeCategoryMenu(ArrayList<Category> categoryList, ArrayList<Product> products) {
        Ask.forCategoryToRemove();
        Print.allCategories(categoryList);
        Print.optionE();
    }


    private static void optionE() {
        System.out.println("e. Previous menu");
    }

    private static void allCategories(ArrayList<Category> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
    }

    static void removeProductMenu(ArrayList<Product> products) {
        System.out.println("""
                                
                Which product would you like to remove?
                """);
        Print.allProductNamesWithNumber(products);
        Print.optionE();
    }

    static void addToCartMenu(ArrayList<Product> products) {
        System.out.println("""
                                
                Add the product you want to buy in the cart
                """);
        System.out.println("\t" + Super.printProductFieldNames());
        Print.allProductsWithNumber(products);
        newLine();
        Print.optionP();
        Print.optionV();
        Print.optionE();
    }

    private static void newLine() {
        System.out.print("\n");
    }

    private static void optionV() {
        System.out.println("v. View cart");
    }

    private static void optionP() {
        System.out.println("p. Proceed to checkout");
    }

    static void noProductFound() {
        System.out.println("""
                There is no match based on your search string
                Please double check your spelling or choose another criteria""");
    }

    static void allProductNamesWithNumber(ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(products.get(i).getName());
        }
    }


    static void allProductsWithNumber(ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            if (i > 9) {
                System.out.print((i + 1) + ". ");
                System.out.println(products.get(i).toString());
            } else {
                System.out.print((i + 1) + ".  ");
                System.out.println(products.get(i).toString());
            }
        }
    }

    static void cart(HashMap<Product, Integer> shoppingCart) {
        shoppingCart.forEach((key, value) -> System.out.println(key.printInCart() + "x" + value));
    }

    static void cartFieldNames() {
        System.out.println(("Name" + LineUp.lineUpName(4) +
                "| Price" + LineUp.lineUpPrice(5) +
                "| ProductID" + LineUp.lineUpProductID(9) +
                "| Amount"));
    }

    static void cartWithNumbers(HashMap<Product, Integer> shoppingCart, List<String> keyList, List<Integer> valueList) {
        for (int i = 0; i < shoppingCart.size(); i++) {
            if (i > 9)
                System.out.println((i + 1) + ". " + keyList.get(i) + "x" + valueList.get(i));
            else
                System.out.println((i + 1) + ".  " + keyList.get(i) + "x" + valueList.get(i));
        }
    }

    static void quitMessage() {
        System.out.println("Welcome back");
    }
}