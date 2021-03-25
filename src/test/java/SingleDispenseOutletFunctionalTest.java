import org.junit.Test;

public class SingleDispenseOutletFunctionalTest extends AbstractDispenseFunctionalTest {

    @Test
    public void test() throws InterruptedException {

        String beverageName = getRandomBeverageToOrder();
        coffeeMachine.serveBeverage(beverageName);

        // Sleep current Test thread for some time to let beverage be brewed
        Thread.sleep(1000);
    }
}
