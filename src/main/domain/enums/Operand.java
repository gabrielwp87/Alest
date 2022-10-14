package src.main.domain.enums;

public enum Operand {
    Addition('+'),
    Multiplication('*'),
    Division('/'),
    Exponential('ˆ'),
    
    Subtraction('-');

    private char operand;

    Operand(char operand) {
        this.operand = operand;
    }

    public char getOperand() {
        return operand;
    }
}
