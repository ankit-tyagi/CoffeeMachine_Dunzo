import org.junit.Test;

public class MultiDispenseOutletFunctionalTest extends AbstractDispenseFunctionalTest {

    @Test
    public void test() throws InterruptedException {

        int outletCount = testInputReader.getOutletCount();

        // order multiple beverages
        for (int i = 0; i < outletCount; i++) {
            String beverageName = getRandomBeverageToOrder();
            coffeeMachine.serveBeverage(beverageName);
        }

        // Sleep current Test thread for some time to let beverage be brewed
        Thread.sleep(1000);
    }
}
