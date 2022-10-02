

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
    Fundera igenom vilka klasser som kan behövas för att implementera följande:

    Varor som kan innehålla:
        1. Namn
        2. Pris
        3. Vilken/vilka kategori det tillhör
        4. Varumärket
        5. ean-kod

    Kategorier kommer variera med vilken typ av varor vårt system ska kunna hantera och ska vara
    flexibelt.

    Det ska gå att skapa nya kategorier i framtiden.


    Hantera lagersaldo för produkter där jag kan se vilka olika varor som finns samt antal av dessa

    Söka efter varor inom ett specifikt prisintervall, kategori mm (Java Streams)

    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var categoryList = new ArrayList<Category>();

        categoryList.add(new Category("Put & Approach"));
        categoryList.add(new Category("Midrange"));
        categoryList.add(new Category("Fairway Driver"));
        categoryList.add(new Category("Distance Driver"));
        var inventory = new ArrayList<Product>();
        menu(sc, categoryList, inventory);
    }

    private static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        String choice;
        do {
            printMenu();
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchMenu(choice, sc, categoryList, inventory);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        switch (choice) {
            case "1" -> product(sc, categoryList, inventory);
            case "2" -> category(sc, categoryList, inventory);
            case "3" -> inventoryBalance(sc, categoryList, inventory);
            case "4" -> search();
            case "e" -> quit();
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
                e. Quit
                """);
    }

    private static void product(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        String choice;
        do {
            printProductMenu();
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchProduct(choice, sc, categoryList, inventory);
        } while (!choice.equals("e"));
    }

    private static void printProductMenu() {
        System.out.println("""
                            
                Product Menu
                ============
                1. Show products
                2. Add product
                3. Remove product
                e. Main menu
                """);
    }

    private static void switchProduct(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        switch (choice) {
//            case "1" -> showProduct();
            case "2" -> addProduct(choice, sc, categoryList, inventory);
//            case "3" -> removeProduct();
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void addProduct(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        printAvailableCategories(categoryList);
        choice = sc.nextLine();
        choice = choice.toLowerCase();
        switchAddNewProduct(choice, sc, categoryList, inventory);

    }

    private static void switchAddNewProduct(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        switch (choice) {
            case "1" -> addNewProduct(0, sc, categoryList, inventory);
            case "2" -> addNewProduct(1, sc, categoryList, inventory);
            case "3" -> addNewProduct(2, sc, categoryList, inventory);
            case "4" -> addNewProduct(3, sc, categoryList, inventory);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void printAvailableCategories(ArrayList<Category> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
        System.out.println("e. Quit");
    }

    private static void addNewProduct(int choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        System.out.println("To add a new product in this category (" + categoryList.get(choice).toString() + "), the following information is needed:");
        System.out.println("""
                Product name
                Price
                Brand
                ProductID
                """);
        System.out.println("What is the name of the product?");
        String name = sc.nextLine();
        System.out.println("Insert the price of the product with two (2) decimals:");
        Double price = sc.nextDouble();
        System.out.println("What is the brand?");
        String brand = sc.nextLine();
        System.out.println("Fill in the productID:");
        String productID = sc.nextLine();
        inventory.add(new Product(name, price, categoryList.get(choice), brand, productID));
    }

    public static ArrayList<Category> category(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        String choice;

        do {
            printCategoryMenu(categoryList);
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchCategories(categoryList, choice, sc);
        } while (!choice.equals("e"));
        return categoryList;
    }

    private static void printCategoryMenu(ArrayList<Category> categoryList) {
        System.out.println("""
                            
                Category Menu
                =============
                1. Add category
                2. Print categories
                e. Main menu
                """);
    }

    private static void printCategories(ArrayList<Category> list) {
        if (list.size() == 0) {
            System.out.println("Please create a category before you print it");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void switchCategories(ArrayList<Category> categoryList, String choice, Scanner sc) {
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

    private static void inventoryBalance(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        System.out.println(inventory.size());
        System.out.println(inventory.toString());
    }

    private static void search() {
    }

    private static void quit() {
        System.out.println("Programmet avslutas, välkommen åter");
    }


}