package src.main.controller;

import src.main.domain.dto.ExpressionDto;
import src.main.domain.model.ExpressionModel;
import src.main.usecases.CalculateExpressionUseCase;

public class CalculatorController {
    public static ExpressionModel execute(ExpressionDto expressionDto) throws Exception {
        ExpressionModel expressionModel = new ExpressionModel(expressionDto);

        double result = CalculateExpressionUseCase.execute(expressionModel);
        expressionModel.setResult(result);

        return expressionModel;
    }
}
