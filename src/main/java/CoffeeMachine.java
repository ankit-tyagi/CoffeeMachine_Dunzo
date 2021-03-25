import javafx.util.Pair;

import java.util.List;

public class CoffeeMachine {

    private final int n;
    private final IngredientReserve ingredientReserve;
    private final BeverageCatalogue beverageCatalogue;

    public CoffeeMachine(int n, IngredientReserve ingredientReserve, BeverageCatalogue beverageCatalogue) {
        this.n = n;
        this.ingredientReserve = ingredientReserve;
        this.beverageCatalogue = beverageCatalogue;
    }

    public void fillIngredient(String ingredientName, Integer quantity) {
        this.ingredientReserve.fillIngredient(new Ingredient(ingredientName), quantity);
    }

   public void addNewBeverage(String beverageName, List<Pair<String, Integer>> ingredients) {
        this.beverageCatalogue.registerNewBeverage(beverageName, ingredients);
   }

    public void serveBeverage(String beverageName) {
        // TODO
    }
}
