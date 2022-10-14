import java.util.*;

public class Master extends Super {
    static void menu(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        String choice;
        do {
            Print.masterStartMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products, shoppingCart);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        switch (choice) {
            case "1" -> inventory(sc, categoryList, products);
            case "2" -> category(sc, categoryList, products);
            case "3" -> search(sc, categoryList, products);
            case "4" -> showShopMenu(sc, categoryList, products, shoppingCart);
            case "e" -> Print.quitMessage();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void inventory(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
        do {
            Print.masterInventoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchInventory(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }


    private static void switchInventory(String choice, Scanner sc, List<Category> categoryList, List<Product> products) {
        switch (choice) {
            case "1" -> printInventory(sc, categoryList, products);
            case "2" -> addProduct(sc, categoryList, products);
            case "3" -> editStock(sc, categoryList, products);
            case "4" -> removeProduct(sc, categoryList, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void printInventory(Scanner sc, List<Category> categoryList, List<Product> products) {
        if (products.size() == 0) {
            System.out.println("""
                    Please add a product before print it""");
        } else {
            Ask.forCategoryToPrint();
            Print.alternatives();
            String choice = sc.nextLine().toLowerCase();
            switchProductMenu(choice, sc, categoryList, products);
        }
    }

    private static void showShopMenu(Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart) {
        String choice;
        var visibleCopyOfProducts = getCopyOfProducts(products);
        do {
            Print.shopMenu();
            choice = sc.nextLine().toLowerCase();
            switchShopMenu(choice, sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
        } while (!choice.equals("e"));
    }

    private static List<Product> getCopyOfProducts(List<Product> products) {
        var visibleCopyOfProducts = new ArrayList<Product>();

        for (Product product : products) {
            visibleCopyOfProducts.add(new Product(product.getName(), product.getPrice(), product.getCategory(), product.getBrand(), product.getProductID(), product.getStock()));
        }
        return visibleCopyOfProducts;
    }


    private static void switchShopMenu(String choice, Scanner sc, List<Category> categoryList, List<Product> products, HashMap<Product, Integer> shoppingCart, List<Product> visibleCopyOfProducts) {
        switch (choice) {
            case "1" -> addToCart(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
            case "2" -> viewCart(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
            case "3" -> editCart(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
            case "4" -> toCheckout(sc, categoryList, products, shoppingCart, visibleCopyOfProducts);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    public static void category(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
        do {
            Print.masterCategoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchCategories(categoryList, choice, sc, products);
        } while (!choice.equals("e"));
    }

    private static void switchCategories(List<Category> categoryList, String choice, Scanner sc, List<Product> products) {
        switch (choice) {
            case "1" -> addNewCategory(categoryList, sc);
            case "2" -> printCategories(categoryList);
            case "3" -> deleteCategory(sc, categoryList, products);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
}