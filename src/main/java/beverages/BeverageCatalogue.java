package beverages;

import ingredients.Ingredient;
import javafx.util.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This is the catalogue of all the beverages the coffee machine knows how to brew. Consider this as persistence layer
 * through which we can read and write about a beverage.
 */
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

    public Optional<Beverage> getBeverage(String beverageName) {

        return Optional.ofNullable(beverageByName.get(beverageName));
    }

    public Collection<String> getAllBeverageNames() {

        return beverageByName.keySet();
    }
}
