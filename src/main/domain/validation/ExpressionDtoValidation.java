package src.main.domain.validation;

import src.main.domain.types.Stack;

public class ExpressionDtoValidation {

    private ExpressionDtoValidation() {
    }

    public static Stack validate(String expression) {
        System.out.println("ExpressionDtoValidation - Process started");
        Stack expressionStack = new Stack();
        for (int i = 0; i < expression.length(); i++) expressionStack.push(expression.charAt(i));

        System.out.println("ExpressionDtoValidation - Process finished");

        return expressionStack;
    }
}
