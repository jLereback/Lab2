import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer {
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
            case "1" -> showProduct(products);
            case "2" -> printCategories(categoryList);
            case "3" -> printProductsInCategory(sc, categoryList, categoryProductHashMap);
            case "4" -> search();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void printProductsInCategory(Scanner sc, ArrayList<Category> categoryList, HashMap<Product, Category> categoryProductHashMap) {
        categoryList.forEach(System.out::println);


        System.out.println("In what category would you like to see the products?");
/*        categoryProductHashMap.put();





        String choice = sc.nextLine();*/

/*
        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                addNewProduct((Integer.parseInt(choice) - 1), sc, categoryList, products, categoryProductHashMap);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
*/



/*        categoryProductHashMap.containsKey();*/


        System.out.println(categoryProductHashMap);
    }

    private static void printMenu() {
        System.out.println("""
                
                Disc Shop
                =========
                1. Show products
                2. Show categories
                3. Search product
                e. Switch user
                """);
    }

    private static void showProduct(ArrayList<Product> products) {
        if (products.size() == 0) {
            System.out.println("""
                    There is no products available in this shop at the moment
                    Please come back later""");
        } else {
            for (Product product : products) {
                System.out.println(product.printProducts());
            }
        }
    }

    private static void printCategories(ArrayList<Category> categoryList) {
        if (categoryList.size() == 0) {
            System.out.println("Please create a category before you print it");
        } else {
            categoryList.forEach(System.out::println);
        }
    }

    private static void search() {
    }
}
