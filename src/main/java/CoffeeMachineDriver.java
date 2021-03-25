import beverages.BeverageCatalogue;
import coffeemachine.CoffeeMachine;
import ingredients.IngredientReserve;

public class CoffeeMachineDriver {

    public static void main(String[] args) {

        BeverageCatalogue beverageCatalogue = new BeverageCatalogue();
        IngredientReserve ingredientReserve = new IngredientReserve();
        CoffeeMachine coffeeMachine = new CoffeeMachine(3, ingredientReserve, beverageCatalogue);

        // Read file
        // Call coffeemachine.CoffeeMachine.addIngredient or coffeeMachine.addNewBeverage or coffeeMachine.serveBeverage
    }
}
