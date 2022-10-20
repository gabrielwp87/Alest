package src.main.domain.dto;

import src.main.domain.exceptions.InvalidExpressionException;
import src.main.domain.types.Stack;
import src.main.domain.validation.ExpressionDtoValidation;

public class ExpressionDto {
    private final Stack expression;

    public ExpressionDto(String expressionString) throws InvalidExpressionException {
        expression = ExpressionDtoValidation.validate(expressionString);
    }

    public Stack getExpression() {
        return this.expression;
    }
}
