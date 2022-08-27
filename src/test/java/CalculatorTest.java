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
    public void doubleMinus() {
        Assertions.assertEquals(23, calculator.solve("8+2--9*1+8/2"));
    }

    @Test
    public void multiMinus() {
        Assertions.assertEquals(-17, calculator.solve("8*-2-1"));
    }

    @Test
    public void simpleInt() {
        Assertions.assertEquals(5, calculator.solve("8+2-9*1+8/2"));
    }

    @Test
    public void simpleFloat() {
        Assertions.assertEquals(-3.04f, calculator.solve("1.1*2.3/0.5-1*8.1"));
    }

    @Test
    public void manyMultiplyDivision() {
        Assertions.assertEquals(186.67f, calculator.solve("2*5*7/3*8"));
    }

    @Test
    public void hardInt() {
        Assertions.assertEquals(7, calculator.solve("3+4-(4-5)+-1"));
    }

    @Test
    public void hardFloat() {
        Assertions.assertEquals(9.49f, calculator.solve("4+(1.8/2--(8/3.5-6.7))+9"));
    }
}
