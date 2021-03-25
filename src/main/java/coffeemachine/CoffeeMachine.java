package coffeemachine;

import beverages.Beverage;
import beverages.BeverageCatalogue;;
import ingredients.Ingredient;
import ingredients.IngredientReserve;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CoffeeMachine implements AutoCloseable {

    private final IngredientReserve ingredientReserve;
    private final BeverageCatalogue beverageCatalogue;
    private final ExecutorService executorService;

    public CoffeeMachine(int dispenseOutlets, IngredientReserve ingredientReserve, BeverageCatalogue beverageCatalogue) {

        this.executorService = Executors.newFixedThreadPool(dispenseOutlets);
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

        Optional<Beverage> beverage = this.beverageCatalogue.getBeverage(beverageName);
        if (beverage.isPresent()) {
            BrewBeverageTask task = new BrewBeverageTask(beverage.get(), ingredientReserve);
            executorService.submit(task);
        } else {
            System.out.println(beverageName + " not found");
        }
    }

    @Override
    public void close() {

        this.executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Executor Service did not shutdown");
                    }
                }
            } catch (InterruptedException ex) {
                executorService.shutdownNow();
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}
