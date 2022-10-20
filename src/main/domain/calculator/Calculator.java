package src.main.domain.calculator;

import src.main.domain.enums.Operator;

public class Calculator {
    private Calculator() {
    }

    public static Integer calculate(Integer firstOperand, Operator operator, Integer secondOperand) {

        return switch (operator) {
            case Addition -> firstOperand + secondOperand;
            case Division -> firstOperand / secondOperand;
            case Exponential -> (int) Math.pow(firstOperand.floatValue(), secondOperand.floatValue());
            case Subtraction -> firstOperand - secondOperand;
            case Multiplication -> firstOperand * secondOperand;
        };

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
