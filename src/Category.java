import java.util.Scanner;

public record Category(String category) {
    @Override
    public String toString() {
        return category;
    }
}
