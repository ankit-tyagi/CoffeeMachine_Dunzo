package ingredients;

import beverages.Recipe;
import exceptions.InsufficientIngredientsQuantityException;
import exceptions.UnavailableIngredientsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
