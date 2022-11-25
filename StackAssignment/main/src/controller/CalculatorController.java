package StackAssignment.main.src.controller;

import StackAssignment.main.src.domain.dto.ExpressionDto;
import StackAssignment.main.src.domain.model.ExpressionModel;
import StackAssignment.main.src.usecases.CalculateExpressionUseCase;

public class CalculatorController {
    public static ExpressionModel execute(ExpressionDto expressionDto) throws Exception {
        ExpressionModel expressionModel = new ExpressionModel(expressionDto);

        double result = CalculateExpressionUseCase.execute(expressionModel);
        expressionModel.setResult(result);

        return expressionModel;
    }
}
