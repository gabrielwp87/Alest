package src.main.domain.dto;

import src.main.domain.types.Stack;
import src.main.domain.validation.ExpressionDtoValidation;

public class ExpressionDto {
    private Stack expression;

    public ExpressionDto(String expression) {
        this.expression = ExpressionDtoValidation.validate(expression);
    }

    public Stack getExpression() {
        return this.expression;
    }
}
