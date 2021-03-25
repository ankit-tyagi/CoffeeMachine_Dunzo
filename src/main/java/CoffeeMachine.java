import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {

    private int n;
    private Map<Ingredient, Integer> ingredientQuantity;
    private Map<String, Beverage> beverageByNameMap;

    public CoffeeMachine(int n) {
        this.n = n;
        this.ingredientQuantity = new HashMap<>();
    }

    public void addIngredients(String ingredientName, Integer quantity) {

        this.ingredientQuantity.put(new Ingredient(ingredientName), quantity);
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
