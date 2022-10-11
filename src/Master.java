import java.util.ArrayList;
import java.util.Scanner;

public class Master extends Super {
    static void menu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printMasterMenu();
            choice = sc.nextLine().toLowerCase();
            switchMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> inventory(sc, categoryList, products);
            case "2" -> category(sc, categoryList, products);
            case "3" -> search(sc, categoryList, products);
            case "4" -> showShopMenu(sc, categoryList, products);
            case "e" -> Menu.quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void inventory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printMasterInventoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchInventory(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }


    private static void switchInventory(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> printInventory(sc, categoryList, products);
            case "2" -> addProduct(sc, categoryList, products);
            case "3" -> editStock(sc, categoryList, products);
            case "4" -> removeProduct(sc, categoryList, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void editStock(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0)
            System.out.println("""
                    Please add a product before you edit it""");
        else
            chooseProduct(sc, products);
    }

    private static void chooseProduct(Scanner sc, ArrayList<Product> products) {
        String choice;
        Menu.printEdibleProductMenu(sc, products);
        choice = sc.nextLine();
        chooseSpecificProduct(choice, sc, products);
    }

    private static void chooseSpecificProduct(String choice, Scanner sc, ArrayList<Product> products) {
        if (choice.equals("e"))
            System.out.println("Going back to previous menu");
        else if ((Integer.parseInt(choice) <= products.size())) {
            int chosenProduct = (Integer.parseInt(choice) - 1);
            Menu.editChosenProduct(choice, products);
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
        askForNewStock(choice);
        products.get(chosenProduct).editStock(products.get(chosenProduct).getStock() + sc.nextInt());
        sc.nextLine();
        printNewStock(chosenProduct, products);
    }

    private static void decreaseStock(Scanner sc, String choice, int chosenProduct, ArrayList<Product> products) {
        askForNewStock(choice);
        int decreasingStock = sc.nextInt();
        sc.nextLine();
        if (products.get(chosenProduct).getStock() - decreasingStock < 1) {
            System.out.println("""
                    You need at least one piece of
                    the specified product in your inventory
                    """);
            decreaseStock(sc, choice, chosenProduct, products);
        }
        else {
            products.get(chosenProduct).editStock(products.get(chosenProduct).getStock() - decreasingStock);
            printNewStock(chosenProduct, products);
        }
    }

    private static void askForNewStock(String choice) {
        System.out.println("How much would you like to " + increaseOrDecrease(choice) + " the stock?");
    }

    private static String increaseOrDecrease(String choice) {
        if (choice.equals("1"))
            return "increase";
        else
            return "decrease";
    }

    private static void printNewStock(int chosenProduct, ArrayList<Product> products) {
        System.out.println("The new stock for " + products.get(chosenProduct).getName() + " is: " + products.get(chosenProduct).getStock());
    }


    private static void printInventory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        if (products.size() == 0) {
            System.out.println("""
                    Please add a product before you print it""");
        } else {
            Menu.printChosenMenu();
            String choice = sc.nextLine().toLowerCase();
            switchProductMenu(choice, sc, categoryList, products);
        }
    }

    private static void showShopMenu(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printShopMenu();
            choice = sc.nextLine().toLowerCase();
            switchShopMenu(choice, sc, categoryList, products);
        } while (!choice.equals("e"));
    }

    //                Shop Menu
    //                =========
    //                1. Add to cart
    //                2. Show cart
    //                3. Edit cart
    //                4. Checkout
    //                e. Main Menu

    private static void switchShopMenu(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> addToCart(sc, categoryList, products);
            case "2" -> showCart(sc, categoryList, products);
            case "3" -> editCart(sc, categoryList, products);
            case "4" -> toCheckout(sc, categoryList, products);
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    public static void category(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        String choice;
        do {
            Menu.printMasterCategoryMenu();
            choice = sc.nextLine().toLowerCase();
            switchCategories(categoryList, choice, sc, products);
        } while (!choice.equals("e"));
    }

    private static void switchCategories(ArrayList<Category> categoryList, String choice, Scanner sc, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> addNewCategory(categoryList, sc);
            case "2" -> printCategories(categoryList);
            case "3" -> deleteCategory(sc, categoryList, products);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }
}