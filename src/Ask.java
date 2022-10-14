public class Ask {

    static void forCategoryToUse() {
        System.out.println("""
                                
                Which category would you like to use?""");
    }

    static void forCategoryToRemove() {
        System.out.println("""
                                
                Which category would you like to remove?
                """);
    }

    static void forProductToEdit() {
        System.out.println("""
                                
                What product would you like to edit?""");
    }

    static void forNewStockOrAmount(String choice, String type) {
        System.out.printf("How much would you like to %s the %s?\n", Super.increaseOrDecrease(choice), type);
    }
    static void forCategoryToPrint() {
        System.out.println("""
                                
                Would you like to see products from all
                categories or products from a specific one?""");
    }

}