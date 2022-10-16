package users;

import inventory.Category;
import inventory.Product;
import resten.Print;
import shop.PointOfSale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static shop.PointOfSale.checkOut;
import static shop.Shop.*;

public class SuperShop {
    static void showShopMenu(Scanner sc, List<Category> categoryList, List<Product> products,
                             HashMap<Product, Integer> shoppingCart) {
        var visibleCopyOfProducts = getCopyOfProducts(products);
        doShopMenuWhile(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
    }

    private static void doShopMenuWhile
            (Scanner sc, List<Category> categoryList, List<Product> products,
             HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        String choice;
        do {
            Print.shopMenu();
            choice = sc.nextLine().toLowerCase();
            switchShopMenu(choice, sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
        } while (!choice.equals("e"));
    }

    private static List<Product> getCopyOfProducts(List<Product> products) {
        var visibleCopyOfProducts = new ArrayList<Product>();
        for (Product product : products) {
            visibleCopyOfProducts.add(newProduct(product));
        }
        return visibleCopyOfProducts;
    }

    private static Product newProduct(Product product) {
        return new Product(product.getName(), product.getPrice(), product.getCategory(),
                product.getBrand(), product.getProductID(), product.getStock());
    }

    private static void switchShopMenu
            (String choice, Scanner sc, List<Category> categoryList, List<Product> products,
             HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        switch (choice) {
            case "1" -> addToCart(sc, products, shoppingCart, visibleCopyOfProducts);
            case "2" -> viewCart(shoppingCart);
            case "3" -> editCart(sc, products, shoppingCart, visibleCopyOfProducts);
            case "4" -> checkOut(sc, shoppingCart, visibleCopyOfProducts);
            case "e" -> leaveShop(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void leaveShop
            (Scanner sc, List<Category> categoryList, List<Product> products,
             HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        String choice;
        do {
            Print.leavingShop();
            choice = sc.nextLine().toLowerCase();
            switchLeaveShop(choice, sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
        } while (!choice.equals("1") && (!choice.equals("e")));
    }

    private static void switchLeaveShop
            (String choice, Scanner sc, List<Category> categoryList, List<Product> products,
             HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        if (choice.equals("1"))
            doShopMenuWhile(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
        else if (choice.equals("e")) {
            shoppingCart.clear();
        }
    }
}
