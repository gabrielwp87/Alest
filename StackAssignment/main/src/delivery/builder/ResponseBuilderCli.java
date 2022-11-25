package StackAssignment.main.src.delivery.builder;

import StackAssignment.main.src.domain.model.ExpressionModel;

public class ResponseBuilderCli {
    private ResponseBuilderCli() {
    }

    public static String buildSuccess(ExpressionModel expressionModel) {

        return "Expressao: " + expressionModel.getExpression().toString() + ";\nResultado: " + expressionModel.getResult().toString() + " ; \nTamanho maximo da pilha: " + expressionModel.getExpression().size() + ";";
    }

    public static String buildError(String message, String expression) {

        return "[[ ERRO ]]\nExpressao: " + expression + " ; \nErro: " + message + ";";
    }

}
