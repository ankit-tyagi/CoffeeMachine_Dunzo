public class BeverageRecipeNotFoundException extends RuntimeException {
    public BeverageRecipeNotFoundException(String beverageName) {
        super("Recipe for " + beverageName + " not found");
    }
}
