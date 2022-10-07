import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


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
//        do {
        Menu.printProductMenu(categoryList);
        choice = sc.nextLine();
        chooseSpecificCategory(choice, sc, categoryList, products);
//        } while (!choice.equals("e"));
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
        System.out.println("Name " + lineUpName(4) +
                "| price " + lineUpPrice(5) +
                "| category " + lineUpCategory(8) +
                "| brand " + lineUpBrand(5) +
                "| productID " + lineUpProductID(9) +
                "| stock");
        products.stream()
                .filter(product -> product.category().equals(categoryName))
                .forEach(System.out::println);
    }

    static void search() {
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

        String name = setInfo("Name: ", sc);
        BigDecimal price = setPrice("Price: ", sc);
        String brand = setInfo("Brand: ", sc);
        String productID = setInfo("Product ID: ", sc);
        int stock = setStock("Stock: ", sc);
        sc.nextLine();
        products.add(new Product(name, price, categoryList.get(choice), brand, productID, stock));
    }

    private static BigDecimal setPrice(String s, Scanner sc) {
        System.out.print(s);
        return new BigDecimal(sc.nextLine());
    }

    static String setInfo(String s, Scanner sc) {
        System.out.print(s);
        return sc.nextLine();
    }

    static Integer setStock(String s, Scanner sc) {
        System.out.print(s);
        return sc.nextInt();
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
        System.out.println("The chosen product is now deleted");
        categoryList.remove(choiceNumber);
    }

    static void printAllProducts(ArrayList<Product> products, ArrayList<Category> categoryList) {
        System.out.printf("Name " + lineUpName(4) +
                "| price " + lineUpPrice(5) +
                "| category " + lineUpCategory(8) +
                "| brand " + lineUpBrand(5) +
                "| productID " + lineUpProductID(9) +
                "| stock");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    static String lineUpName(int length) {
        for (int i = 0; i < 12 - length; i++)
            System.out.print("");
        return " ";
    }

    static String lineUpCategory(int length) {
        for (int i = 0; i < 18 - length; i++)
            System.out.print("");
        return " ";
    }

    static String lineUpPrice(int length) {
        for (int i = 0; i < 6 - length; i++)
            System.out.print("");
        return " ";
    }

    static String lineUpBrand(int length) {
        for (int i = 0; i < 18 - length; i++)
            System.out.print("");
        return " ";
    }

    static String lineUpProductID(int length) {
        for (int i = 0; i < 6 - length; i++)
            System.out.print("");
        return " ";
    }


    static void removeProduct(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0)
            System.out.println("Please add a product before you remove it");
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
