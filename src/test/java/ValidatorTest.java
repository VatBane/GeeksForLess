import org.junit.jupiter.api.*;

public class ValidatorTest {
    @Test
    public void bracketsTrue() {
        Assertions.assertTrue(Validator.brackets("()"));
    }

    @Test
    public void bracketsFalse() {
        Assertions.assertFalse(Validator.brackets("(((((())"));
    }

    @Test
    public void oneTwoOperations() {
        Assertions.assertTrue(Validator.operationsNumber("+000*+00"));
    }

    @Test
    public void ThreeMoreOperations() {
        Assertions.assertFalse(Validator.operationsNumber("****////"));
    }

    @Test
    public void orderTrue() {
        Assertions.assertTrue(Validator.operationsOrder("*-"));
    }

    @Test
    public void orderFalse() {
        Assertions.assertFalse(Validator.operationsOrder("-+"));
    }
}
