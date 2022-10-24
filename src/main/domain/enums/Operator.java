package src.main.domain.enums;

public enum Operator {
    Addition('+'),
    Multiplication('*'),
    Division('/'),
    Exponential('^'),
    Subtraction('-');

    private final char operator;

    Operator(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }
}
