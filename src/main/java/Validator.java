public class Validator {
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
}
