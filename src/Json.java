
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Json {
    static void saveToFile(List<Product> products) {
        Gson gson = new Gson();

        String json = gson.toJson(products);

        try {
            Files.writeString(Path.of("src/products.json"), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static List<Product> readFromFile() throws IOException {

        return new Gson().fromJson(Files.readString(Path.of("src/products.json")), new TypeToken<ArrayList<Product>>() {}.getType());
    }
}
