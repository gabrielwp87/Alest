package src.main.controller;

import src.main.domain.dto.ExpressionDto;
import src.main.domain.model.ExpressionModel;
import src.main.usecases.CalculateExpressionUseCase;

public class CalculatorController {
    public static ExpressionModel execute(ExpressionDto expressionDto) throws Exception {
        System.out.println("CalculatorController - Process started");

        ExpressionModel expressionModel = new ExpressionModel(expressionDto);

        int result = CalculateExpressionUseCase.execute(expressionModel);
        expressionModel.setResult(result);
        System.out.println("CalculatorController - Process finished");

        return expressionModel;
    }
}
