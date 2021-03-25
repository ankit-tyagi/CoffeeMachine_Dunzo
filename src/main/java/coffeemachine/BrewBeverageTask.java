package coffeemachine;

import beverages.Beverage;
import beverages.Recipe;
import exceptions.InsufficientIngredientsQuantityException;
import exceptions.UnavailableIngredientsException;
import ingredients.IngredientReserve;

/**
 * This class is the main handler for serving a brewing. It manages of end-to-end life cycle of a serve/brew beverage by
 * coordinating activities across multiple sub operations like - dispensing ingredients from reservoir to mixing chamber,
 * Moving the beverage from mixing chamber to boiling chamber and then dispensing the beverage to one of the outlets.
 *
 * Note: For demo purposes, this class only have 1 sub operation - Dispensing the ingredients from reservoir. As soon
 * as ingredients are release from reservoir, this class considers beverage to have been served and prints a message to
 * system.out.
 */
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
            // Dispense Ingredients from reservoir to mixing chamber
            this.ingredientReserve.dispenseIngredientsForRecipe(recipe);

            // TODO: Do other operations

            // Beverage have been prepared/served.
            System.out.println(beverageName + " is prepared");
        } catch (InsufficientIngredientsQuantityException ex){
            System.out.println(beverageName + " cannot be prepared because " + ex.getInsufficientIngredients() + " is insufficient.");
        } catch (UnavailableIngredientsException ex) {
            System.out.println(beverageName + " cannot be prepared because " + ex.getUnavailableIngredientsException() + " is unavailable.");
        }
    }
}
