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

        try {
            Recipe recipe = this.beverageCatalogue.getRecipeForBeverage(beverageName);
            this.ingredientReserve.dispenseIngredientsForRecipe(recipe);
            makeBeverage(beverageName);
        } catch (BeverageRecipeNotFoundException ex){
            System.out.println("Recipe for " + beverageName + " not found");
        } catch (InsufficientIngredientException ex){
            System.out.println(beverageName + " cannot be prepared because " + ex.name + " is " + ex.status);
        }
    }

    private void makeBeverage(String beverageName) {
        System.out.println(beverageName + " is prepared");
    }
}
