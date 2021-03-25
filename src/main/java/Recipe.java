import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Recipe {

    private final Map<Ingredient, Integer> ingredients;

    public Recipe(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients);
    }

    static class RecipeBuilder {

        private Map<Ingredient, Integer> ingredients;

        public RecipeBuilder() {
            this.ingredients = new HashMap<>();
        }

        public void addIngredient(Ingredient ingredient, int quantity) {
            this.ingredients.put(ingredient, quantity);
        }

        public void addAllIngredients(Map<Ingredient, Integer> ingredients) {
            this.ingredients.putAll(ingredients);
        }

        public Recipe build() {
            return new Recipe(Collections.unmodifiableMap(this.ingredients));
        }
    }
}
