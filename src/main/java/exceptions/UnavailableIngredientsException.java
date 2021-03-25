package exceptions;

import ingredients.Ingredient;

import java.util.List;

public class UnavailableIngredientsException extends RuntimeException {

    private List<Ingredient> unavailableIngredientsException;

    public UnavailableIngredientsException(List<Ingredient> unavailableIngredientsException) {

        this.unavailableIngredientsException = unavailableIngredientsException;
    }

    public List<Ingredient> getUnavailableIngredientsException() {
        return unavailableIngredientsException;
    }
}
