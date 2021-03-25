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

/**
 * This class is the top level facade to interact with Coffee Machine. Operation like Adding ingredients,
 * registering new beverages and request to serve a beverage is handled by this class or put in other words
 * this class is the interface exposed to outside world using which the clients (humans/circuits) can interact
 * with Coffee Machine.
 *
 * Internally, This classes uses a Fixed thread pool executor to handle concurrent serve-beverage requests. Thread pool
 * size is dependent on the number of dispensing outlets in the machine i.e 3 Outlets configures the thread pool with 3
 * threads and so on. Having a fixed thread pool ensure that only N (number of outlets) beverages are being served at a
 * time although multiple (unbounded in our case) can be accepted by the coffee machine.
 *
 * Since this class is a facade, it interacts with multiple other subsystems to fulfill a given request Like - to add a
 * new ingredient, it takes help of IngredientReserve, to find and store beverages it uses BeverageCatalogue.
 */
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
