import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        String choice;
        do {
            printMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, inventory);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        switch (choice) {
            case "1" -> product(sc, categoryList, inventory);
            case "2" -> category(sc, categoryList, inventory);
            case "3" -> inventoryBalance(sc, categoryList, inventory);
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
            case "1" -> showProduct(inventory);
            case "2" -> addProduct(sc, categoryList, inventory);
//            case "3" -> removeProduct();
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void showProduct(ArrayList<Product> inventory) {
        if (inventory.size() == 0) {
            System.out.println("Please add a product before you print it");
        } else {
            for (Product product : inventory) {
                System.out.println(product.printProducts());
            }
        }
    }

    private static void addProduct(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        printAvailableCategories(categoryList);
        String choice = sc.nextLine();

        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                addNewProduct((Integer.parseInt(choice) - 1), sc, categoryList, inventory);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
            addProduct(sc, categoryList, inventory);
        }
    }

    private static void printAvailableCategories(ArrayList<Category> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println((i + 1) + ". " + categoryList.get(i).toString());
        }
        System.out.println("e. Quit");
    }

    private static void addNewProduct(int choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        System.out.println("To add a new product in this category (" + categoryList.get(choice).toString()
                + "), \nyou need to fill in the following information:");

        System.out.print("Product name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        Double price = sc.nextDouble();
        System.out.print("Brand: ");
        String brand = sc.nextLine();
        System.out.print("Product ID: ");
        String productID = sc.nextLine();
        inventory.add(new Product(name, price, categoryList.get(choice), brand, productID));
    }

    public static void category(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> inventory) {
        String choice;

        do {
            printCategoryMenu(categoryList);
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchCategories(categoryList, choice, sc);
        } while (!choice.equals("e"));

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
        inventory.forEach(System.out::println);
    }

    private static void search() {
    }



}
