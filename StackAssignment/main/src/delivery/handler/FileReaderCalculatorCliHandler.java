package StackAssignment.main.src.delivery.handler;

import StackAssignment.main.src.controller.CalculatorController;
import StackAssignment.main.src.delivery.builder.ResponseBuilderCli;
import StackAssignment.main.src.domain.dto.ExpressionDto;
import StackAssignment.main.src.domain.exceptions.InvalidExpressionException;
import StackAssignment.main.src.domain.model.ExpressionModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderCalculatorCliHandler {
    private FileReaderCalculatorCliHandler() {
    }

    public static void execute(String filePath) {

        try {

            BufferedReader reader;
            Path path1 = Paths.get(filePath);
            try {
                reader = Files.newBufferedReader(path1, Charset.defaultCharset());
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------\n");

                        ExpressionDto expressionDto = new ExpressionDto(line);

                        ExpressionModel expressionModel = CalculatorController.execute(expressionDto);

                        String response = ResponseBuilderCli.buildSuccess(expressionModel);

                        System.out.println(response);
                    } catch (InvalidExpressionException e) {
                        String response = ResponseBuilderCli.buildError(e.getMessage(), e.getExpression());
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
