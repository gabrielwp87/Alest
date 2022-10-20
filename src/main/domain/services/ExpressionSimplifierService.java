package src.main.domain.services;

import src.main.config.Config;
import src.main.domain.calculator.Calculator;
import src.main.domain.enums.Operator;
import src.main.domain.types.Stack;

public class ExpressionSimplifierService {
    private ExpressionSimplifierService() {
    }

    public static Stack simplify(Stack expressionStack) {

        Stack auxStack = new Stack();
        Double[] operands = new Double[2];
        int operandsCount = 0, decimalPlaces = 0;
        Operator operator = null, operandSign = null;
        boolean closed = false;
        boolean receivedNumber = false;
        boolean receivedSubtractionOperator = false;


        while (!expressionStack.isEmpty()) {
            char element = expressionStack.pop();
            auxStack.push(element);

            if (isSpace(element)) {
                receivedNumber = false;
                receivedSubtractionOperator = false;
            }

            Operator operatorOrNull = isOperator(element);
            if (operatorOrNull != null) {
                if (receivedNumber) operandSign = operatorOrNull;
                else operator = operatorOrNull;

                if (operatorOrNull == Operator.Subtraction) {
                    receivedSubtractionOperator = true;
                } else {
                    receivedSubtractionOperator = false;

                }
            }

            if (receivedNumber && receivedSubtractionOperator) {
                operands[operandsCount - 1] = operands[operandsCount - 1] * -1;
            }

            if (element == '.' && receivedNumber) {
                operands[operandsCount - 1] = operands[operandsCount - 1] / Math.pow(10, decimalPlaces);
                decimalPlaces = 1;
            }

            Double number = isNumber(element);
            if (number != null) {
                decimalPlaces++;
                if (!receivedNumber) {
                    receivedNumber = true;
                    operands[operandsCount] = number;
                    operandsCount++;

                } else {
                    double result = (number * (Math.pow(10, decimalPlaces - 1)));

                    if (operands[operandsCount - 1] < 0) {
                        operands[operandsCount - 1] = (operands[operandsCount - 1]) - result;
                    } else {
                        operands[operandsCount - 1] = (operands[operandsCount - 1]) + result;
                    }

                }

            } else decimalPlaces = 0;


            if (isClose(element)) {
                receivedNumber = false;
                receivedSubtractionOperator = false;
                operands = new Double[2];
                operandsCount = 0;
                if (!closed) {
                    closed = true;
                }
            }

            if (isOpen(element)) {

                if (operandsCount == 2) {
                    assert operator != null;

                    String resultString = Calculator.calculate(operands[1], operator, operands[0]).toString();
                    char auxStackElement = auxStack.pop();

                    while (!isClose(auxStackElement)) {
                        auxStackElement = auxStack.pop();
                    }

                    for (int i = resultString.length() - 1; i >= 0; i--) {
                        auxStack.push(resultString.charAt(i));
                    }
                }
                operands = new Double[2];
                closed = false;
                receivedNumber = false;
                operandsCount = 0;
                receivedSubtractionOperator = false;


            }


        }


        auxStack.flip();

        return Stack.clone(auxStack);

    }

    static Double isNumber(char element) {
        try {
            return Double.parseDouble(String.valueOf(element));
        } catch (NumberFormatException err) {
            return null;
        }
    }

    public static boolean isOpen(char element) {
        return element == '{' || element == '[' || element == '(';
    }

    public static boolean isClose(char element) {
        return element == '}' || element == ']' || element == ')';

    }


    public static Operator isOperator(char element) {
        if (element == Operator.Division.getOperator()) return Operator.Division;
        if (element == Operator.Addition.getOperator()) return Operator.Addition;
        if (element == Operator.Subtraction.getOperator()) return Operator.Subtraction;
        if (element == Operator.Exponential.getOperator()) return Operator.Exponential;
        if (element == Operator.Multiplication.getOperator()) return Operator.Multiplication;
        return null;
    }

    static boolean isSpace(char element) {
        return element == ' ';
    }
}
