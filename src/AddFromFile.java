import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddFromFile {
    public static Product addFromFile(ArrayList<Category> categoryList) {
        String homePath = System.getProperty("src");

        Pattern pattern = Pattern.compile(",");

        try (Stream<String> lines = Files.lines(Path.of( "src/products.csv"))) {


            ArrayList<Product> productList = (ArrayList<Product>) lines.map(line -> {
                String[] arr = pattern.split(line);


                System.out.println(Arrays.toString(arr));

                Category theCategory = getCategory(categoryList, arr);
                return new Product(
                        arr[0],
                        new BigDecimal(arr[1]),
                        theCategory,
                        arr[3],
                        Integer.parseInt(arr[4]),
                        Integer.parseInt(arr[5]));
            }).toList();


            productList.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static Category getCategory(ArrayList<Category> categoryList, String[] arr) {
        categoryList.add(new Category(arr[2]));
        Category categoryName = categoryList.get(categoryList.size() - 1);
        int indexOfCategory = categoryList.indexOf(categoryName);
        return categoryList.get(indexOfCategory);


    }
}
