import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.rangeClosed;


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
            case "2" -> printAllProducts(products, categoryList);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void chooseCategory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        Menu.printProductMenu(categoryList);
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
        int choiceNumber = (Integer.parseInt(choice) - 1);
        Category categoryName = categoryList.get(choiceNumber);
        System.out.println("Name" + LineUp.lineUpName(4) +
                "| Price" + LineUp.lineUpPrice(5) +
                "| Category" + LineUp.lineUpCategory(8) +
                "| Brand" + LineUp.lineUpBrand(5) +
                "| ProductID" + LineUp.lineUpProductID(9) +
                "| Stock");
        products.stream()
                .filter(product -> product.getCategory().equals(categoryName))
                .forEach(System.out::println);
    }

    static void search(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("""
                If you want to search for a product within a price range
                        -> write 'price' and press enter
                Search for a name, productID, brand or category

                """);
        String searchString = sc.nextLine();
        if (searchString.equals("price")) {
            System.out.println(searchString);

        } else {
            ArrayList<Product> searchResult = getSearchResult(products, searchString);

            if (searchResult.isEmpty())
                System.out.println("""
                        There is no match based on your search string
                        Please double check your spelling or choose another criteria""");
            else {
                System.out.println("Search result:");
                searchResult.forEach(System.out::println);
            }
        }
    }

    private static ArrayList<Product> getSearchResult(ArrayList<Product> products, String searchString) {
        return (ArrayList<Product>) products.stream()
                .filter(product -> searchString.equals(String.valueOf(product.getProductID())) ||
                           searchString.equals(String.valueOf(product.getName())) ||
                           searchString.equals(String.valueOf(product.getCategory())) ||
                           searchString.equals(String.valueOf(product.getPrice())) ||
                           searchString.equals(String.valueOf(product.getBrand())))
                .collect(Collectors.toList());
    }

    static void addNewCategory(ArrayList<Category> categoryList, Scanner sc) {
        System.out.println("Insert the name of the new category:");
        categoryList.add(new Category(sc.nextLine()));
    }

    static void addProduct(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        Menu.printProductMenu(categoryList);
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

    static void addNewProduct(int choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("To add a new product in this category (" + categoryList.get(choice).toString() +
                "), \nyou need to fill in the following information:");

        String name = setAndCompareName(sc, products);
        BigDecimal price = setPrice(sc);
        String brand = setBrand(sc);
        int productID = setAndCompareProductID(sc, products);
        int stock = setStock(sc);

        products.add(new Product(name, price, categoryList.get(choice), brand, productID, stock));
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
                printDuplicateProductMessage();
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
                printDuplicateProductMessage();
            }
        } while (possibleDuplicateProductID.isPresent());
        return productID;
    }

    private static void printDuplicateProductMessage() {
        System.out.println("This product already exists, please try again");
    }

    private static String setName(Scanner sc) {
        return sc.nextLine();
    }

    private static String setBrand(Scanner sc) {
        System.out.print("Brand: ");
        return sc.nextLine();
    }

    private static int setProductID(Scanner sc) {
        int productID = sc.nextInt();
        sc.nextLine();
        return productID;
    }

    private static BigDecimal setPrice(Scanner sc) {
        System.out.print("Price: ");
        return new BigDecimal(sc.nextLine());
    }

    static Integer setStock(Scanner sc) {
        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();
        return stock;
    }


    static void productsBalance(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        products.forEach(System.out::println);
    }

    static void deleteCategory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        Menu.printRemoveCategoryMenu(categoryList, products);
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

    private static void removeChosenCategory(int choiceNumber, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("The chosen category is now deleted");
        categoryList.remove(choiceNumber);
    }

    static void printAllProducts(ArrayList<Product> products, ArrayList<Category> categoryList) {
        System.out.println("Name" + LineUp.lineUpName(4) +
                "| Price" + LineUp.lineUpPrice(5) +
                "| Category" + LineUp.lineUpCategory(8) +
                "| Brand" + LineUp.lineUpBrand(5) +
                "| ProductID" + LineUp.lineUpProductID(9) +
                "| Stock");
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
        Menu.printRemoveProductMenu(products);
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
    }


    static void addToCart(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {

    }

    static void showCart(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {

    }

    static void editCart(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {

    }

    static void toCheckout(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {

    }
}



