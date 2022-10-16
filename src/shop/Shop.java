package shop;

import discounts.Discounter;
import json.Json;
import resten.Ask;
import resten.LineUp;
import resten.Print;
import users.Super;
import inventory.*;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

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


}
