import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AddFromFile {
    public static void addFromFile(ArrayList<Category> categoryList) {
        String homePath = System.getProperty("scr");

        Pattern pattern = Pattern.compile(",");

        try (Stream<String> lines = Files.lines(Path.of(homePath, "products.csv"))) {

            ArrayList<Product> productList = (ArrayList<Product>) lines.map(line -> {
                String[] arr = pattern.split(line);
                System.out.println(Arrays.toString(arr));
                return new Product(
                        arr[0],
                        new BigDecimal(arr[1]),
                        categoryList.get(categoryList.indexOf(arr[2])),
                        arr[3],
                        Integer.parseInt(arr[4]),
                        Integer.parseInt(arr[5]));
            }).toList();

            productList.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
