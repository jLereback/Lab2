package shop;

import json.Json;
import resten.Ask;
import resten.LineUp;
import resten.Print;
import users.Super;
import inventory.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public abstract class Shop extends Super {

    public static void addToCart(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        String choice;
        Print.addToCartMenu(visibleCopyOfProducts);
        choice = sc.nextLine().toLowerCase();
        try {
            if (choice.equals("e"))
                Print.goingBackToPreviousMenu();
            else if ((Integer.parseInt(choice) <= products.size())) {
                checkChosenProduct(sc, categoryList, products, shoppingCart, visibleCopyOfProducts, choice);
            } else
                Print.chooseOneOfTheAlternativesBelow();
        } catch (NumberFormatException e) {
            Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void checkChosenProduct(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts, String choice) {
        if (getChosenProduct(visibleCopyOfProducts, choice).getStock() <= 0)
            System.out.println("This product is out of stock");
        else
            addProductToCart(getChosenProduct(visibleCopyOfProducts, choice), sc, categoryList, products, shoppingCart, visibleCopyOfProducts, getChosenProduct(products, choice));
    }


    private static Product getChosenProduct(List<Product> products, String choice) {
        return products.get(Integer.parseInt(choice) - 1);
    }

    private static void addProductToCart(Product tempChosenProduct, Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts, Product chosenProduct) {
        Ask.howManyToAdd(tempChosenProduct);
        int amountInCart = getInput(sc);

        if (tempChosenProduct.getStock() < amountInCart)
            addAvailableAmountToCart(tempChosenProduct, shoppingCart, chosenProduct);
        else
            addAmountToCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
    }

    private static void addAvailableAmountToCart(Product tempChosenProduct, HashMap<Product, Integer> shoppingCart, Product chosenProduct) {
        int amountInCart;
        Print.addAvailableAmount(tempChosenProduct);
        amountInCart = tempChosenProduct.getStock();
        addAmountToCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
        Print.productAddedToCart(tempChosenProduct, amountInCart);
    }


    private static void addAmountToCart(Product tempChosenProduct, HashMap<Product, Integer> shoppingCart, Product chosenProduct, int amountInCart) {
        if (shoppingCart.containsKey(chosenProduct))
            addAmountToProductInCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
        else
            addNewProductInCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
    }

    private static void addNewProductInCart(Product tempChosenProduct, HashMap<Product, Integer> shoppingCart, Product chosenProduct, int amountInCart) {
        shoppingCart.put(chosenProduct, amountInCart);
        updateProductStock(tempChosenProduct, amountInCart);
    }


    private static void addAmountToProductInCart(Product tempChosenProduct, HashMap<Product, Integer> shoppingCart, Product chosenProduct, int amountInCart) {
        increaseChosenAmount(chosenProduct, shoppingCart.get(chosenProduct), shoppingCart, amountInCart);
        updateProductStock(tempChosenProduct, amountInCart);
    }

    private static void updateProductStock(Product tempChosenProduct, int amountInCart) {
        tempChosenProduct.editStock(tempChosenProduct.getStock() - amountInCart);
    }

    private static int getInput(Scanner sc) {
        return Integer.parseInt(sc.nextLine());
    }

    public static void viewCart(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        Print.cartFieldNames();
        Print.cart(shoppingCart);
    }

    public static void editCart(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        if (shoppingCart.size() == 0)
            System.out.println("""
                    The cart is empty""");
        else
            editProductInCart(sc, shoppingCart, products, visibleCopyOfProducts);
    }

    private static void editProductInCart(Scanner sc, HashMap<Product, Integer> shoppingCart, List<Product> products, List<Product> visibleCopyOfProducts) {
        Ask.forProductToEdit();
        List<Product> listOfCart = shoppingCart.keySet().stream().toList();
        var keyList = shoppingCart.keySet().stream().map(Product::printInCart).toList();
        var valueList = shoppingCart.values().stream().toList();

        Print.cartWithNumbers(shoppingCart, keyList, valueList);
        String choice = sc.nextLine();
        chooseProductInCart(choice, sc, products, shoppingCart, listOfCart, visibleCopyOfProducts);
    }

    private static void chooseProductInCart(String choice, Scanner sc, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> listOfCart, List<Product> visibleCopyOfProducts) {
        if (choice.equals("e"))
            Print.goingBackToPreviousMenu();
        else if (Integer.parseInt(choice) <= products.size()) {
            Product chosenProduct = listOfCart.get(Integer.parseInt(choice) - 1);

            int currentAmount = shoppingCart.get(chosenProduct);
            Print.editChosenProduct(choice, products, "amount");
            choice = sc.nextLine().toLowerCase();
            switchEditProductInCart(choice, chosenProduct, getTempChosenProduct(visibleCopyOfProducts, chosenProduct), currentAmount, sc, products, shoppingCart);
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

    private static void switchEditProductInCart(String choice, Product chosenProduct, Product tempChosenProduct, int currentAmount, Scanner sc, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        switch (choice) {
            case "1" ->
                    increaseItemInCart(sc, choice, chosenProduct, tempChosenProduct, currentAmount, products, shoppingCart);
            case "2" ->
                    decreaseAmountInCart(sc, choice, chosenProduct, tempChosenProduct, currentAmount, products, shoppingCart);
            case "e" -> Print.goingBackToPreviousMenu();
            default -> Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void increaseItemInCart(Scanner sc, String choice, Product chosenProduct, Product tempChosenProduct, int currentAmount, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        if (tempChosenProduct.getStock() == 0)
            Print.availableAmountAlreadyInCart();
        else {
            increaseSpecifiedProduct(sc, choice, chosenProduct, tempChosenProduct, currentAmount, shoppingCart);
        }
    }

    private static void increaseSpecifiedProduct(Scanner sc, String choice, Product chosenProduct, Product tempChosenProduct, int currentAmount, HashMap<Product, Integer> shoppingCart) {
        Ask.forNewStockOrAmount(choice, "amount");
        int increasingAmount = getInput(sc);
        if (tempChosenProduct.getStock() < increasingAmount) {
            increaseAvailableAmount(chosenProduct, tempChosenProduct, currentAmount, shoppingCart);
        } else
            increaseChosenAmount(chosenProduct, currentAmount, shoppingCart, increasingAmount);
    }

    private static void increaseChosenAmount(Product chosenProduct, int currentAmount, HashMap<Product, Integer> shoppingCart, int increasingAmount) {
        int newAmount = currentAmount + increasingAmount;
        shoppingCart.replace(chosenProduct, currentAmount, newAmount);
    }

    private static void increaseAvailableAmount(Product chosenProduct, Product tempChosenProduct, int currentAmount, HashMap<Product, Integer> shoppingCart) {
        int availableAmount = tempChosenProduct.getStock();
        int newAmount = currentAmount + availableAmount;
        int emptyStock = 0;
        tempChosenProduct.editStock(emptyStock);
        shoppingCart.replace(chosenProduct, currentAmount, newAmount);
    }

    private static void addAvailableAmountOfProductInCart(Product tempChosenProduct, HashMap<Product, Integer> shoppingCart, Product chosenProduct) {
        int amountInCart;
        Print.addAvailableAmount(tempChosenProduct);
        amountInCart = tempChosenProduct.getStock();
        addAmountToCart(tempChosenProduct, shoppingCart, chosenProduct, amountInCart);
        Print.productAddedToCart(tempChosenProduct, amountInCart);
    }

    private static void decreaseAmountInCart(Scanner sc, String choice, Product chosenProduct, Product tempChosenProduct, int currentAmount, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        Ask.forNewStockOrAmount(choice, "amount");
        int decreasingAmount = getInput(sc);
        int newAmount = currentAmount - decreasingAmount;

        if (shoppingCart.get(chosenProduct) - decreasingAmount <= 0) {

            tempChosenProduct.editStock(tempChosenProduct.getStock() + shoppingCart.get(chosenProduct));

            shoppingCart.remove(chosenProduct);
            Print.productRemovedCart();
        } else {
            shoppingCart.replace(chosenProduct, currentAmount, newAmount);
            tempChosenProduct.editStock(tempChosenProduct.getStock() + decreasingAmount);

            System.out.println("The specified product in cart is updated");
        }
    }

    public static void toCheckout(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        viewCart(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);

        List<BigDecimal> totalPriceList = new ArrayList<>();

        for (int i = 0; i < getProductPrice(shoppingCart).size(); i++) {
            BigDecimal totalPrice = getProductPrice(shoppingCart).get(i)
                    .multiply(getAmountOfEachProductInCart(shoppingCart).get(i));
            totalPriceList.add(totalPrice);
        }

        BigDecimal totalPrice = totalPriceList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        discountsTotalPrice(totalPrice, getTotalNumOfProductsInCart(shoppingCart));

        freeShipping();

        products = visibleCopyOfProducts;
        Json.exportProductsToFile(products);

        Print.receipt();
    }
    public static void discountsTotalPrice(BigDecimal totalPrice, BigDecimal totalAmountInCart) {
        var priceSpace = LineUp.price(totalPrice.toString().length());
        BigDecimal shippingCost = new BigDecimal(7);
        Print.newLine();
        Print.productPriceInCart(totalPrice, totalAmountInCart, priceSpace);
        Print.shippingPriceInCart(totalPrice, totalAmountInCart, priceSpace);
        Print.totalPriceInCart(totalPrice, shippingCost, priceSpace);
    }
    private static BigDecimal getTotalNumOfProductsInCart(HashMap<Product, Integer> shoppingCart) {
        return getAmountOfEachProductInCart(shoppingCart).stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static List<BigDecimal> getAmountOfEachProductInCart(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.values().stream()
                .map(BigDecimal::new).toList();
    }

    private static List<BigDecimal> getProductPrice(HashMap<Product, Integer> shoppingCart) {
        return shoppingCart.keySet().stream()
                .map(Product::getPrice).toList();
    }

    private static void freeShipping() {

    }

    private BigDecimal getDiscountedPrice(BigDecimal priceToPay) {
        return null;
    }


}