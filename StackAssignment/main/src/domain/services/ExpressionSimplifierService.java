package StackAssignment.main.src.domain.services;

import StackAssignment.main.src.domain.calculator.Calculator;
import StackAssignment.main.src.domain.enums.Operator;
import StackAssignment.main.src.domain.types.Stack;

public class ExpressionSimplifierService {

    static Stack auxStack;

    static Double[] operands;
    static int operandsCount, decimalPlaces;
    static Operator operator, operandSign;
    static boolean closed;
    static boolean receivedNumber;
    static boolean receivedSubtractionOperator;

    private ExpressionSimplifierService() {
    }

    public static Stack simplify(Stack expressionStack) {

        auxStack = new Stack();
        operands = new Double[2];
        operandsCount = 0;
        decimalPlaces = 0;
        operator = null;
        operandSign = null;
        closed = false;
        receivedNumber = false;
        receivedSubtractionOperator = false;


        while (!expressionStack.isEmpty()) {
            char element = expressionStack.pop();
            auxStack.push(element);

            if (isSpace(element)) processSpace();

            Operator operatorOrNull = isOperator(element);
            if (operatorOrNull != null) processOperator(operatorOrNull);

            if (receivedNumber && receivedSubtractionOperator) processNegativeNumber();

            if (element == '.' && receivedNumber) processDecimal();

            Double number = isNumber(element);
            if (number != null) processNumber(number);
            else decimalPlaces = 0;

            if (isClose(element)) processClose();

            if (isOpen(element)) processOpen();

        }


        auxStack.flip();

        return Stack.clone(auxStack);

    }

    public static Double isNumber(char element) {
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

    private static boolean isSpace(char element) {
        return element == ' ';
    }

    private static void processSpace() {
        receivedNumber = false;
        receivedSubtractionOperator = false;
    }

    private static void processOperator(Operator element) {
        if (receivedNumber) operandSign = element;
        else operator = element;

        receivedSubtractionOperator = element == Operator.Subtraction;
    }

    private static void processNumber(Double number) {
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
    }

    private static void processOpen() {
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

    private static void processClose() {
        receivedNumber = false;
        receivedSubtractionOperator = false;
        operands = new Double[2];
        operandsCount = 0;
        if (!closed) {
            closed = true;
        }
    }

    private static void processDecimal() {
        operands[operandsCount - 1] = operands[operandsCount - 1] / Math.pow(10, decimalPlaces);
        decimalPlaces = 1;
    }

    private static void processNegativeNumber() {
        operands[operandsCount - 1] = operands[operandsCount - 1] * -1;

    }

}
