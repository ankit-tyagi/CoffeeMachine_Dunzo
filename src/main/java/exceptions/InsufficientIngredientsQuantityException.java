package exceptions;

import ingredients.Ingredient;

import java.util.List;

public class InsufficientIngredientsQuantityException extends RuntimeException {

    List<Ingredient> insufficientIngredients;

    public InsufficientIngredientsQuantityException(List<Ingredient> insufficientIngredients) {

        this.insufficientIngredients = insufficientIngredients;
    }

    public List<Ingredient> getInsufficientIngredients() {
        return insufficientIngredients;
    }
}
