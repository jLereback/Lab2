package shop;

import discounts.Discounter;
import inventory.Product;
import files.Files;
import misc.Ask;
import misc.LineUp;
import misc.Print;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.valueOf;

public class PointOfSale {
    public static void checkOut(Scanner sc, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        BigDecimal ordTotalPrice = getOrdTotalPrice(shoppingCart);
        Print.checkOutTotalPrice(ordTotalPrice);
        String choice = checkPromoCode(sc);
        BigDecimal discountedPrice = Discounter.applyDiscount
                (ordTotalPrice, getUserInputOfPromoCode(sc, choice));

        loadCheckingDiscounts();

        System.out.println(Print.checkOutCart(discountedPrice, ordTotalPrice));
        saveReceiptAndUpdateProductList
                (sc, discountedPrice, ordTotalPrice, visibleCopyOfProducts);
    }

    private static void saveReceiptAndUpdateProductList(Scanner sc, BigDecimal discountedPrice, BigDecimal ordTotalPrice,
                                                        List<Product> visibleCopyOfProducts) {
        Print.saveReceiptOption();
        String choice = sc.nextLine();
        if (choice.equals("1")) {
            Files.exportReceipt(getReceipt(discountedPrice, ordTotalPrice));
            Files.exportProductsToFile(visibleCopyOfProducts);
        } else if (choice.equals("2")) {
            System.out.println(getReceipt(discountedPrice, ordTotalPrice));
            Files.exportProductsToFile(visibleCopyOfProducts);
        } else
            System.out.println("Going back to the shop");
    }

    private static String checkPromoCode(Scanner sc) {
        Print.promoCodeOption();
        return sc.nextLine();
    }

    private static BigDecimal getOrdTotalPrice(HashMap<Product, Integer> shoppingCart) {
        List<BigDecimal> ordPriceList = new ArrayList<>();
        putTotalPriceToList(ordPriceList, shoppingCart);
        return getOrdTotalPrice(ordPriceList);
    }

    public static String nextLine() {
        return "\n";
    }

    private static BigDecimal getOrdTotalPrice(List<BigDecimal> ordPriceList) {
        return ordPriceList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static String getUserInputOfPromoCode(Scanner sc, String choice) {
        if (choice.equals("1")) {
            System.out.println("\nFill in the code");
            return sc.nextLine();
        } else
            return "null";
    }

    public static void tryAgain(Scanner sc) {
        String choice;
        do {
            tryNewPromoCode();
            choice = sc.nextLine();
            getUserInputOfPromoCode(sc, choice);
        } while (choice.equals("1") || choice.equals("e"));
    }

    public static void tryNewPromoCode() {
        Print.notValidPromoCode();
        Ask.toTryAgain();
        Print.yesAndNo();
    }

    private static void putTotalPriceToList(List<BigDecimal> ordPriceList, HashMap<Product, Integer> shoppingCart) {
        for (int i = 0; i < getProductPriceList(shoppingCart).size(); i++) {
            ordPriceList.add(getTotalPrice(shoppingCart, i));
        }
    }

    private static BigDecimal getTotalPrice(HashMap<Product, Integer> shoppingCart, int i) {
        return getProductPriceList(shoppingCart).get(i)
                .multiply(getAmountOfEachProductInCart(shoppingCart).get(i));
    }

    public static String getReceipt(BigDecimal discountedPrice, BigDecimal ordPrice) {
        String[] dateAndTimeArray = getDateAndTime();
        String dateAndTime = LineUp.withTab
                (1) + dateAndTimeArray[0] + LineUp.withTab(1) + dateAndTimeArray[1];
        BigDecimal price = ordPrice.subtract(discountedPrice);
        if (price.equals(valueOf(0)))
            return String.format("""

                            ͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟
                                       Receipt
                                      Disc Shop
                            %s
                                            
                            %s
                            %s
                                            
                            %s
                                                    
                                    Welcome back
                            ͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟
                            """, dateAndTime, getOrdProductPriceInCart(ordPrice),
                    getShippingCostInCart(ordPrice), finalCostInCart(discountedPrice));
        else
            return String.format("""
                                                    
                            ͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟
                                       Receipt
                                      Disc Shop
                            %s
                                            
                            %s
                            %s
                            %s
                                            
                            %s
                                                    
                                    Welcome back
                            ͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟͟
                            """, dateAndTime, getOrdProductPriceInCart(ordPrice), getShippingCostInCart(ordPrice),
                    getDiscount(discountedPrice, ordPrice), finalCostInCart(discountedPrice));
    }

    private static String[] getDateAndTime() {
        String pattern = "yyyy-MM-dd,HH:mm:ss";
        return new SimpleDateFormat(pattern).format(new Date()).split(",");
    }


    public static String getDiscount(BigDecimal discountedPrice, BigDecimal ordPrice) {
        BigDecimal price = ordPrice.subtract(discountedPrice);
        BigDecimal numOfSpace = price.subtract(valueOf(99));
        return "Discount |" + LineUp.withTab(4) + "| -" + LineUp.withSpace(numOfSpace) + get$() + price;
    }

    public static String getShippingCostInCart(BigDecimal ordPrice) {
        return "Shipping " + Discounter.checkShippingCost(ordPrice);
    }

    public static String getOrdShippingCostInCart() {
        return "Shipping |" + LineUp.withTab(4) + "|\t$7";
    }

    public static String getOrdProductPriceInCart(BigDecimal ordPrice) {
        return "Products |" + LineUp.withTab(4) + "|" + LineUp.withSpace(ordPrice) + get$() + ordPrice;
    }

    public static String getOrdTotalPriceInCart(BigDecimal ordPrice) {
        BigDecimal price = ordPrice.add(valueOf(7));
        return LineUp.withTab(4) + "  Total |" + LineUp.withSpace(price) + get$() + price;
    }

    public static String finalCostInCart(BigDecimal discountedPrice) {
        BigDecimal price = Discounter.applyDiscount(discountedPrice);
        return LineUp.withTab(4) + "  Total |" + LineUp.withSpace(price) + get$() + price;
    }

    private static String get$() {
        return "$";
    }

    private static List<BigDecimal> getAmountOfEachProductInCart(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.values().stream()
                .map(BigDecimal::new).toList();
    }

    private static List<BigDecimal> getProductPriceList(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.keySet().stream()
                .map(Product::getPrice).toList();
    }


    public static void loadCheckingDiscounts() {
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
}
