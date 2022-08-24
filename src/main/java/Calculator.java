import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Calculator {
    private final Set<Character> digits = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',');
    private final Set<Character> operations = Set.of('+', '*', '/', '-');

    public float solve(String expr) {
        try {
            List<Float> numbers = new LinkedList<>();
            List<String> opers = new LinkedList<>();
            StringBuilder buf = new StringBuilder("");
            System.out.println(expr);

            if (!Validator.brackets(expr) || !Validator.operationsNumber(expr) || !Validator.operationsOrder(expr) || Validator.operationBeforeExpr(expr))
                throw new IllegalArgumentException("Expression is wrong!!!");

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
                    expr = new StringBuilder(expr).replace(start, i + 1, Float.toString(solve(expr.substring(start + 1, i)))).toString();
                    i = 0;
                    brCount = new int[]{0, 0};
                }
            }

            int regime = 0;

            for (int i = 0; i < expr.length(); i++) {
                if (regime == 0) {
                    if (digits.contains(expr.charAt(i))) {
                        buf.append(expr.charAt(i));
                    } else {
                        regime = 1;
                        i--;
                        if (buf.isEmpty())
                            continue;
                        numbers.add(Float.parseFloat(buf.toString()));
                        buf = new StringBuilder("");
                    }
                } else {
                    if (operations.contains(expr.charAt(i))) {
                        buf.append(expr.charAt(i));
                    } else {
                        regime = 0;
                        i--;
                        opers.add(buf.toString());
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

            if (opers.size() == numbers.size()) {
                numbers.set(0, numbers.get(0) * -1);
                opers.remove(0);
            }

            while (true) {
                if (opers.contains("*-")) {
                    numbers.set(opers.indexOf("*-") + 1, (numbers.get(opers.indexOf("*-")) + 1) * -1);
                    opers.set(opers.indexOf("*-"), "*");
                } else if (opers.contains("/-")) {
                    numbers.set(opers.indexOf("/-") + 1, (numbers.get(opers.indexOf("/-")) + 1) * -1);
                    opers.set(opers.indexOf("/-"), "/");
                } else if (opers.contains("+-")) {
                    numbers.set(opers.indexOf("+-") + 1, (numbers.get(opers.indexOf("+-")) + 1) * -1);
                    opers.set(opers.indexOf("+-"), "+");
                } else if (opers.contains("--")) {
                    numbers.set(opers.indexOf("--") + 1, (numbers.get(opers.indexOf("--")) + 1) * -1);
                    opers.set(opers.indexOf("--"), "-");
                } else {
                    break;
                }
            }

            while (opers.size() > 0) {
                if (opers.contains("*")) {
                    numbers.set(opers.indexOf("*"), (numbers.get(opers.indexOf("*")) * (numbers.get(opers.indexOf("*") + 1))));
                    numbers.remove(opers.indexOf("*") + 1);
                    opers.remove("*");
                } else if (opers.contains("/")) {
                    numbers.set(opers.indexOf("/"), (numbers.get(opers.indexOf("/")) / (numbers.get(opers.indexOf("/") + 1))));
                    numbers.remove(opers.indexOf("/") + 1);
                    opers.remove("/");
                } else {
                    if (opers.get(0).equals("+")) {
                        numbers.set(0, numbers.get(0) + numbers.get(1));
                    } else {
                        numbers.set(0, numbers.get(0) - numbers.get(1));
                    }
                    numbers.remove(1);
                    opers.remove(0);
//                    numbers.set(opers.indexOf("+"), (numbers.get(opers.indexOf("+")) + (numbers.get(opers.indexOf("+") + 1))));
//                    numbers.remove(opers.indexOf("+") + 1);
//                    opers.remove("+");
                }
            }

            System.out.println((float) Math.round(numbers.get(0) * 100)/100);

            return (float) Math.round(numbers.get(0) * 100)/100;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0f;
    }
}
