package shop;

import discounts.Discounter;
import resten.Ask;
import resten.LineUp;
import resten.Print;
import users.Super;
import inventory.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.valueOf;


public abstract class Shop extends Super {

    public static void addToCart
            (Scanner sc, List<Product> products, HashMap<Product, Integer> shoppingCart,
             List<Product> visibleCopyOfProducts) {
        String choice;
        Print.addToCartMenu(visibleCopyOfProducts);
        choice = sc.nextLine().toLowerCase();
        try {
            if (choice.equals("e"))
                Print.goingBackToPreviousMenu();
            else if ((Integer.parseInt(choice) <= products.size())) {
                checkChosenProduct(sc, products, shoppingCart, visibleCopyOfProducts, choice);
            } else
                Print.chooseOneOfTheAlternativesBelow();
        } catch (NumberFormatException e) {
            Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void checkChosenProduct
            (Scanner sc, List<Product> products, HashMap<Product, Integer> shoppingCart,
             List<Product> visibleCopyOfProducts, String choice) {
        if (getChosenProduct(visibleCopyOfProducts, choice).getStock() <= 0)
            System.out.println("This product is out of stock");
        else
            addProductToCart(getChosenProduct(visibleCopyOfProducts, choice),
                    sc, shoppingCart, getChosenProduct(products, choice));
    }


    private static Product getChosenProduct(List<Product> products, String choice) {
        return products.get(Integer.parseInt(choice) - 1);
    }

    private static void addProductToCart
            (Product tempChosenProduct, Scanner sc, HashMap<Product, Integer> shoppingCart, Product chosenProduct) {
        Ask.howManyToAdd(tempChosenProduct);
        int amountInCart = getInput(sc);

        if (tempChosenProduct.getStock() < amountInCart)
            addAvailableAmountToCart(tempChosenProduct, shoppingCart, chosenProduct);
        else
            addAmountToCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
    }

    private static void addAvailableAmountToCart
            (Product tempChosenProduct, HashMap<Product, Integer> shoppingCart, Product chosenProduct) {
        int amountInCart;
        Print.addAvailableAmount(tempChosenProduct);
        amountInCart = tempChosenProduct.getStock();
        addAmountToCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
    }


    private static void addAmountToCart
            (Product tempChosenProduct, HashMap<Product, Integer> shoppingCart,
             Product chosenProduct, int amountInCart) {
        if (shoppingCart.containsKey(chosenProduct))
            addAmountToProductInCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
        else
            addNewProductInCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
    }

    private static void addNewProductInCart
            (Product tempChosenProduct, HashMap<Product, Integer> shoppingCart,
             Product chosenProduct, int amountInCart) {
        shoppingCart.put(chosenProduct, amountInCart);
        Print.productAddedToCart(tempChosenProduct, amountInCart);
        updateProductStock(tempChosenProduct, amountInCart);
    }


    private static void addAmountToProductInCart
            (Product tempChosenProduct, HashMap<Product, Integer> shoppingCart,
             Product chosenProduct, int amountInCart) {
        increaseChosenAmount(chosenProduct, shoppingCart.get(chosenProduct), shoppingCart, amountInCart);
        updateProductStock(tempChosenProduct, amountInCart);
    }

    private static void updateProductStock(Product tempChosenProduct, int amountInCart) {
        tempChosenProduct.editStock(tempChosenProduct.getStock() - amountInCart);
    }

    private static int getInput(Scanner sc) {
        return Integer.parseInt(sc.nextLine());
    }

    public static void viewCart(HashMap<Product, Integer> shoppingCart) {
        if (shoppingCart.isEmpty())
            Print.cartIsEmpty();
        else
            Print.cartWithNames(shoppingCart);
    }


    public static void editCart
            (Scanner sc, List<Product> products, HashMap<Product, Integer> shoppingCart,
             List<Product> visibleCopyOfProducts) {
        if (shoppingCart.size() == 0)
            Print.cartIsEmpty();
        else
            editProductInCart(sc, shoppingCart, products, visibleCopyOfProducts);
    }


    private static void editProductInCart
            (Scanner sc, HashMap<Product, Integer> shoppingCart, List<Product> products,
             List<Product> visibleCopyOfProducts) {
        Ask.forProductToEdit();

        Print.cartWithNumbers(shoppingCart, getKeyList(shoppingCart), getValueList(shoppingCart));
        chooseProductInCart(sc.nextLine(), sc, products, shoppingCart,
                getListOfCart(shoppingCart), visibleCopyOfProducts);
    }

    private static List<Product> getListOfCart(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.keySet().stream().toList();
    }

    private static List<String> getKeyList(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.keySet().stream().map(Product::printInCart).toList();
    }

    private static List<Integer> getValueList(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.values().stream().toList();
    }

    private static void chooseProductInCart
            (String choice, Scanner sc, List<Product> products, HashMap<Product, Integer> shoppingCart,
             List<Product> listOfCart, List<Product> visibleCopyOfProducts) {
        if (choice.equals("e"))
            Print.goingBackToPreviousMenu();
        else if (Integer.parseInt(choice) <= products.size()) {
            Product chosenProduct = listOfCart.get(Integer.parseInt(choice) - 1);
            Print.editChosenProduct("amount");
            switchEditProductInCart(sc.nextLine().toLowerCase(), chosenProduct,
                    getTempChosenProduct(visibleCopyOfProducts, chosenProduct),
                    shoppingCart.get(chosenProduct), sc, shoppingCart);
        }
    }

    private static Product getTempChosenProduct(List<Product> visibleCopyOfProducts, Product chosenProduct) {
        for (Product visibleCopyOfProduct : visibleCopyOfProducts) {
            if (visibleCopyOfProduct.getName().equals(chosenProduct.getName())) {
                return visibleCopyOfProduct;
            }
        }
        return null;
    }

    private static void switchEditProductInCart
            (String choice, Product chosenProduct, Product tempChosenProduct,
             int currentAmount, Scanner sc, HashMap<Product, Integer> shoppingCart) {
        switch (choice) {
            case "1" -> increaseItemInCart
                    (sc, choice, chosenProduct, tempChosenProduct, currentAmount, shoppingCart);
            case "2" -> decreaseAmountInCart
                    (sc, choice, chosenProduct, tempChosenProduct, currentAmount, shoppingCart);
            case "e" -> Print.goingBackToPreviousMenu();
            default -> Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void increaseItemInCart
            (Scanner sc, String choice, Product chosenProduct, Product tempChosenProduct,
             int currentAmount, HashMap<Product, Integer> shoppingCart) {
        if (tempChosenProduct.getStock() == 0)
            Print.availableAmountAlreadyInCart();
        else {
            increaseSpecifiedProduct(sc, choice, chosenProduct, tempChosenProduct, currentAmount, shoppingCart);
        }
    }

    private static void increaseSpecifiedProduct
            (Scanner sc, String choice, Product chosenProduct, Product tempChosenProduct,
             int currentAmount, HashMap<Product, Integer> shoppingCart) {
        Ask.forNewStockOrAmount(choice, "amount");
        int increasingAmount = getInput(sc);
        if (tempChosenProduct.getStock() < increasingAmount) {
            increaseAvailableAmount(chosenProduct, tempChosenProduct, currentAmount, shoppingCart);
        } else
            increaseChosenAmount(chosenProduct, currentAmount, shoppingCart, increasingAmount);
    }

    private static void increaseChosenAmount
            (Product chosenProduct, int currentAmount, HashMap<Product, Integer> shoppingCart, int increasingAmount) {
        int newAmount = currentAmount + increasingAmount;
        shoppingCart.replace(chosenProduct, currentAmount, newAmount);
    }

    private static void increaseAvailableAmount
            (Product chosenProduct, Product tempChosenProduct, int currentAmount,
             HashMap<Product, Integer> shoppingCart) {
        int availableAmount = tempChosenProduct.getStock();
        int newAmount = currentAmount + availableAmount;
        int emptyStock = 0;
        tempChosenProduct.editStock(emptyStock);
        shoppingCart.replace(chosenProduct, currentAmount, newAmount);
    }

    private static void decreaseAmountInCart
            (Scanner sc, String choice, Product chosenProduct, Product tempChosenProduct,
             int currentAmount, HashMap<Product, Integer> shoppingCart) {
        Ask.forNewStockOrAmount(choice, "amount");
        int decreasingAmount = getInput(sc);
        int newAmount = currentAmount - decreasingAmount;

        if (shoppingCart.get(chosenProduct) - decreasingAmount <= 0) {
            removeProductFromCart(chosenProduct, tempChosenProduct, shoppingCart);
        } else {
            updateProductInCart
                    (chosenProduct, tempChosenProduct, currentAmount, shoppingCart, decreasingAmount, newAmount);
        }
    }

    private static void updateProductInCart
            (Product chosenProduct, Product tempChosenProduct, int currentAmount,
             HashMap<Product, Integer> shoppingCart, int decreasingAmount, int newAmount) {
        shoppingCart.replace(chosenProduct, currentAmount, newAmount);
        tempChosenProduct.editStock(tempChosenProduct.getStock() + decreasingAmount);
        Print.chosenProductUpdated();
    }


    private static void removeProductFromCart
            (Product chosenProduct, Product tempChosenProduct, HashMap<Product, Integer> shoppingCart) {
        tempChosenProduct.editStock(tempChosenProduct.getStock() + shoppingCart.get(chosenProduct));
        shoppingCart.remove(chosenProduct);
        Print.productRemovedFromCart();
    }

    public static void checkOutTotalPrice(BigDecimal ordinaryPrice) {
        productPriceInCart(ordinaryPrice);
        printShippingCostInCart();
        Discounter.checkShippingCost(ordinaryPrice);
        ordinaryPriceInCart(ordinaryPrice);
    }

    public static void printShippingCostInCart() {
        System.out.println("Shipping |" + LineUp.withTab(4) + "|\t$7");
    }

    public static void checkOut_Step1(Scanner sc, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        List<BigDecimal> ordinaryPriceList = new ArrayList<>();
        putTotalPriceToList(ordinaryPriceList, shoppingCart);

        BigDecimal ordinaryTotalPrice = getOrdinaryTotalPrice(ordinaryPriceList);


        checkOutTotalPrice(ordinaryTotalPrice);


        Print.promoCodeMenu();


        BigDecimal discountedPrice = Discounter.applyDiscount(ordinaryTotalPrice, getUserInputOfPromoCode(sc));

        loadCheckingDiscounts();

        printCheckOutTotalPrice(discountedPrice, ordinaryTotalPrice, getTotalNumOfProductsInCart(shoppingCart));


        products = visibleCopyOfProducts;
//        Json.exportProductsToFile(products);

        Print.newLine();
        Print.newLine();
        Print.newLine();
        Print.newLine();
        Print.newLine();
        System.out.println("͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟");
        receipt(shoppingCart, discountedPrice, ordinaryTotalPrice);
        System.exit(0);
    }

    private static void loadCheckingDiscounts() {
        System.out.print("Checking discounts");
        try {
            for (int j = 0; j < 5; j++) {
                System.out.print(".");
                TimeUnit.SECONDS.sleep(1);
            }
            Print.newLine();
            Print.newLine();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static BigDecimal getOrdinaryTotalPrice(List<BigDecimal> ordinaryPriceList) {
        return ordinaryPriceList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static String getUserInputOfPromoCode(Scanner sc) {
        int choice = Integer.parseInt(sc.nextLine());
        if (choice == 1) {
            System.out.println("\nFill in the code");
            return sc.nextLine();
        } else
            return "null";
    }

    private static void putTotalPriceToList(List<BigDecimal> ordinaryPriceList, HashMap<Product, Integer> shoppingCart) {
        for (int i = 0; i < getProductPriceList(shoppingCart).size(); i++) {
            ordinaryPriceList.add(getTotalPrice(shoppingCart, i));
        }
    }

    private static BigDecimal getTotalPrice(HashMap<Product, Integer> shoppingCart, int i) {
        return getProductPriceList(shoppingCart).get(i)
                .multiply(getAmountOfEachProductInCart(shoppingCart).get(i));
    }

    public static void receipt(HashMap<Product, Integer> shoppingCart, BigDecimal discountedPrice, BigDecimal ordinaryPrice) {
        String[] dateAndTime = getDateAndTime();
        String dateAndTimeInReceipt = LineUp.withTab(1) + dateAndTime[0] + LineUp.withTab(1) + dateAndTime[1];
        String shopNameInReceipt = LineUp.withTab(2) + "  Disc Shop";
        String receiptHeader = LineUp.withTab(2) + "   Receipt";

        System.out.println(receiptHeader);
        System.out.println(shopNameInReceipt);
        System.out.println(dateAndTimeInReceipt);
        Print.newLine();
        printCheckOutTotalPrice(discountedPrice, ordinaryPrice, getTotalNumOfProductsInCart(shoppingCart));
        Print.newLine();
        Print.quitMessage();
        //        Json.exportReceipt(dateAndTimeInReceipt);
    }

    private static String[] getDateAndTime() {
        String pattern = "yyyy-MM-dd,hh:mm:ss";
        return new SimpleDateFormat(pattern).format(new Date()).split(",");
    }

    public static void printCheckOutTotalPrice(BigDecimal discountedPrice, BigDecimal ordinaryPrice, BigDecimal totalAmountInCart) {
        productPriceInCart(ordinaryPrice);
        shippingCostInCart(ordinaryPrice);
        discount(discountedPrice, ordinaryPrice);
        Print.newLine();
        finalCostInCart(discountedPrice);
    }

    public static void discount(BigDecimal discountedPrice, BigDecimal ordinaryPrice) {
        BigDecimal price = ordinaryPrice.subtract(discountedPrice);
        BigDecimal numOfSpace = price.subtract(valueOf(99));
        System.out.println("Discount |" + LineUp.withTab(4) + "| -" + LineUp.withSpace(numOfSpace) + get$() + price);
    }

    public static void shippingCostInCart(BigDecimal ordinaryPrice) {
        System.out.println("Shipping " + Discounter.checkShippingCost(ordinaryPrice));
    }

    public static void productPriceInCart(BigDecimal ordinaryPrice) {
        System.out.println("Products |" + LineUp.withTab(4) + "|" + LineUp.withSpace(ordinaryPrice) + get$() + ordinaryPrice);
    }

    public static void ordinaryPriceInCart(BigDecimal ordinaryPrice) {
        BigDecimal price = ordinaryPrice.add(valueOf(7));
        System.out.println(LineUp.withTab(4) + "  Total |" + LineUp.withSpace(price) + get$() + price);
    }

    public static void finalCostInCart(BigDecimal discountedPrice) {
        BigDecimal price = Discounter.applyDiscount(discountedPrice);
        System.out.println(LineUp.withTab(4) + "  Total |" + LineUp.withSpace(price) + get$() + price);
    }

    private static String get$() {
        return "$";
    }

    private static BigDecimal getTotalNumOfProductsInCart(HashMap<Product, Integer> shoppingCart) {
        return getAmountOfEachProductInCart(shoppingCart).stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static List<BigDecimal> getAmountOfEachProductInCart(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.values().stream()
                .map(BigDecimal::new).toList();
    }

    private static List<BigDecimal> getProductPriceList(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.keySet().stream()
                .map(Product::getPrice).toList();
    }
}