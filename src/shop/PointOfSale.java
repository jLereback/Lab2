package shop;

import discounts.Discounter;
import inventory.Product;
import json.Json;
import resten.LineUp;
import resten.Print;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.valueOf;

public class PointOfSale {
    public static void checkOutTotalPrice(BigDecimal ordinaryPrice) {
        System.out.println(getOrdinaryProductPriceInCart(ordinaryPrice));
        printShippingCostInCart();
        System.out.println(getOrdinaryTotalPriceInCart(ordinaryPrice));
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

        PointOfSale.loadCheckingDiscounts();

        printCheckOutTotalPrice(discountedPrice, ordinaryTotalPrice, getTotalNumOfProductsInCart(shoppingCart));


        products = visibleCopyOfProducts;
        Json.exportProductsToFile(products);


        receipt(shoppingCart, discountedPrice, ordinaryTotalPrice);
        Json.exportReceipt(receipt(shoppingCart, discountedPrice, ordinaryTotalPrice));
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

    public static String receipt(HashMap<Product, Integer> shoppingCart, BigDecimal discountedPrice, BigDecimal ordinaryPrice) {
        String[] dateAndTimeArray = getDateAndTime();
        String dateAndTime = LineUp.withTab(1) + dateAndTimeArray[0] + LineUp.withTab(1) + dateAndTimeArray[1];

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
                """, dateAndTime, getOrdinaryProductPriceInCart(ordinaryPrice), getShippingCostInCart(ordinaryPrice),
                getDiscount(discountedPrice, ordinaryPrice), finalCostInCart(discountedPrice));
    }

    private static String getNameOfShop() {
        return LineUp.withTab(2) + "  Disc Shop";
    }

    private static String getReceiptHeader() {
        return LineUp.withTab(2) + "   Receipt";
    }

    private static String[] getDateAndTime() {
        String pattern = "yyyy-MM-dd,hh:mm:ss";
        return new SimpleDateFormat(pattern).format(new Date()).split(",");
    }

    public static void printCheckOutTotalPrice(BigDecimal discountedPrice, BigDecimal ordinaryPrice, BigDecimal totalAmountInCart) {
        System.out.println(getOrdinaryProductPriceInCart(ordinaryPrice));
        System.out.println(getShippingCostInCart(ordinaryPrice));
        System.out.println(getDiscount(discountedPrice, ordinaryPrice));
        Print.newLine();
        System.out.println(finalCostInCart(discountedPrice));
    }

    public static String getDiscount(BigDecimal discountedPrice, BigDecimal ordinaryPrice) {
        BigDecimal price = ordinaryPrice.subtract(discountedPrice);
        BigDecimal numOfSpace = price.subtract(valueOf(99));
        return "Discount |" + LineUp.withTab(4) + "| -" + LineUp.withSpace(numOfSpace) + get$() + price;
    }

    public static String getShippingCostInCart(BigDecimal ordinaryPrice) {
        return "Shipping " + Discounter.checkShippingCost(ordinaryPrice);
    }

    public static String getOrdinaryProductPriceInCart(BigDecimal ordinaryPrice) {
        return "Products |" + LineUp.withTab(4) + "|" + LineUp.withSpace(ordinaryPrice) + get$() + ordinaryPrice;
    }
    public static String getOrdinaryTotalPriceInCart(BigDecimal ordinaryPrice) {
        BigDecimal price = ordinaryPrice.add(valueOf(7));
        return LineUp.withTab(4) + "  Total |" + LineUp.withSpace(price) + get$() + price;
    }
    public static String finalCostInCart(BigDecimal discountedPrice) {
        BigDecimal price = Discounter.applyDiscount(discountedPrice);
        return LineUp.withTab(4) + "  Total |" + LineUp.withSpace(price) + get$() + price;
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
