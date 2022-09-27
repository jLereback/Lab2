import java.util.ArrayList;
import java.util.Scanner;

public class InventoryBalance {
    ArrayList<Product> list;
    public InventoryBalance(Scanner sc) {
        list.add(new Product("Fuse", 179, Main.addCategory(sc), "Latitude 64", 111));
        list.add(new Product("Diamond", 189, Main.addCategory(sc), "Latitude 64", 112));

    }
    /*
    Todo; Lägga in en dynamisk switch för att få upp olika alternativ när man lägger till en produkt
     och ska välja kategori till den specifika produkten
     Switch-satsen skapas med hjälp av en for-loop där Case "i" = categoryList.get(i)
     De olika alternativen kan vara:
     1. Putt & Approach
     2. Midrange
     3. Fairway Driver
     4. Distance Driver
     a. Add new category
     På add new category så skriver man in namnet på den nya kategorin och den läggs till i Arraylistan för de olika kategorierna
    */


    public void printBalance() {
        for (Product i : list) {
            System.out.println(i);
        }
    }
}