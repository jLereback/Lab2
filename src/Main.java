

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryBalance i = new InventoryBalance(sc);
        menu(sc);
    }

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

    private static void menu(Scanner sc) {
        String choice;
        do {
            printMenu();
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchMenu(choice, sc);
        } while (!choice.equals("e"));
    }

    private static void switchMenu(String choice, Scanner sc) {
        switch (choice) {
            case "1" -> product();
            case "2" -> addCategory(sc);
            case "3" -> inventoryBalance();
            case "4" -> search();
            case "e" -> quit();
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void printMenu() {
        System.out.println("""
                            
                Shop
                ========
                1. Product
                2. Categories
                3. Inventory balance
                4. Search product
                e. Quit
                """);
    }

    private static void product() {
    }

    public static ArrayList<Category> addCategory(Scanner sc) {
        String choice;
        var categoryList = new ArrayList<Category>();
        do {
            printCategories(categoryList);
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            switchCategories(categoryList, choice, sc);
        } while (!choice.equals("e"));
        return categoryList;
    }

    private static void printCategories(ArrayList<Category> list) {
        for (Category i : list) {
            System.out.println(i);
        }
    }

    private static void switchCategories(ArrayList<Category> categoryList, String choice, Scanner sc) {
        switch (choice) {
            case "1" -> product();
            case "2" -> addCategory(sc);
            case "3" -> inventoryBalance();
            case "c" -> addNewCategory(categoryList, sc);
            default -> System.out.println("Please choose one of the alternatives below:");
        }
    }

    private static void addNewCategory(ArrayList<Category> categoryList, Scanner sc) {
        categoryList.add(new Category(sc.nextLine()));
    }

    private static void inventoryBalance() {
    }

    private static void search() {
    }

    private static void quit() {
        System.out.println("Programmet avslutas, välkommen åter");
    }
}