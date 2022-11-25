package StackAssignment.main.src.domain.calculator;

import StackAssignment.main.src.domain.enums.Operator;

public class Calculator {
    private Calculator() {
    }

    public static Double calculate(Double firstOperand, Operator operator, Double secondOperand) {

        return switch (operator) {
            case Addition -> firstOperand + secondOperand;
            case Division -> firstOperand / secondOperand;
            case Exponential -> Math.pow(firstOperand.floatValue(), secondOperand.floatValue());
            case Subtraction -> firstOperand - secondOperand;
            case Multiplication -> firstOperand * secondOperand;
        };

    }
}
