import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import src.main.controller.CalculatorController;
import src.main.delivery.builder.ResponseBuilder;
import src.main.domain.dto.ExpressionDto;
import src.main.domain.model.ExpressionModel;
import src.main.domain.types.Stack;

public class Main {
    public static void main(String[] args) {
        try {
            ExpressionDto expressionDto = new ExpressionDto("{ ( 5 + 12 ) + [ ( 10 - 8 ) ^ 3 ] }");
            ExpressionModel expressionModel = CalculatorController.execute(expressionDto);
            String response = ResponseBuilder.buildSuccess(expressionModel);
            System.out.println(response);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static boolean verificaExpressao(String s) {
        Stack stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            // Pega cada caractere da string
            char c = s.charAt(i);

            // Verifica se eh um "abre" para emstackr
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }

            // Senao, desemstack e verifica se o "par" esta correto
            else {
                if (stack.isEmpty()) {
                    return false;
                } else if (c == '}' || c == ']' || c == ')') {
                    char aux = stack.pop();
                    // Verifica se o "par" está correto
                    if (c == '}' && aux != '{')
                        return false;
                    if (c == ']' && aux != '[')
                        return false;
                    if (c == ')' && aux != '(')
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static String verificaErroDaExpressao(String s) {
        Stack stack = new Stack();
        Stack stackInicial = new Stack();
        Stack stackChaves = new Stack();
        Stack stackColchetes = new Stack();
        Stack stackParenteses = new Stack();
        boolean problem = false;
        boolean braceProblem = false;
        boolean bracketProblem = false;
        boolean parenthesisProblem = false;
        char reverseRight = '0';
        char wrong = '0';
        char right = '0';
        String expression = "ERRO!!!";

        // Primeira verificação, se tudo que abre - {, [ e ( - fecha - }, ] e ).
        for (int i = 0; i < s.length(); i++) {
            // Pega cada caractere da string
            char c = s.charAt(i);

            if (c == '{' || c == '[' || c == '(' || c == '}' || c == ']' || c == ')') {
                stackInicial.push(c);
            }
        }

        if (stackInicial.size() % 2 != 0) {
            stackChaves.clear();
            stackColchetes.clear();
            stackParenteses.clear();
            // Verificando qual abre-fecha que está faltando
            for (int i = 0; i < s.length(); i++) {
                // Pega cada caractere da string
                char c = s.charAt(i);

                if (c == '{' || c == '}') {
                    stackChaves.push(c);

                } else if (c == '[' || c == ']') {
                    stackColchetes.push(c);

                } else if (c == '(' || c == ')') {
                    stackParenteses.push(c);

                }
            }
            if (stackChaves.size() % 2 != 0) {
                expression = "Está falando um } na expressão!" + stackChaves.size();
            } else if (stackColchetes.size() % 2 != 0) {
                expression = "Está falando um ] na expressão!" + stackColchetes.size();
            } else if (stackParenteses.size() % 2 != 0) {
                expression = "Está falando um ) na expressão!" + stackParenteses.size();
            }
        } else {

            for (int i = 0; i < s.length(); i++) {
                // Pega cada caractere da string
                char c = s.charAt(i);

                // Verifica se eh um "abre" para emstackr
                if (c == '{' || c == '[' || c == '(') {
                    stack.push(c);
                }

                // Senao, desemstack e verifica se o "par" esta correto
                else {
                    if (c == '}' || c == ']' || c == ')') {
                        char aux = stack.pop();
                        // Verifica se o "par" está correto
                        if (c == '}' && aux != '{') {
                            problem = true;
                            braceProblem = true;
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{')
                                right = '}';
                            if (reverseRight == '[')
                                right = ']';
                            if (reverseRight == '(')
                                right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                        if (c == ']' && aux != '[') {
                            problem = true;
                            bracketProblem = true;
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{')
                                right = '}';
                            if (reverseRight == '[')
                                right = ']';
                            if (reverseRight == '(')
                                right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                        if (c == ')' && aux != '(') {
                            problem = true;
                            parenthesisProblem = true;
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{')
                                right = '}';
                            if (reverseRight == '[')
                                right = ']';
                            if (reverseRight == '(')
                                right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                    }
                }
            }
        }
        // if (reverseRight == '{') right = '}';
        // if (reverseRight == '[') right = ']';
        // if (reverseRight == '(') right = ')';

        return "Expressão: " + s + "\n" + "Erro de sintaxe: " + expression + "\n" +
                "tamanho da stack que sobrou: " + stack.size();

    }

}