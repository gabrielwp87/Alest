package src.main.domain.dto;

import src.main.domain.types.Stack;
import src.main.domain.validation.ExpressionDtoValidation;

public class ExpressionDto {
    private Stack expression;

    public ExpressionDto(String expressionString) {
        expression = ExpressionDtoValidation.validate(expressionString);
    }

    public Stack getExpression() {
        return this.expression;
    }
}
