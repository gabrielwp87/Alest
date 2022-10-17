package src.main.delivery.handler;

import src.main.controller.CalculatorController;
import src.main.delivery.builder.ResponseBuilder;
import src.main.domain.dto.ExpressionDto;
import src.main.domain.model.ExpressionModel;
import src.main.infra.implementation.FileReaderImplementation;
import src.main.interfaces.IFileReader;

public class FileReaderCalculatorHandler {
    private FileReaderCalculatorHandler() {
    }

    public static void execute(String filePath) {

        try {
            IFileReader iFileReader = new FileReaderImplementation(filePath);
            String[] expressions = iFileReader.readAllLines();
            for (String expression : expressions) {

                ExpressionDto expressionDto = new ExpressionDto(expression);

                ExpressionModel expressionModel = CalculatorController.execute(expressionDto);

                String response = ResponseBuilder.buildSuccess(expressionModel);

                System.out.println(response);
            }

        } catch (Exception e) {
            // TODO: handle exception

        }

    }
}
