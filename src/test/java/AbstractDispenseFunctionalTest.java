import beverages.BeverageCatalogue;
import coffeemachine.CoffeeMachine;
import ingredients.IngredientReserve;
import javafx.util.Pair;
import org.junit.Before;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstractDispenseFunctionalTest {

    BeverageCatalogue beverageCatalogue;
    IngredientReserve ingredientReserve;
    CoffeeMachine coffeeMachine;

    String fileName = "src/test/resources/data.json";
    TestInputReader testInputReader = new TestInputReader();

    @Before
    public void setup() {

        // Initialize Test Reader
        testInputReader.init(fileName);

        beverageCatalogue = new BeverageCatalogue();
        ingredientReserve = new IngredientReserve();
        coffeeMachine = new CoffeeMachine(testInputReader.getOutletCount(), ingredientReserve, beverageCatalogue);

        // Fill Ingredients
        Map<String, Integer> ingredients = testInputReader.getIngredients();
        ingredients.forEach((k, v) -> {
            coffeeMachine.fillIngredient(k, v);
        });

        // Configure Beverages
        List<String> beverageNames = testInputReader.getBeverageNames();
        for (String beverageName : beverageNames) {
            List<Pair<String, Integer>> recipeDetails = testInputReader.getRecipeForBeverage(beverageName).entrySet()
                    .stream()
                    .map(e -> new Pair<String, Integer>(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());
            coffeeMachine.addNewBeverage(beverageName, recipeDetails);
        }
    }


    public String getRandomBeverageToOrder() {

        Collection<String> allBeverageNames = beverageCatalogue.getAllBeverageNames();
        int rnd = (int)(Math.random() * allBeverageNames.size()) + 1;
        Iterator<String> iterator = allBeverageNames.iterator();
        String beverageName = null;

        while(rnd-- > 0) {
            beverageName = iterator.next();
        }

        return beverageName;
    }
}
