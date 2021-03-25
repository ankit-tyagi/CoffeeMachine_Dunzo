package coffeemachine;

import beverages.Beverage;
import beverages.Recipe;
import exceptions.InsufficientIngredientsQuantityException;
import exceptions.UnavailableIngredientsException;
import ingredients.IngredientReserve;

public class BrewBeverageTask implements Runnable {

    private Beverage beverage;
    private IngredientReserve ingredientReserve;

    public BrewBeverageTask(Beverage beverage, IngredientReserve ingredientReserve) {

        this.beverage = beverage;
        this.ingredientReserve = ingredientReserve;
    }

    @Override
    public void run() {

        String beverageName = beverage.getName();
        Recipe recipe = beverage.getRecipe();

        try {
            this.ingredientReserve.dispenseIngredientsForRecipe(recipe);
            System.out.println(beverageName + " is prepared");
        } catch (InsufficientIngredientsQuantityException ex){
            System.out.println(beverageName + " cannot be prepared because " + ex.getInsufficientIngredients() + " is/are insufficient.");
        } catch (UnavailableIngredientsException ex) {
            System.out.println(beverageName + " cannot be prepared because " + ex.getUnavailableIngredientsException() + " is/are unavailable.");
        }
    }
}
