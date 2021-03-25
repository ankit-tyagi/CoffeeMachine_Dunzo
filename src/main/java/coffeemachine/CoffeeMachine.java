package coffeemachine;

import beverages.BeverageCatalogue;
import beverages.Recipe;
import exceptions.BeverageNotFoundException;
import exceptions.InsufficientIngredientsQuantityException;
import exceptions.UnavailableIngredientsException;
import ingredients.Ingredient;
import ingredients.IngredientReserve;
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
        this.ingredientReserve.setIngredientQuantity(new Ingredient(ingredientName), quantity);
    }

   public void addNewBeverage(String beverageName, List<Pair<String, Integer>> ingredients) {
        this.beverageCatalogue.registerNewBeverage(beverageName, ingredients);
   }

    public void serveBeverage(String beverageName) {

        try {
            Recipe recipe = this.beverageCatalogue.getRecipeForBeverage(beverageName);
            this.ingredientReserve.dispenseIngredientsForRecipe(recipe);
            makeBeverage(beverageName);
        } catch (BeverageNotFoundException ex) {
            System.out.println(ex.getBeverageName() + " not found");
        } catch (InsufficientIngredientsQuantityException ex){
            System.out.println(beverageName + " cannot be prepared because " + ex.getInsufficientIngredients() + " is/are insufficient.");
        } catch (UnavailableIngredientsException ex) {
            System.out.println(beverageName + " cannot be prepared because " + ex.getUnavailableIngredientsException() + " is/are unavailable.");
        }
    }

    private void makeBeverage(String beverageName) {
        System.out.println(beverageName + " is prepared");
    }
}
