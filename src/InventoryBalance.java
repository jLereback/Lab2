import java.util.ArrayList;
import java.util.Scanner;

public class InventoryBalance {
    ArrayList<Product> productList;
    public InventoryBalance(Scanner sc, ArrayList<Category> categoryList) {

    }
    /*
     Todo; Vill jag använda @override toString() för att skriva ut innehållet i arrayListorna?
    */


    public void printBalance() {
        for (Product i : productList) {
            System.out.println(i);
        }
    }
}