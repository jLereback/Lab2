package files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import inventory.Category;
import inventory.Product;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


import java.io.IOException;
import java.nio.file.Path;

public class Files {
    static Path productPath = Path.of("src/Files/products.json");
    static Path categoryPath = Path.of("src/Files/categories.json");
    static Path receiptPath = Path.of("src/Files/receipt.txt");

    public static void exportProductsToFile(List<Product> products) {
        Gson gson = new Gson();

        String json = gson.toJson(products);

        try {
            java.nio.file.Files.writeString(productPath, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> importProductsFromFile() throws IOException {

        return new Gson().fromJson(java.nio.file.Files.readString(productPath),
                new TypeToken<ArrayList<Product>>() {
                }.getType());
    }

    public static void exportCategoryListToFile(List<Category> categoryList) {
        Gson gson = new Gson();

        String json = gson.toJson(categoryList);

        try {
            java.nio.file.Files.writeString(categoryPath, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Category> importCategoryListFromFile() throws IOException {

        return new Gson().fromJson(java.nio.file.Files.readString(categoryPath),
                new TypeToken<ArrayList<Category>>() {
                }.getType());
    }

    public static void exportReceipt(String receipt) {
        try {
            java.nio.file.Files.writeString(receiptPath, receipt, StandardOpenOption.APPEND);
            System.out.println(receipt);
            System.out.printf("You can find the receipt in the following path: %s\n", receiptPath);


        } catch (IOException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}