import org.junit.Test;

public class SingleDispenseOutletFunctionalTest extends AbstractDispenseFunctionalTest {

    @Test
    public void test() throws InterruptedException {

        String beverageName = getRandomBeverageToOrder();
        coffeeMachine.serveBeverage(beverageName);
    }
}
