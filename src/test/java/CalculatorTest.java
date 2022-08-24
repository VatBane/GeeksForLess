import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void wrongBracketsNumber() {
         Assertions.assertEquals(0f, calculator.solve("(3+4)+2)"));
    }

    @Test
    public void wrongOperationsOrder() {
        Assertions.assertEquals(0f, calculator.solve("3.4*-5+*9"));
    }

    @Test
    public void operationBeforeExpr() {
        Assertions.assertEquals(0f, calculator.solve("*9+10-2"));
    }

    @Test
    public void simpleInt() {
        Assertions.assertEquals(5, calculator.solve("8+2-9*1+8/2"));
    }

    @Test
    public void simpleFloat() {
        Assertions.assertEquals(-3.04f, calculator.solve("1.1*2.3/0.5-1*8.1"));
    }

}
