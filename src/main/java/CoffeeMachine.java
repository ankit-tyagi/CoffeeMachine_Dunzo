import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class CoffeeMachine {

    private int n;
    private IngredientReserve ingredientReserve;
    private Map<String, Beverage> beverageByNameMap;

    public CoffeeMachine(int n, IngredientReserve ingredientReserve) {
        this.n = n;
        this.ingredientReserve = ingredientReserve;
    }

    public void fillIngredient(String ingredientName, Integer quantity) {
        this.ingredientReserve.fillIngredient(new Ingredient(ingredientName), quantity);
    }

    public void addRecipe(String beverageName, List<Pair<String, Integer>> ingredients) {

        Recipe.RecipeBuilder recipeBuilder = Recipe.builder();
        for(Pair<String, Integer> ingredient: ingredients) {
            recipeBuilder.addIngredient(new Ingredient(ingredient.getKey()), ingredient.getValue());
        }

        Beverage beverage = new Beverage(beverageName, recipeBuilder.build());

        this.beverageByNameMap.put(beverageName, beverage);
    }

    public void serveBeverage(String beverageName) {
        // TODO
    }
}
