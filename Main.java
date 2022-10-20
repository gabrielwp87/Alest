import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import src.main.controller.CalculatorController;
import src.main.delivery.builder.ResponseBuilder;
import src.main.delivery.handler.FileReaderCalculatorHandler;
import src.main.domain.dto.ExpressionDto;
import src.main.domain.model.ExpressionModel;
import src.main.domain.types.Stack;

public class Main {
    public static void main(String[] args) {
        FileReaderCalculatorHandler.execute("expressoes2.txt");
    }
}