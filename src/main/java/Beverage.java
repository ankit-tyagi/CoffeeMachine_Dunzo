import java.util.Objects;

public class Beverage {

    private final String name;
    private final Recipe recipe;

    public Beverage(String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beverage beverage = (Beverage) o;
        return Objects.equals(name, beverage.name) &&
                Objects.equals(recipe, beverage.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, recipe);
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "name='" + name + '\'' +
                ", recipe=" + recipe +
                '}';
    }
}
