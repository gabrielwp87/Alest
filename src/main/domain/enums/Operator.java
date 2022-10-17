package src.main.domain.enums;

public enum Operator {
    Addition('+'),
    Multiplication('*'),
    Division('/'),
    Exponential('^'),

    Subtraction('-');

    private final char operator;

    Operator(char operand) {
        this.operator = operand;
    }

    public char getOperator() {
        return operator;
    }
}
