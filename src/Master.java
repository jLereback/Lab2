import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Master {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        String choice;
        do {
            printMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products, categoryProductHashMap);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        switch (choice) {
            case "1" -> product(sc, categoryList, products, categoryProductHashMap);
            case "2" -> category(sc, categoryList, products, categoryProductHashMap);
            case "3" -> productsBalance(sc, categoryList, products, categoryProductHashMap);
            case "4" -> search();
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void printMenu() {
        System.out.println("""
                            
                Disc Shop
                =========
                1. Product
                2. Categories
                3. Inventory balance
                4. Search product
                e. Switch user
                """);
    }

    private static void product(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        String choice;
        do {
            printProductMenu();
            choice = sc.nextLine().toLowerCase();
            switchProduct(choice, sc, categoryList, products, categoryProductHashMap);
        } while (!choice.equals("e"));
    }

    private static void printProductMenu() {
        System.out.println("""
                            
                Product Menu
                ============
                1. Show products
                2. Add product
                3. Remove product
                e. Back to Main menu
                """);
    }

    private static void switchProduct(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        switch (choice) {
            case "1" -> showProduct(categoryProductHashMap);
            case "2" -> addProduct(sc, categoryList, categoryProductHashMap);
//            case "3" -> removeProduct();
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void showProduct(HashMap<Product, Category> categoryProductHashMap) {
        if (categoryProductHashMap.size() == 0) {
            System.out.println("Please add a product before you print it");
        } else {
            for (Entry<Product, Category> entry : categoryProductHashMap.entrySet()) {
                System.out.println(entry.getKey());
            }
        }
    }

    private static void addProduct(Scanner sc, ArrayList<Category> categoryList, HashMap<Product, Category> categoryProductHashMap) {
        printAddProductMenu(categoryList);
        String choice = sc.nextLine();

        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                addNewProduct((Integer.parseInt(choice) - 1), sc, categoryList, categoryProductHashMap);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
            addProduct(sc, categoryList, categoryProductHashMap);
        }
    }

    private static void printAddProductMenu(ArrayList<Category> categoryList) {
        System.out.println("In which category would you like to add the product?");
        for (int i = 0; i < categoryList.size(); i++) {
            /*            System.out.println((i + 1) + ". " + categoryList.get(i).toString());*/
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
        System.out.println("e. Back to Product menu");
    }

    private static void addNewProduct(int choice, Scanner sc, ArrayList<Category> categoryList, HashMap<Product, Category> products) {
        System.out.println("To add a new product in this category (" + categoryList.get(choice).toString() +
                "), \nyou need to fill in the following information:");

        String name = getInfo("Name: ", sc);
        Double price = getPrice("Price: ", sc);
        sc.nextLine();
        String brand = getInfo("Brand: ", sc);
        String productID = getInfo("Product ID: ", sc);
        products.put(new Product(name, price, brand, productID), categoryList.get(choice));

    }

    private static String getInfo(String s, Scanner sc) {
        System.out.print(s);
        return sc.nextLine();
    }

    private static Double getPrice(String s, Scanner sc) {
        System.out.print(s);
        return sc.nextDouble();
    }


    public static void category(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        String choice;

        do {
            printCategoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchCategories(categoryList, choice, sc, products);
        } while (!choice.equals("e"));
    }

    private static void printCategoryMenu() {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                e. Back to Main menu
                """);
    }

    private static void printCategories(ArrayList<Category> list) {
        if (list.size() == 0) {
            System.out.println("Please create a category before you print it");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void switchCategories(ArrayList<Category> categoryList, String choice, Scanner sc, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> addNewCategory(categoryList, sc);
            case "2" -> printCategories(categoryList);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void addNewCategory(ArrayList<Category> categoryList, Scanner sc) {
        System.out.println("Insert the name of the new category:");
        categoryList.add(new Category(sc.nextLine()));
    }

    private static void productsBalance(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products, HashMap<Product, Category> categoryProductHashMap) {
        System.out.println(products.size());
        products.forEach(System.out::println);
    }

    private static void search() {
    }
}