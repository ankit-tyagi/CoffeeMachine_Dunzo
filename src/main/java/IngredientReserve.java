import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class IngredientReserve {

    private final Map<Ingredient, Integer> ingredientQuantityMap;

    public IngredientReserve() {
        this.ingredientQuantityMap = new HashMap<>();
    }

    public synchronized void fillIngredient(Ingredient ingredient, Integer quantity) {

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

        if (!unavailableIngredients.isEmpty() || !insufficientIngredients.isEmpty()) {
            if(!unavailableIngredients.isEmpty()){
                throw new InsufficientIngredientException(unavailableIngredients.iterator().next().getName(), "not available");
            }else{
                throw new InsufficientIngredientException(unavailableIngredients.iterator().next().getName(), "not sufficient");
            }
        }

        recipe.getIngredients().forEach(new BiConsumer<Ingredient, Integer>() {
            @Override
            public void accept(Ingredient ingredient, Integer integer) {
                IngredientReserve.this.fillIngredient(ingredient, (ingredientQuantityMap.get(ingredient) - recipe.getIngredients().get(ingredient)));
            }
        });

    }
}
