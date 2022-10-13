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

    static void forNewStock(String choice) {
        System.out.println("How much would you like to " + Super.increaseOrDecrease(choice) + " the stock?");
    }


}
