package ingredients;

import beverages.Recipe;
import exceptions.InsufficientIngredientsQuantityException;
import exceptions.UnavailableIngredientsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ingredient Reserve does what the name suggests. It acts as the reserve of ingredients that the coffee machine has.
 * Whenever a beverage is to be made, ingredients from this reserve are used and similarly when coffee machine is reloaded
 * with more ingredients, they go to ingredient reserve.
 *
 * Internally, this class ensures that only 1 thread at a time is operating on the reservoir. It can either be dispense
 * ingredients for 1 recipe at a time or fill ingredients back. Synchronization at this point ensures that we do not have race
 * condition when serving multiple beverages because only 1 beverage ingredients can be released by the reservoir at a
 * time and if any beverage finds reservoir to have insufficient or unavailable ingredients, it sees valid state.
 */
public class IngredientReserve {

    private final Map<Ingredient, Integer> ingredientQuantityMap;

    public IngredientReserve() {

        this.ingredientQuantityMap = new HashMap<>();
    }

    public synchronized void setIngredientQuantity(Ingredient ingredient, Integer quantity) {

        this.ingredientQuantityMap.put(ingredient, quantity);
    }

    public synchronized void dispenseIngredientsForRecipe(Recipe recipe) {

        List<Ingredient> unavailableIngredients = new ArrayList<>();
        List<Ingredient> insufficientIngredients = new ArrayList<>();

        for (Map.Entry<Ingredient, Integer> ingredientQuantityPair : recipe.getIngredients().entrySet()) {
            Ingredient ingredient = ingredientQuantityPair.getKey();
            Integer requiredQuantity = ingredientQuantityPair.getValue();

            if (!ingredientQuantityMap.containsKey(ingredient)) {
                unavailableIngredients.add(ingredient);
            } else if (ingredientQuantityMap.get(ingredient) < requiredQuantity) {
                insufficientIngredients.add(ingredient);
            }
        }

        if (!unavailableIngredients.isEmpty()) {
            throw new UnavailableIngredientsException(unavailableIngredients);
        }

        if (!insufficientIngredients.isEmpty()) {
            throw new InsufficientIngredientsQuantityException(insufficientIngredients);
        }

        recipe.getIngredients().forEach((ingredient, requiredQuantity) -> {
            setIngredientQuantity(ingredient, (ingredientQuantityMap.get(ingredient) - requiredQuantity));
        });
    }
}
