package src.main.domain.model;

import src.main.domain.dto.ExpressionDto;
import src.main.domain.types.Stack;

public class ExpressionModel {
    private Stack expression;

    public ExpressionModel(ExpressionDto expressionDto) {
        this.expression = expressionDto.getExpression();
    }

    public Stack getExpression() {
        return this.expression;
    }
}
