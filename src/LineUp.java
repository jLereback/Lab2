public abstract class LineUp {
    static String lineUpName(int length) {
        if (length < 4)
            return "\t\t ";
        else if (length < 8)
            return "\t ";
        else
            return " ";
    }

    static String lineUpPrice(int length) {
        if (length < 4)
            return "\t\t";
        else if (length < 8)
            return "\t";
        else
            return "";
    }

    static String lineUpCategory(int length) {
        if (length < 5)
            return "\t\t";
        else if (length < 9)
            return "\t";
        else
            return " ";
    }

    static String lineUpBrand(int length) {
        if (length < 6)
            return "\t\t\t";
        else if (length < 10)
            return "\t\t";
        else
            return "\t";
    }

    static String lineUpProductID(int length) {
        if (length < 6)
            return "\t\t";
        else if (length < 10)
            return "\t";
        else
            return " ";
    }
}
