package src.main.domain.exceptions;

public class InvalidExpressionException extends Exception {
    private final String expression;

    public InvalidExpressionException(String message, String expression) {
        super(message);
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
