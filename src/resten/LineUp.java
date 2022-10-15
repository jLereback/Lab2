package resten;

public abstract class LineUp {
    public static String name(int length) {
        if (length < 4)
            return "\t\t ";
        else if (length < 8)
            return "\t ";
        else
            return " ";
    }

    public static String price(int length) {
        if (length < 4)
            return "\t\t";
        else if (length < 8)
            return "\t";
        else
            return "";
    }

    public static String category(int length) {
        if (length < 5)
            return "\t\t";
        else if (length < 9)
            return "\t";
        else
            return " ";
    }

    public static String brand(int length) {
        if (length < 6)
            return "\t".repeat(Math.max(0, 3));

        else if (length < 10)
            return "\t".repeat(Math.max(0, 2));

        else
            return "\t";
    }

    public static String productID(int length) {
        if (length < 6)
            return "\t\t";
        else if (length < 10)
            return "\t";
        else
            return " ";
    }

    public static String withTab(int timesOfTab){
        return "\t".repeat(Math.max(0, timesOfTab));
    }

}

