package users;

import inventory.Category;
import inventory.Product;
import json.Json;
import resten.Ask;
import resten.LineUp;
import resten.Print;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Super {
    public static void printCategories(List<Category> categoryList) {
        if (categoryList.size() == 0) {
            System.out.println("Please create a category before you print it");
        } else {
            categoryList.forEach(System.out::println);
        }
    }

    public static void switchProductMenu
            (String choice, Scanner sc, List<Category> categoryList, List<Product> products) {
        switch (choice) {
            case "1" -> chooseCategory(sc, categoryList, products);
            case "2" -> chooseAllProducts(products);
            case "e" -> Print.goingBackToPreviousMenu();
            default -> Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void chooseCategory(Scanner sc, List<Category> categoryList, List<Product> products) {
        Print.productMenu(categoryList);
        chooseSpecificCategory(sc.nextLine(), categoryList, products);
    }

    private static void chooseSpecificCategory(String choice, List<Category> categoryList, List<Product> products) {
        if (choice.equals("e"))
            Print.goingBackToPreviousMenu();
        else if ((Integer.parseInt(choice) <= categoryList.size()))
            printProductsInCategory(choice, categoryList, products);
    }

    public static void printProductsInCategory(String choice, List<Category> categoryList, List<Product> products) {
        int chosenCategory = (Integer.parseInt(choice) - 1);
        Category categoryName = categoryList.get(chosenCategory);
        System.out.println(Print.productFieldNames());
        products.stream()
                .filter(product -> product.getCategory().equals(categoryName))
                .forEach(System.out::println);
    }

    public static void search(Scanner sc, List<Product> products) {
        System.out.println("""
                Search for a name, productID, brand or category""");
        String searchString = sc.nextLine().toLowerCase();

        List<Product> searchResult = getSearchResult(products, searchString);

        if (searchResult.isEmpty())
            Print.noProductFound();
        else
            printSearchResult(searchResult);
    }

    private static void printSearchResult(List<Product> searchResult) {
        System.out.println("Search result:");
        searchResult.forEach(System.out::println);
    }

    private static List<Product> getSearchResult(List<Product> products, String searchString) {
        return products.stream()
                .filter(product ->
                        searchProductID(searchString, product) ||
                        searchName(searchString, product) ||
                        searchCategory(searchString, product) ||
                        searchPrice(searchString, product) ||
                        searchBrand(searchString, product))
                .collect(Collectors.toList());
    }

    private static boolean searchProductID(String searchString, Product product) {
        return searchString.equals(String.valueOf(product.getProductID()));
    }

    private static boolean searchBrand(String searchString, Product product) {
        return searchString.equals(product.getBrand().toLowerCase()) || searchString.matches("e\s?64");
    }

    private static boolean searchPrice(String searchString, Product product) {
        return searchString.equals(String.valueOf(product.getPrice()));
    }

    private static boolean searchCategory(String searchString, Product product) {
        return searchString.equals(product.getCategory().toLowerCase());
    }

    private static boolean searchName(String searchString, Product product) {
        return searchString.equals(product.getName().toLowerCase()) ||
                product.getName().toLowerCase().contains(searchString);
    }

    public static void addNewCategory(List<Category> categoryList, Scanner sc) {
        System.out.println("Insert the name of the new category:");
        categoryList.add(new Category(sc.nextLine()));
        Json.exportCategoryListToFile(categoryList);
    }

    public static void addProduct(Scanner sc, List<Category> categoryList, List<Product> products) {
        String choice;
        Print.productMenu(categoryList);
        choice = sc.nextLine();
        try {
            if (choice.equals("e"))
                Print.goingBackToPreviousMenu();
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                addNewProduct((Integer.parseInt(choice) - 1), sc, categoryList, products);
        } catch (NumberFormatException e) {
            Print.chooseOneOfTheAlternativesBelow();
        }
    }

    public static void addNewProduct
            (int chosenCategory, Scanner sc, List<Category> categoryList, List<Product> products) {
        System.out.println("To add a new product in this category (" + categoryList.get(chosenCategory).toString() +
                "), \nyou need to fill in the following information:");

        String name = setAndCompareName(sc, products);
        BigDecimal price = setPrice(sc);
        String brand = setBrand(sc);
        int productID = setAndCompareProductID(sc, products);
        int stock = setStock(sc);

        products.add(new Product(name, price, categoryList.get(chosenCategory), brand, productID, stock));
        Json.exportProductsToFile(products);
    }

    private static String setAndCompareName(Scanner sc, List<Product> products) {
        Optional<Product> possibleDuplicateName;
        String name;
        do {
            System.out.print("Name: ");
            name = setName(sc);
            if (products.size() == 0)
                return name;
            String finalName = name;
            possibleDuplicateName = products.stream()
                    .filter(product -> product.getName().equals(finalName)).findFirst();
            if (possibleDuplicateName.isPresent()) {
                Print.duplicateProductMessage();
            }
        } while (possibleDuplicateName.isPresent());
        return name;
    }

    private static int setAndCompareProductID(Scanner sc, List<Product> products) {
        Optional<Product> possibleDuplicateProductID;
        int productID;
        do {
            System.out.print("ProductID: ");
            productID = setProductID(sc);
            if (products.size() == 0)
                return productID;
            int finalProductID = productID;
            possibleDuplicateProductID = products.stream()
                    .filter(product -> product.getProductID() == finalProductID).findAny();
            if (possibleDuplicateProductID.isPresent()) {
                Print.duplicateProductMessage();
            }
        } while (possibleDuplicateProductID.isPresent());
        return productID;
    }

    private static String setName(Scanner sc) {
        return sc.nextLine();
    }

    private static String setBrand(Scanner sc) {
        System.out.print("Brand: ");
        return sc.nextLine();
    }

    private static int setProductID(Scanner sc) {
        return Integer.parseInt(sc.nextLine());
    }

    private static BigDecimal setPrice(Scanner sc) {
        System.out.print("Price apiece: ");
        return new BigDecimal(sc.nextLine());
    }

    public static Integer setStock(Scanner sc) {
        System.out.print("Stock: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static void productsBalance(List<Product> products) {
        products.forEach(System.out::println);
    }

    public static void deleteCategory(Scanner sc, List<Category> categoryList) {
        String choice;
        Print.removeCategoryMenu(categoryList);
        choice = sc.nextLine();
        try {
            if (choice.equals("e"))
                Print.goingBackToPreviousMenu();
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                removeChosenCategory((Integer.parseInt(choice) - 1), categoryList);
        } catch (NumberFormatException e) {
            Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void removeChosenCategory(int chosenProduct, List<Category> categoryList) {
        System.out.println("The chosen category is now deleted");
        categoryList.remove(chosenProduct);
        Json.exportCategoryListToFile(categoryList);
    }

    public static void chooseAllProducts(List<Product> products) {
        System.out.println(Print.productFieldNames());
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void removeProduct(Scanner sc, List<Product> products) {
        if (products.size() == 0)
            System.out.println("A product need to be added before it can be removed");
        else {
            removeExistingProduct(sc, products);
        }
    }

    private static void removeExistingProduct(Scanner sc, List<Product> products) {
        String choice;
        Print.removeProductMenu(products);
        choice = sc.nextLine();
        try {
            if (choice.equals("e"))
                Print.goingBackToPreviousMenu();
            else if ((Integer.parseInt(choice) <= products.size()))
                removeChosenProduct((Integer.parseInt(choice) - 1), products);
        } catch (NumberFormatException e) {
            Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void removeChosenProduct(int choiceNumber, List<Product> products) {
        System.out.println("The chosen product is now deleted");
        products.remove(choiceNumber);
        Json.exportProductsToFile(products);
    }

    public static void editStock(Scanner sc, List<Product> products) {
        if (products.size() == 0)
            System.out.println("""
                    A product need to be added before it can be edited""");
        else
            editStockInProduct(sc, products);
    }

    private static void editStockInProduct(Scanner sc, List<Product> products) {
        String choice;
        Print.edibleProductMenu(products);
        choice = sc.nextLine();
        chooseProduct(choice, sc, products);
    }

    private static void chooseProduct(String choice, Scanner sc, List<Product> products) {
        if (choice.equals("e"))
            Print.goingBackToPreviousMenu();
        else if (Integer.parseInt(choice) <= products.size()) {
            Product chosenProduct = products.get(Integer.parseInt(choice) - 1);
            Print.editChosenProduct("stock");
            choice = sc.nextLine().toLowerCase();
            switchEditProductMenu(choice, chosenProduct, sc);
        }
    }

    private static void switchEditProductMenu(String choice, Product chosenProduct, Scanner sc) {
        switch (choice) {
            case "1" -> increaseStock(sc, choice, chosenProduct);
            case "2" -> decreaseStock(sc, choice, chosenProduct);
            case "e" -> Print.goingBackToPreviousMenu();
            default -> Print.chooseOneOfTheAlternativesBelow();
        }
    }

    private static void increaseStock(Scanner sc, String choice, Product chosenProduct) {
        Ask.forNewStockOrAmount(choice, "stock");
        chosenProduct.editStock(chosenProduct.getStock() + sc.nextInt());
        sc.nextLine();
        Print.newStock(chosenProduct);
    }

    private static void decreaseStock(Scanner sc, String choice, Product chosenProduct) {
        Ask.forNewStockOrAmount(choice, "stock");
        int decreasingStock = sc.nextInt();
        sc.nextLine();
        if (chosenProduct.getStock() - decreasingStock < 1) {
            System.out.println("""
                    You need at least one piece of the specified product in your inventory
                    """);
            decreaseStock(sc, choice, chosenProduct);
        } else {
            chosenProduct.editStock(chosenProduct.getStock() - decreasingStock);
            Print.newStock(chosenProduct);
        }
    }

    public static String getIncreaseOrDecrease(String choice) {
        if (choice.equals("1"))
            return "increase";
        else
            return "decrease";
    }







}