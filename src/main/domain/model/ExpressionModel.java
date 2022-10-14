package src.main.domain.model;

import src.main.domain.dto.ExpressionDto;
import src.main.domain.types.Stack;
import src.main.utils.StackCopier;

public class ExpressionModel {
    private final Stack expression;

    public ExpressionModel(ExpressionDto expressionDto) {
        this.expression = expressionDto.getExpression();
    }

    public Stack getExpression() {
        return StackCopier.copy(expression);
    }
}
