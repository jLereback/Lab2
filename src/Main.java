

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
Todo: Fundera igenom vilka klasser som kan behövas för att implementera följande:
     Varor som kan innehålla:
        1. Namn
        2. Pris
        3. Vilken/vilka kategori det tillhör
        4. Varumärket
        5. ean-kod
    - Kategorier kommer variera med vilken typ av varor vårt system ska kunna hantera och ska vara
    flexibelt.
    - Det ska gå att skapa nya kategorier i framtiden.
    - Hantera lagersaldo för produkter där jag kan se vilka olika varor som finns samt antal av dessa
    - Söka efter varor inom ett specifikt prisintervall, kategori mm (Java Streams)
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var categoryList = new ArrayList<Category>();

        categoryList.add(new Category("Put & Approach"));
        categoryList.add(new Category("Midrange"));
        categoryList.add(new Category("Fairway Driver"));
        categoryList.add(new Category("Distance Driver"));
        var inventory = new ArrayList<Product>();
        Start.menu(sc, categoryList, inventory);

    }
}