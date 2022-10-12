
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Json {
    private static void saveToFile(ArrayList<Category> categoryList) {
        Gson gson = new Gson();

        ArrayList<Product> discList = new ArrayList<>();

        discList.add(new Product("Pure", new BigDecimal(17), categoryList.get(0), "Latitude 64", 90210, 3));
        discList.add(new Product("Compass", new BigDecimal(19), categoryList.get(1), "Latitude 64", 90211, 6));
        discList.add(new Product("Sapphire", new BigDecimal(27), categoryList.get(2), "Latitude 64", 90212, 1));
        discList.add(new Product("Wraith", new BigDecimal(23), categoryList.get(2), "Innova", 90310, 4));
        String json = gson.toJson(discList);

        try {
            Files.writeString(Path.of("src/products.json"), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static ArrayList<Product> readFromFile() throws IOException {

        return new Gson().fromJson(Files.readString(Path.of("src/products.json")), new TypeToken<ArrayList<Product>>() {}.getType());
    }
}
