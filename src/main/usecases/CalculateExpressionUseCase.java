package src.main.usecases;

import src.main.config.Config;
import src.main.domain.enums.Operand;
import src.main.domain.model.ExpressionModel;
import src.main.domain.types.Stack;

public class CalculateExpressionUseCase {
    private CalculateExpressionUseCase() {
    }

    public static double execute(ExpressionModel expressionModel) {

        //desempilha - > guarda em outra stack

        // se for numero guarda ele como num1

        //desempilha - > guarda em outra stack

        // prox caractere = op

        //desempilha - > guarda em outra stack

        // prox caractere = num2

        //desempilha - > guarda em outra stack

        // faz operacao, pega resultado

        // remove (...) da outra stack

        // coloca numero

//{ [ ( ( 2 ^ 5 ) - ( 3 * 15 ) ) + ( ( 102 + 379 ) * ( 468 - 248 ) ) ] - [ ( ( 3 ^ 6 ) - ( 54 * 11 ) ) + ( ( 175 / 5 ) / ( 100 - 117 ) ) ] }
        Stack stack = expressionModel.getExpression();
        Stack auxStack = new Stack();
        int[] numbers = new int[2];
        int i = 0;
        boolean gotFirstNumber = false;
        boolean gotSecondNumber = false;
        boolean gotOperand = false;
        while (!stack.isEmpty()) {
            int number;
            char operator;

            char element = stack.pop();

            number = isNumber(element);

            if (number != -1) {
                //todo: e um numero
                i++;
                if (i > 1) i = 0;
                numbers[i] = number;
                if (i == 0) gotFirstNumber = true;
                if (i == 1) gotSecondNumber = true;
            }

            if (isOpen(element)) {
                //todo: e um abre
            }


            if (isClose(element)) {
                //todo: e um fecha

            }

            Operand operand = isOperand(element);

            if (operand != null) {
                //todo: e um operador
                gotOperand = true;
            }

            auxStack.push(element);

        }
        return 0;
    }

    private static int isNumber(char element) {
        for (int i = 0; i < Config.numbers.length; i++) {
            if (element == Config.numbers[i]) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isOpen(char element) {
        return true;
    }

    private static boolean isClose(char element) {
        return true;
    }

    private static Operand isOperand(char element) {
        if (element == Operand.Division.getOperand()) return Operand.Division;
        if (element == Operand.Addition.getOperand()) return Operand.Addition;
        if (element == Operand.Subtraction.getOperand()) return Operand.Subtraction;
        if (element == Operand.Exponential.getOperand()) return Operand.Exponential;
        if (element == Operand.Multiplication.getOperand()) return Operand.Multiplication;
        return null;
    }
}
