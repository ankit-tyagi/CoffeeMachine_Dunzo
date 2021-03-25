public class InsufficientIngredientException extends RuntimeException {
    String name;
    String status;
    public InsufficientIngredientException(String name, String status) {
        super("Ingredient " + name + " is " + status);
        this.name = name;
        this.status = status;
    }
}
