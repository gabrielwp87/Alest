package StackAssignment.main.src.domain.dto;

import StackAssignment.main.src.domain.exceptions.InvalidExpressionException;
import StackAssignment.main.src.domain.types.Stack;
import StackAssignment.main.src.domain.validation.ExpressionDtoValidation;

public class ExpressionDto {
    private final Stack expression;

    public ExpressionDto(String expressionString) throws InvalidExpressionException {
        expression = ExpressionDtoValidation.validate(expressionString);
    }

    public Stack getExpression() {
        return this.expression;
    }
}
