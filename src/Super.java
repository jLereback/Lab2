import java.util.ArrayList;
import java.util.Scanner;

public abstract class Super {

    static void printCategories(ArrayList<Category> categoryList) {
        if (categoryList.size() == 0) {
            System.out.println("Please create a category before you print it");
        } else {
            categoryList.forEach(System.out::println);
        }
    }

    static void showProduct(ArrayList<Product> products) {
        if (products.size() == 0)
            System.out.println("Please add a product before you print it");
        else
            for (Product product : products) {
                System.out.println(product);
            }
    }

    static void printProductsInCategory(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {

        int choiceNumber = (Integer.parseInt(choice) - 1);
        Category categoryName = categoryList.get(choiceNumber);
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
        Menu.printProductMenu(categoryList);
        String choice = sc.nextLine();

        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                addNewProduct((Integer.parseInt(choice) - 1), sc, categoryList, products);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
            addProduct(sc, categoryList, products);
        }
    }

    static void addNewProduct(int choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println("To add a new product in this category (" + categoryList.get(choice).toString() +
                "), \nyou need to fill in the following information:");

        String name = getInfo("Name: ", sc);
        Double price = getPrice("Price: ", sc);
        sc.nextLine();
        String brand = getInfo("Brand: ", sc);
        String productID = getInfo("Product ID: ", sc);
        products.add(new Product(name, price, categoryList.get(choice), brand, productID));
    }

    static String getInfo(String s, Scanner sc) {
        System.out.print(s);
        return sc.nextLine();
    }

    static Double getPrice(String s, Scanner sc) {
        System.out.print(s);
        return sc.nextDouble();
    }

    static void productsBalance(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        System.out.println(products.size());
        products.forEach(System.out::println);
    }

    static void deleteCategory(ArrayList<Category> categoryList) {

    }

    static void switchProduct(String choice, Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        switch (choice) {
            case "1" -> showProduct(products);
            case "2" -> addProduct(sc, categoryList, products);
            case "3" -> removeProduct();
            case "e" -> System.out.println("Going back to previous menu");
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    static void printAllProducts(ArrayList<Product> products) {
        for (Product product : products) {
            System.out.println(product.printProducts());
        }
    }

    static void chooseCategory(Scanner sc, ArrayList<Category> categoryList, ArrayList<Product> products) {
        Menu.printProductMenu(categoryList);
        String choice = sc.nextLine();

        try {
            if (choice.equals("e"))
                System.out.println("Going back to previous menu");
            else if ((Integer.parseInt(choice) <= categoryList.size()))
                printProductsInCategory(choice, sc, categoryList, products);
            addNewProduct((Integer.parseInt(choice) - 1), sc, categoryList, products);
        } catch (NumberFormatException e) {
            System.out.println("Please choose one of the alternatives below:");
            addProduct(sc, categoryList, products);
        }
    }

    static void removeProduct() {

    }

}
