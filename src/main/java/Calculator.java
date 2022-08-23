import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Calculator {
    private Set<Character> digits = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',');
    private Set<Character> operations = Set.of('+', '*', '/', '-');

    public float solve (String expr) {
        List<Float> numbers = new LinkedList<>();
        List<String> opers = new LinkedList<>();
        StringBuilder buf = new StringBuilder("");
        System.out.println(expr);

        int start = 0;
        int[] brCount = {0, 0};

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                brCount[0]++;
                if (brCount[0] <= 1)
                    start = i;
            } else if (expr.charAt(i) == ')') {
                brCount[1]++;
            }

            if (brCount[0] == brCount[1] && brCount[0] != 0) {
                solve(expr.substring(start + 1, i));
                brCount = new int[]{0, 0};
            }
        }

        int regime = 0;

        for (int i = 0; i < expr.length(); i++) {
            if (regime == 0) {
                if (digits.contains(expr.charAt(i))) {
                    buf.append(expr.charAt(i));
                } else {
                    numbers.add(Float.parseFloat(buf.toString()));
                    regime = 1;
                    i--;
                    buf = new StringBuilder("");
                }
            } else {
                if (operations.contains(expr.charAt(i))) {
                    buf.append(expr.charAt(i));
                } else {
                    opers.add(buf.toString());
                    regime = 0;
                    i--;
                    buf = new StringBuilder("");
                }
            }

            if (i == expr.length() - 1) {
                numbers.add(Float.parseFloat(buf.toString()));
            }

//          if (digits.contains(expr.charAt(i))) {
//                buf.append(expr.charAt(i));
//            } else {
//                numbers.add(Float.parseFloat(buf.toString()));
//                buf = new StringBuilder();
//            }
//
//            if (i == expr.length() - 1)
//                numbers.add(Float.parseFloat(buf.toString()));
        }

//        if (opers.size() == numbers.size()) {
//
//        }


        System.out.println(numbers.toString());

        return 0f;
    }
}
