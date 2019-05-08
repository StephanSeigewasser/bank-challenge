import java.util.Stack;
import java.util.stream.Stream;

public class Calc {
    private static final String OPERATORS = "+-*/";

    public double evaluate(String expr) {
        Stack<Double> stack = new Stack<>();

        try {
            Stream.of(expr.split(" ")).forEach(literal -> {
                if (isNumber(literal)) {
                    stack.push(Double.parseDouble(literal));
                } else if (isOperator(literal)) {
                    double operant2 = stack.pop();
                    double operant1 = stack.pop();

                    stack.push(computeOperation(operant1, operant2, literal));
                } else {
                    throw new IllegalArgumentException("literal is no operator or operant: " + literal);
                }
            });
        } catch (Exception e) {
            // could not process expression so there'll be no result
            stack.clear();
        }

        if(stack.empty()) {
            return 0;
        }

        return stack.pop();
    }

    private double computeOperation(double operant1, double operant2, String operator) {
        switch (operator) {
            case "+":
                return operant1 + operant2;
            case "-":
                return operant1 - operant2;
            case "*":
                return operant1 * operant2;
            case "/":
                return operant1 / operant2;
            default:
                throw new IllegalArgumentException("got illegal operator : " + operator);
        }
    }

    private boolean isOperator(String literal) {
        return literal.length() == 1 && OPERATORS.contains(literal);
    }

    private boolean isNumber(String literal) {
        try {
            Double.parseDouble(literal);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
