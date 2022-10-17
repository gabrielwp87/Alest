package src.main.delivery.builder;

import src.main.domain.model.ExpressionModel;
import src.main.domain.types.Stack;

public class ResponseBuilder {
    private ResponseBuilder() {
    }

    public static String buildSuccess(ExpressionModel expressionModel) {

        return "Expressao: " + expressionModel.getExpression().toString() + " ; \n Resultado: " + expressionModel.getResult().toString() + " ; \n Tamanho maximo da pilha: " + Integer.toString(expressionModel.getExpression().size()) + " ;";
    }
}
