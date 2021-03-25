package beverages;

import exceptions.BeverageNotFoundException;
import ingredients.Ingredient;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeverageCatalogue {

    private final Map<String, Beverage> beverageByName;

    public BeverageCatalogue()
    {
        this.beverageByName = new HashMap<>();
    }

    public void registerNewBeverage(String beverageName, List<Pair<String, Integer>> ingredients) {

        Recipe.RecipeBuilder recipeBuilder = Recipe.builder();
        for(Pair<String, Integer> ingredient: ingredients) {
            recipeBuilder.addIngredient(new Ingredient(ingredient.getKey()), ingredient.getValue());
        }
        Beverage beverage = new Beverage(beverageName, recipeBuilder.build());

        this.beverageByName.put(beverageName, beverage);
    }

    public Recipe getRecipeForBeverage(String beverageName) {

        if (beverageByName.containsKey(beverageName)) {
            return this.beverageByName.get(beverageName).getRecipe();
        } else {
            throw new BeverageNotFoundException(beverageName);
        }
    }
}