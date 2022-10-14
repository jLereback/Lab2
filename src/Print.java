import java.util.*;
import java.util.List;

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

    static void editChosenProduct(String choice, List<Product> products, String stockOrAmount) {
        System.out.printf("""
                                
                Would you like to increase or decrease the %s?
                1. Increase
                2. Decrease
                e. Previous menu
                """, stockOrAmount);
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

    static void alternatives() {
        System.out.println("""
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

    static void edibleProductMenu(Scanner sc, List<Product> products) {
        Ask.forProductToEdit();
        Print.allProductNamesWithNumber(products);
        Print.optionE();
    }


    static void productMenu(List<Category> categoryList) {
        Ask.forCategoryToUse();
        Print.allCategories(categoryList);
        Print.optionE();
    }


    static void removeCategoryMenu(List<Category> categoryList, List<Product> products) {
        Ask.forCategoryToRemove();
        Print.allCategories(categoryList);
        Print.optionE();
    }


    private static void optionE() {
        System.out.println("e. Previous menu");
    }

    private static void allCategories(List<Category> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(number(i) + ". " + categoryList.get(i).toString());
        }
    }

    static void removeProductMenu(List<Product> products) {
        System.out.println("""
                                
                Which product would you like to remove?
                """);
        Print.allProductNamesWithNumber(products);
        Print.optionE();
    }

    static void addToCartMenu(List<Product> productList) {
        System.out.println("""
                                
                Add the product you want to buy in the cart
                """);
        System.out.println("\t" + Super.printProductFieldNames());
        Print.allProductsWithNumber(productList);
        Print.optionE();
    }

    static void addAvailableAmount(Product tempChosenProduct) {
        System.out.printf("""
                Only %d products are available for purchase
                Those are added to the cart
                """, tempChosenProduct.getStock());
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

    static void allProductNamesWithNumber(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.print(number(i) + smallSpace() + productNames(i, products));
        }
    }
    static void allProductsWithNumber(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            Print.productsWithNumber(products, i);
        }
    }

    private static void productsWithNumber(List<Product> products, int i) {
        if (products.get(i).getStock() <= 0)
            Print.productsInStock(i, productOutOfStock(i, products), productOutOfStock(i, products));
        else {
            Print.productsInStock(i, productsInStock(i, products), productsInStock(i, products));
        }
    }

    private static void productsInStock(int i, String optionSmallSpace, String optionBigSpace) {
        if (i > 9) {
            System.out.println(number(i) + smallSpace() + optionSmallSpace);
        } else {
            System.out.println(number(i) + bigSpace() + optionBigSpace);
        }
    }

    private static String productOutOfStock(int i, List<Product> products) {
        return productNames(i, products) + " | Out Of Stock";
    }

    private static String productNames(int i, List<Product> products) {
        return products.get(i).getName();
    }

    private static String productsInStock(int index, List<Product> products) {
        return products.get(index).toString();
    }

    private static String bigSpace() {
        return ".  ";
    }
    private static String smallSpace() {
        return ". ";
    }

    static void chooseOneOfTheAlternativesBelow() {
        System.out.println("Please choose one of the alternatives below:");
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
            Print.cart(keyList, valueList, i);
        }
    }

    private static void cart(List<String> keyList, List<Integer> valueList, int i) {
        if (i > 9)
            System.out.println(number(i) + smallSpace() + cartNames(keyList, i) + "x" + amountInCart(valueList, i));
        else
            System.out.println(number(i) + bigSpace() + cartNames(keyList, i) + "x" + amountInCart(valueList, i));
    }

    private static Integer amountInCart(List<Integer> valueList, int i) {
        return valueList.get(i);
    }

    private static String cartNames(List<String> keyList, int i) {
        return keyList.get(i);
    }

    private static int number(int i) {
        return i + 1;
    }

    static void productRemovedFromCart() {
        System.out.println("""
                The product is removed from your cart
                """);
    }

    static void goingBackToPreviousMenu() {
        System.out.println("""
                Going back to previous menu
                """);
    }

    static void quitMessage() {
        System.out.println("Welcome back");
    }
}