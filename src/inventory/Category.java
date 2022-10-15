package inventory;

import java.util.Objects;

public final class Category {
    private final String category;

    public Category(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

    public String category() {
        return category;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Category) obj;
        return Objects.equals(this.category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }


    public String toLowerCase() {
        return category.toLowerCase();
    }
}