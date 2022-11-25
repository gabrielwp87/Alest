package StackAssignment.main.src.domain.enums;

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
