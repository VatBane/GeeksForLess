import javax.swing.plaf.basic.BasicMenuUI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Validator {
    private static final Set<Character> symbols = Set.of('+', '-', '*', '/');

    public static boolean brackets(String expr) {
        int left = 0, right = 0;

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(')
                left++;
            else if (expr.charAt(i) == ')')
                right++;
        }

        return left == right;
    }

    public static boolean operationsNumber(String expr) {
        int count = 0;

        for(int i = 1; i < expr.length(); i++) {
            if (symbols.contains(expr.charAt(i))) {
                count++;
            } else
                count = 0;
            if (count > 2)
                return false;
        }
        return true;
    }

    public static boolean operationsOrder(String expr) {
        char[] buffer = new char[2];

        for (int i = 0, j = 0; i < expr.length(); i++) {
            if (symbols.contains(expr.charAt(i))) {
                buffer[j] = expr.charAt(i);
                j++;
            } else if (j != 0) {
                buffer = new char[2];
                j = 0;
            }

            if (j == 2) {
                if(!checkOrder(buffer))
                    return false;
                j = 0;
                buffer = new char[2];
            }
        }

        return true;
    }

    private static boolean checkOrder(char[] buffer) {
        return buffer[1] == '-';
    }

    public static boolean operationBeforeExpr(String expr) {
        return (symbols.contains(expr.charAt(0)) && expr.charAt(0) != '-');
    }

    public static boolean onlyDigits(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            if (Character.isAlphabetic(expr.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
