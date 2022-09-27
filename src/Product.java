import java.util.ArrayList;

public record Product(String name, int price, ArrayList<Category> category, String brand, int eanCode) {

}
