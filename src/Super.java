import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public abstract class Super {
    static void printCategories(ArrayList<Category> categoryList) {
        if (categoryList.size() == 0) {
            System.out.println("Please create a category before you print it");
        } else {
            categoryList.forEach(System.out::println);
        }
    }
    static void switchProductMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> chooseCategory(sc, categoryList, products);
            case "2" -> allProducts(products, categoryList);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void chooseCategory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        Print.productMenu(categoryList);
        choice = sc.nextLine();
        chooseSpecificCategory(choice, sc, categoryList, products);
    }
    private static void chooseSpecificCategory(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (choice.equals("e"))
            System.out.println("Going back to previous menu");
        else if ((Integer.parseInt(choice) <= categoryList.size()))
            printProductsInCategory(choice, categoryList, products);
    }
    static void printProductsInCategory(String choice, ArrayList<Category> categoryList, ArrayList<Product> products) {
        int chosenCategory = (Integer.parseInt(choice) - 1);
        Category categoryName = categoryList.get(chosenCategory);
        System.out.println(printProductFieldNames());
        products.stream()
                .filter(product -> product.getCategory().equals(categoryName))
                .forEach(System.out::println);
    }
    static String printProductFieldNames() {
        return ("Name" + LineUp.lineUpName(4) +
                "| Price" + LineUp.lineUpPrice(5) +
                "| Category" + LineUp.lineUpCategory(8) +
                "| Brand" + LineUp.lineUpBrand(5) +
                "| ProductID" + LineUp.lineUpProductID(9) +
                "| Stock");
    }
    static void search(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
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
    private static List<Product> getSearchResult(ArrayList<Product> products, String searchString) {
        return products.stream()
                .filter(product -> searchString.equals(String.valueOf(product.getProductID())) ||
                        searchString.equals(product.getName().toLowerCase()) ||
                        product.getName().toLowerCase().contains(searchString) ||
                        searchString.equals(product.getCategory().toLowerCase()) ||
                        searchString.matches("e\s?64") ||
                        searchString.equals(String.valueOf(product.getPrice())) ||
                        searchString.equals(product.getBrand().toLowerCase()))
                .collect(Collectors.toList());
    }
    static void addNewCategory(ArrayList<Category> categoryList, Scanner sc) {
        System.out.println("Insert the name of the new category:");
        categoryList.add(new Category(sc.nextLine()));
    }
    static void addProduct(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        Print.productMenu(categoryList);
        choice = sc.nextLine();
        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                addNewProduct((Integer.parseInt(choice) - 1), sc, categoryList, products);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
        }
    }
    static void addNewProduct(int chosenCategory, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("To add a new product in this category (" + categoryList.get(chosenCategory).toString() +
                "), \nyou need to fill in the following information:");

        String name = setAndCompareName(sc, products);
        BigDecimal price = setPrice(sc);
        String brand = setBrand(sc);
        int productID = setAndCompareProductID(sc, products);
        int stock = setStock(sc);

        products.add(new Product(name, price, categoryList.get(chosenCategory), brand, productID, stock));
        Json.saveToFile(products);
    }
    private static String setAndCompareName(Scanner sc, ArrayList<Product> products) {
        Optional<Product> possibleDuplicateName;
        String name;
        do {
            System.out.print("Name: ");
            name = setName(sc);
            if (products.size() == 0)
                return name;
            String finalName = name;
            possibleDuplicateName = products.stream()
                    .filter(product -> product.getName().equals(finalName))
                    .findFirst();
            if (possibleDuplicateName.isPresent()) {
                Print.duplicateProductMessage();
            }
        } while (possibleDuplicateName.isPresent());
        return name;
    }
    private static int setAndCompareProductID(Scanner sc, ArrayList<Product> products) {
        Optional<Product> possibleDuplicateProductID;
        int productID;
        do {
            System.out.print("ProductID: ");
            productID = setProductID(sc);
            if (products.size() == 0)
                return productID;
            int finalProductID = productID;
            possibleDuplicateProductID = products.stream()
                    .filter(product -> product.getProductID() == finalProductID)
                    .findAny();
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
        System.out.print("Price: ");
        return new BigDecimal(sc.nextLine());
    }
    static Integer setStock(Scanner sc) {
        System.out.print("Stock: ");
        return Integer.parseInt(sc.nextLine());
    }
    static void productsBalance(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        products.forEach(System.out::println);
    }
    static void deleteCategory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        Print.removeCategoryMenu(categoryList, products);
        choice = sc.nextLine();
        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                removeChosenCategory((Integer.parseInt(choice) - 1), sc, categoryList, products);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void removeChosenCategory(int chosenProduct, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("The chosen category is now deleted");
        categoryList.remove(chosenProduct);
    }
    static void allProducts(ArrayList<Product> products, ArrayList<Category> categoryList) {
        System.out.println(printProductFieldNames());
        for (Product product : products) {
            System.out.println(product);
        }
    }
    static void removeProduct(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0)
            System.out.println("A product need to be added before it can be removed");
        else {
            removeExistingProduct(sc, categoryList, products);
        }
    }
    private static void removeExistingProduct(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        Print.removeProductMenu(products);
        choice = sc.nextLine();
        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= products.size()))
                removeChosenProduct((Integer.parseInt(choice) - 1), sc, categoryList, products);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void removeChosenProduct(int choiceNumber, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("The chosen product is now deleted");
        products.remove(choiceNumber);
        Json.saveToFile(products);
    }
    static void editStock(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0)
            System.out.println("""
                    A product need to be added before it can be edited""");
        else
            chooseProductToEdit(sc, products);
    }
    private static void chooseProductToEdit(Scanner sc, ArrayList<Product> products) {
        String choice;
        Print.edibleProductMenu(sc, products);
        choice = sc.nextLine();
        chooseSpecificProduct(choice, sc, products);
    }
    private static void chooseSpecificProduct(String choice, Scanner sc, ArrayList<Product> products) {
        if (choice.equals("e"))
            System.out.println("Going back to previous menu");
        else if (Integer.parseInt(choice) <= products.size()) {
            int chosenProduct = (Integer.parseInt(choice) - 1);
            Print.editChosenProduct(choice, products);
            choice = sc.nextLine().toLowerCase();
            switchEditProductMenu(choice, chosenProduct, sc, products);
        }
    }
    private static void switchEditProductMenu(String choice, int chosenProduct, Scanner sc, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> increaseStock(sc, choice, chosenProduct, products);
            case "2" -> decreaseStock(sc, choice, chosenProduct, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void increaseStock(Scanner sc, String choice, int chosenProduct, ArrayList<Product> products) {
        Ask.forNewStock(choice);
        products.get(chosenProduct).editStock(products.get(chosenProduct).getStock() + sc.nextInt());
        sc.nextLine();
        printNewStock(chosenProduct, products);
    }
    private static void decreaseStock(Scanner sc, String choice, int chosenProduct, ArrayList<Product> products) {
        Ask.forNewStock(choice);
        int decreasingStock = sc.nextInt();
        sc.nextLine();
        if (products.get(chosenProduct).getStock() - decreasingStock < 1) {
            System.out.println("""
                    You need at least one piece of the specified product in your inventory
                    """);
            decreaseStock(sc, choice, chosenProduct, products);
        } else {
            products.get(chosenProduct).editStock(products.get(chosenProduct).getStock() - decreasingStock);
            printNewStock(chosenProduct, products);
        }
    }
    private static void printNewStock(int chosenProduct, ArrayList<Product> products) {
        System.out.println("The new stock for " + products.get(chosenProduct).getName() + " is: " + products.get(chosenProduct).getStock());
    }
    static String increaseOrDecrease(String choice) {
        if (choice.equals("1"))
            return "increase";
        else
            return "decrease";
    }
    static void addToCart(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) throws Exception {
        String choice;

        Print.addToCartMenu(products);
        choice = sc.nextLine();

        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if (choice.equals("p"))
                toCheckout(sc, categoryList, products, shoppingCart);
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                addProductToCart((Integer.parseInt(choice) - 1), sc, categoryList, products, shoppingCart);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void addProductToCart(int chosenProduct, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) {
        System.out.println("How many " + products.get(chosenProduct).getName() + " would you like to add?");
        int amountInCart = Integer.parseInt(sc.nextLine());
        if (shoppingCart.containsKey(products.get(chosenProduct))) {
            int newAmountInCart = shoppingCart.get(products.get(chosenProduct)) + amountInCart;
            amountInCart = shoppingCart.get(products.get(chosenProduct));
            shoppingCart.replace(products.get(chosenProduct), amountInCart, newAmountInCart);
        } else
            shoppingCart.put(products.get(chosenProduct), amountInCart);
    }
    static void viewCart(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) {
        Print.cartFieldNames();
        Print.cart(shoppingCart);
    }
    static void editCart(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) {
        String choice;
        Print.edibleProductMenu(sc, products);
        choice = sc.nextLine();
        chooseSpecificProduct(choice, sc, products);

        Ask.forProductToEdit();

        if (shoppingCart.size() == 0)
            System.out.println("""
                    The cart is empty""");
        else
            chooseProductInCart(sc, shoppingCart, products);
    }
    private static void chooseProductInCart(Scanner sc, HashMap<Product, Integer> shoppingCart, ArrayList<Product> products) {
        Ask.forProductToEdit();
        var keyList = shoppingCart.keySet().stream().map(Product::printInCart).toList();
        var valueList = shoppingCart.values().stream().toList();

        Print.cartWithNumbers(shoppingCart, keyList, valueList);
        String choice = sc.nextLine();
        chooseSpecificProductInCart(choice, sc, products, shoppingCart);
    }
    private static void chooseSpecificProductInCart(String choice, Scanner sc, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) {
        if (choice.equals("e"))
            System.out.println("Going back to previous menu");
        else if (Integer.parseInt(choice) <= products.size()) {
            int chosenProduct = (Integer.parseInt(choice) - 1);
            Print.editChosenProduct(choice, products);
            choice = sc.nextLine().toLowerCase();
            switchEditProductInCart(choice, chosenProduct, sc, products, shoppingCart);
        }
    }
    private static void switchEditProductInCart(String choice, int chosenProduct, Scanner sc, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) {
        switch (choice) {
            case "1" -> increaseProductInCart(sc, choice, chosenProduct, products, shoppingCart);
            case "2" -> decreaseProductInCart(sc, choice, chosenProduct, products, shoppingCart);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
    private static void increaseProductInCart(Scanner sc, String choice, int chosenProduct, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) {
        askForNewAmount(choice);
        products.get(chosenProduct).editStock(products.get(chosenProduct).getStock() + sc.nextInt());
        sc.nextLine();
        printNewStock(chosenProduct, products);
    }
    private static void decreaseProductInCart(Scanner sc, String choice, int chosenProduct, ArrayList<Product> products, HashMap<Product, Integer> shoppingCart) {
        askForNewAmount(choice);
        int decreasingStock = sc.nextInt();
        sc.nextLine();
        if (products.get(chosenProduct).getStock() - decreasingStock < 1) {
            System.out.println("""
                    You need at least one piece of the specified product in your inventory
                    """);
            decreaseStock(sc, choice, chosenProduct, products);
        } else {
            products.get(chosenProduct).editStock(products.get(chosenProduct).getStock() - decreasingStock);
            Print.cart(shoppingCart);
        }
    }
    private static void askForNewAmount(String choice) {

    }
    static void toCheckout (Scanner sc, ArrayList < Category > categoryList, ArrayList < Product > products, HashMap < Product, Integer > shoppingCart) {
    }
}