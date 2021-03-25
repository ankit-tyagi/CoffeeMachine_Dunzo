package exceptions;

public class BeverageNotFoundException extends RuntimeException {

    private final String beverageName;

    public BeverageNotFoundException(String beverageName) {
        this.beverageName = beverageName;
    }

    public String getBeverageName() {
        return beverageName;
    }
}
