package resten;

import discounts.Discounter;
import inventory.Category;
import inventory.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public abstract class Print {

    public static void startMenu() {
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

    public static void masterStartMenu() {
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

    public static void masterInventoryMenu() {
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

    public static void editChosenProduct(String stockOrAmount) {
        System.out.printf("""
                                
                Would you like to increase or decrease the %s?
                1. Increase
                2. Decrease
                e. Previous menu
                """, stockOrAmount);
    }

    public static void masterCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Main menu
                """);
    }

    public static void customerMenu() {
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

    public static void shopMenu() {
        System.out.println("""
                                
                Shop Menu
                =========
                1. Add to cart
                2. View cart
                3. Edit cart
                4. Checkout
                e. Leave shop
                """);
    }

    public static void adminMenu() {
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

    public static void adminProductMenu() {
        System.out.println("""
                            
                Product Menu
                ============
                1. Add product
                2. Remove product
                e. Main menu
                """);
    }

    public static void alternatives() {
        System.out.println("""
                1. Choose specific category
                2. All categories
                e. Previous menu
                """);
    }

    public static void adminCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                3. Delete category
                e. Main menu
                """);
    }

    public static void duplicateProductMessage() {
        System.out.println("This product already exists, please try again");
    }

    public static void edibleProductMenu(List<Product> products) {
        Ask.forProductToEdit();
        Print.allProductNamesWithNumber(products);
        Print.optionE();
    }


    public static void productMenu(List<Category> categoryList) {
        Ask.forCategoryToUse();
        Print.allCategories(categoryList);
        Print.optionE();
    }


    public static void removeCategoryMenu(List<Category> categoryList) {
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

    public static void removeProductMenu(List<Product> products) {
        System.out.println("""
                                
                Which product would you like to remove?
                """);
        Print.allProductNamesWithNumber(products);
        Print.optionE();
    }

    public static void addToCartMenu(List<Product> productList) {
        System.out.println("""
                                
                Add the product you want to buy in the cart
                """);
        System.out.println("\t" + productFieldNames());
        Print.allProductsWithNumber(productList);
        Print.optionE();
    }

    public static void addAvailableAmount(Product tempChosenProduct) {
        System.out.printf("""
                Only %d %s are available for purchase
                """, tempChosenProduct.getStock(), tempChosenProduct.getName());
    }

    public static void chosenProductUpdated() {
        System.out.println("The chosen product in cart is updated");
    }

    public static void newStock(Product chosenProduct) {
        System.out.println("The new stock for " + chosenProduct.getName() + " is: " + chosenProduct.getStock());
    }

    public static void leavingShop() {
        Ask.ifLeaving();
        System.out.println("1. Stay in shop");
        Print.shopOptionE();
    }

    public static void promoCodeMenu() {
        Ask.ifUsingPromoCode();
        Print.yesAndNo();
    }

    public static void limitedPromoCode() {
        System.out.println("""
                
                This code is limited to carts with a total value over $50
                """);
    }
    public static void tryNewPromoCode(String userInputCode) {
        Print.notValidPromoCode();
        Ask.toTryAgain();
        Print.yesAndNo();
    }

    private static void yesAndNo() {
        System.out.println("""
                1. Yes
                2. No
                """);
    }

    private static void notValidPromoCode() {
        System.out.println("""
                This is not a valid code""");
    }

    private static void shopOptionE() {
        System.out.println("e. Leave shop");
    }


    public static void newLine() {
        System.out.print("\n");
    }

    public static void noProductFound() {
        System.out.println("""
                There is no match based on your search string
                Please double check your spelling or choose another criteria""");
    }

    public static void allProductNamesWithNumber(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.print(number(i) + smallSpace() + productName(i, products));
        }
    }

    public static void allProductsWithNumber(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            Print.productsWithNumber(i, products);
        }
    }

    private static void productsWithNumber(int i, List<Product> products) {
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
        var nameSpace = LineUp.name(productName(i, products).length());
        return productName(i, products) + nameSpace + "| Out Of Stock";
    }

    private static String productName(int i, List<Product> products) {
        return products.get(i).getName();
    }

    private static String productsInStock(int index, List<Product> products) {
        return products.get(index).toString();
    }

    public static void productAddedToCart(Product tempChosenProduct, int amountInCart) {
        System.out.println(amountInCart + " " + tempChosenProduct.getName() + " are added to cart");
    }

    private static String bigSpace() {
        return ".  ";
    }

    private static String smallSpace() {
        return ". ";
    }

    public static void chooseOneOfTheAlternativesBelow() {
        System.out.println("Please choose one of the alternatives below:");
    }

    public static void availableAmountAlreadyInCart() {
        System.out.println("You already have the available amount of chosen product in cart");
    }

    public static void cart(HashMap<Product, Integer> shoppingCart) {
        shoppingCart.forEach((key, value) -> System.out.println(key.printInCart() + "x" + value));
    }

    public static void cartFieldNames() {
        System.out.println("\nName" + LineUp.name(4) +
                "| Apiece" + LineUp.price(6) +
                "| ProductID" + LineUp.productID(9) +
                "| Amount");
    }

    public static String productFieldNames() {
        return ("Name" + LineUp.name(4) +
                "| Apiece" + LineUp.price(6) +
                "| Category" + LineUp.category(8) +
                "| Brand" + LineUp.brand(5) +
                "| ProductID" + LineUp.productID(9) +
                "| Stock");
    }

    public static void cartIsEmpty() {
        System.out.println("""
                The cart is empty""");
    }

    public static void cartWithNames(HashMap<Product, Integer> shoppingCart) {
        Print.cartFieldNames();
        Print.cart(shoppingCart);
    }

    public static void cartWithNumbers(HashMap<Product, Integer> shoppingCart, List<String> keyList, List<Integer> valueList) {
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

    public static void productRemovedFromCart() {
        System.out.println("""
                The product is removed from cart
                """);
    }

    public static void goingBackToPreviousMenu() {
        System.out.println("""
                Going back to previous menu
                """);
    }

    public static void quitMessage() {
        System.out.println(LineUp.withTab(2) + "Welcome back");
    }

    //TODO: MAKE THE RECEIPT
    public static void receipt() {
        System.out.println("THIS IS THE RECEIPT");
    }

}