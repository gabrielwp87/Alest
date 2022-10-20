package src.main.delivery.handler;

import src.main.controller.CalculatorController;
import src.main.delivery.builder.ResponseBuilder;
import src.main.domain.dto.ExpressionDto;
import src.main.domain.exceptions.InvalidExpressionException;
import src.main.domain.model.ExpressionModel;
import src.main.infra.implementation.FileReaderImplementation;
import src.main.interfaces.IFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderCalculatorHandler {
    private FileReaderCalculatorHandler() {
    }

    public static void execute(String filePath) {

        try {
            //IFileReader iFileReader = new FileReaderImplementation(filePath);
            //String[] expressions = iFileReader.readAllLines();
            BufferedReader reader;
            Path path1 = Paths.get(filePath);
            try {
                reader = Files.newBufferedReader(path1, Charset.defaultCharset());
                String line = null;
                while ((line = reader.readLine()) != null) {
                    try {
                        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------\n");

                        ExpressionDto expressionDto = new ExpressionDto(line);

                        ExpressionModel expressionModel = CalculatorController.execute(expressionDto);

                        String response = ResponseBuilder.buildSuccess(expressionModel);

                        System.out.println(response);
                    } catch (InvalidExpressionException e) {
                        String response = ResponseBuilder.buildError(e.getMessage(), e.getExpression());
                        System.out.println(response);

                    }

                }
                reader.close();
            } catch (IOException e) {
                System.err.format("Erro na leitura do arquivo: ", e);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
