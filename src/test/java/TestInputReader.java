import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestInputReader {

    private File file;
    private ObjectMapper objectMapper;
    private JsonNode rootJsonNode;

    public TestInputReader() {
        objectMapper = new ObjectMapper();
    }

    public void init(String fileLocation) {

        file = new File(fileLocation);
        try {
            rootJsonNode = objectMapper.readTree(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getOutletCount() {

        return rootJsonNode.get("machine").get("outlets").get("count_n").asInt();
    }

    public Map<String, Integer> getIngredients() {

        try {
            JsonNode ingredients = rootJsonNode.get("machine").get("total_items_quantity");
            return objectMapper.readValue(ingredients.toString(), Map.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new HashMap<>();
    }

    public List<String> getBeverageNames() {

        List<String> beverageNames = new ArrayList<>();
        rootJsonNode.get("machine").get("beverages").fieldNames().forEachRemaining(beverageNames::add);

        return beverageNames;
    }

    public Map<String, Integer> getRecipeForBeverage(String beverageName) {

        JsonNode recipe = null;
        try {
            recipe = rootJsonNode.get("machine").get("beverages").get(beverageName);
            return objectMapper.readValue(recipe.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }
}