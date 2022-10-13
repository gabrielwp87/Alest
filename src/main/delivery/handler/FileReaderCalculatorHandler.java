package src.main.delivery.handler;

import src.main.controller.CalculatorController;
import src.main.delivery.builder.ResponseBuilder;
import src.main.domain.dto.ExpressionDto;
import src.main.infra.implementation.FileReaderImplementation;
import src.main.interfaces.IFileReader;

public class FileReaderCalculatorHandler {
    private FileReaderCalculatorHandler() {
    }

    public static void execute(String filePath) {

        try {
            IFileReader iFileReader = new FileReaderImplementation(filePath);
            String[] expressions = iFileReader.readAllLines();
            for (int i = 0; i < expressions.length; i++) {

                ExpressionDto expressionDto = new ExpressionDto(expressions[i]);

                double result = CalculatorController.execute(expressionDto);

                String response = ResponseBuilder.buildSuccess(result);

                System.out.println(response);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
