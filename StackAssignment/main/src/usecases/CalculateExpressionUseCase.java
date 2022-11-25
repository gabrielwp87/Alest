package StackAssignment.main.src.usecases;

import StackAssignment.main.src.domain.model.ExpressionModel;
import StackAssignment.main.src.domain.services.ExpressionSimplifierService;
import StackAssignment.main.src.domain.types.Stack;


public class CalculateExpressionUseCase {
    private CalculateExpressionUseCase() {
    }

    public static double execute(ExpressionModel expressionModel) {

        Stack expressionStack = expressionModel.getExpression();

        int step = 0;
        System.out.println("Step " + step + " -> " + expressionStack);
        while (!isSolved(expressionStack)) {
            step++;
            expressionStack = ExpressionSimplifierService.simplify(expressionStack);
            System.out.println("Step " + step + " -> " + expressionStack);

        }

        Stack resultStack = Stack.clone(expressionStack);
        double result = Double.parseDouble(resultStack.toString());

        return result;
    }

    private static boolean isSolved(Stack expressionStack) {
        Stack cpyStack = Stack.clone(expressionStack);
        char element = ' ';
        while (!cpyStack.isEmpty()) {
            element = cpyStack.pop();
            if (ExpressionSimplifierService.isOpen(element) || ExpressionSimplifierService.isClose(element))
                return false;
        }

        return true;
    }


}
