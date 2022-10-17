package src.main.usecases;

import src.main.config.Config;
import src.main.domain.enums.Operator;
import src.main.domain.model.ExpressionModel;
import src.main.domain.types.Stack;


public class CalculateExpressionUseCase {
    private CalculateExpressionUseCase() {
    }

    public static int execute(ExpressionModel expressionModel) {
        System.out.println("CalculateExpressionUseCase - Process started");


//{ ( 5 + 12 ) + [ ( 10 - 8 ) ^ 3 ] }
//{ 17 + [ 2 ^ 3 ] }

        Stack stack = expressionModel.getExpression();
        Stack auxStack = new Stack();
        Integer[] operands = new Integer[2];
        Integer result = 0;
        int operandsCount = 0;
        Operator operator = null;
        boolean closed = false;
        boolean receivedNumber = false;

        do {
            if (stack.isEmpty()) {
                auxStack.flip();

                stack = Stack.clone(auxStack);

                auxStack.clear();
            }

            while (!stack.isEmpty()) {
                char element = stack.pop();
                auxStack.push(element);
                System.out.println(auxStack);

                if (isSpace(element)) {
                    receivedNumber = false;
                }

                Integer number = isNumber(element);
                if (number != null) {
                    if (!receivedNumber) {
                        receivedNumber = true;
                        operands[operandsCount] = isNumber(element);

                        operandsCount++;

                    } else {
                        operands[operandsCount - 1] = (operands[operandsCount - 1]) + (number * 10);
                    }

                }

                if (isClose(element)) {
                    receivedNumber = false;
                    operands = new Integer[2];
                    operandsCount = 0;
                    if (!closed) {
                        closed = true;
                    }
                }

                if (isOpen(element)) {

                    if (operandsCount == 2) {
                        assert operator != null;

                        result = calculate(operands[1], operator, operands[0]);
                        char auxStackElement = auxStack.pop();

                        while (!isClose(auxStackElement)) {
                            auxStackElement = auxStack.pop();
                        }
                        // auxStack.pop();
                        String resultString = result.toString();

                        for (int i = resultString.length() - 1; i >= 0; i--) {
                            auxStack.push(resultString.charAt(i));
                        }
                    }
                    operands = new Integer[2];
                    closed = false;
                    receivedNumber = false;
                    operandsCount = 0;

                }

                Operator operatorOrNull = isOperator(element);
                if (operatorOrNull != null) {
                    operator = operatorOrNull;
                }


            }
        } while (!isSolved(auxStack));

        System.out.println("CalculateExpressionUseCase - Process started");

        return result;
    }

    private static Integer isNumber(char element) {
        for (int i = 0; i < Config.numbers.length; i++) {
            if (element == Config.numbers[i]) {
                return i;
            }
        }
        return null;
    }

    private static boolean isOpen(char element) {
        return element == '{' || element == '[' || element == '(';
    }

    private static boolean isClose(char element) {
        return element == '}' || element == ']' || element == ')';

    }

    private static boolean isSolved(Stack stack) {
        Stack cpyStack = Stack.clone(stack);
        char element = ' ';
        while (!cpyStack.isEmpty()) {
            element = cpyStack.pop();
            if (isOpen(element) || isClose(element) || isOperator(element) != null) return false;
        }

        return true;
    }

    private static Integer calculate(Integer firstOperand, Operator operator, Integer secondOperand) {

        return switch (operator) {
            case Addition -> firstOperand + secondOperand;
            case Division -> firstOperand / secondOperand;
            case Exponential -> (int) Math.pow(firstOperand.floatValue(), secondOperand.floatValue());
            case Subtraction -> firstOperand - secondOperand;
            case Multiplication -> firstOperand * secondOperand;
        };

    }


    private static Operator isOperator(char element) {
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
}
