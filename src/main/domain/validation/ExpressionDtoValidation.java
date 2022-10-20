package src.main.domain.validation;

import src.main.domain.enums.Operator;
import src.main.domain.exceptions.InvalidExpressionException;
import src.main.domain.types.Stack;

public class ExpressionDtoValidation {
    private static String reason;

    private ExpressionDtoValidation() {
    }

    public static Stack validate(String expression) throws InvalidExpressionException {
        if (!verificaExpressao(expression)) {
            reason = verificaErroDaExpressao(expression);
            throw new InvalidExpressionException(reason, expression);
        }
        if (!verificaSintaxe(expression)) {
            throw new InvalidExpressionException(reason, expression);
        }
        Stack expressionStack = new Stack();
        for (int i = 0; i < expression.length(); i++) expressionStack.push(expression.charAt(i));

        return expressionStack;
    }

    private static boolean verificaExpressao(String s) {
        Stack pilha = new Stack();

        for (int i = 0; i < s.length(); i++) {
            // Pega cada caractere da string
            char c = s.charAt(i);

            // Verifica se eh um "abre" para empilhar
            if (c == '{' || c == '[' || c == '(') {
                pilha.push(c);
            }

            // Senao, desempilha e verifica se o "par" esta correto
            else {
                if (pilha.isEmpty()) {
                    return false;
                } else if (c == '}' || c == ']' || c == ')') {
                    char aux = pilha.pop();
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
        return pilha.isEmpty();
    }

    private static String showErrorPosition(String s, int position) {
        return s.substring(0, position - 1) + " >>" + s.charAt(position) + "<< " + s.substring(position);
    }

    private static boolean verificaSintaxe(String s) {

        for (int i = 0; i < s.length() - 1; i++) {
            char element = s.charAt(i);
            if (isNumber(element)) {
                int k = i;
                while (isNumber(s.charAt(k)) || isDot(s.charAt(k))) {
                    k++;
                }

                if (isOpen(element)) {
                    reason = "01 - Expressão deve conter um espaço no lugar de um '" + element + "' na posição " + (i + 1) + ". \n" + showErrorPosition(s, i);
                    ;
                    return false;
                }

                if (isClose(element)) {
                    reason = "02 - Expressão deve conter um espaço no lugar de um '" + element + "' na posição " + (i + 1) + ". \n" + showErrorPosition(s, i);
                    ;
                    return false;
                }

                if (isSpace(element)) {
                    return true;
                }

                if (isOperator(element)) {
                    reason = "03 - Expressão deve conter um espaço no lugar de um '" + element + "' na posição " + (i + 1) + ". \n" + showErrorPosition(s, i);
                    ;
                    return false;
                }

            }

            if (isDot(element)) {
                if (!isNumber(s.charAt((i + 1)))) {
                    reason = "04 - Expressão deve conter um numero no lugar de um '" + s.charAt((i + 1)) + "' na posição " + (i + 1) + ". \n" + showErrorPosition(s, (i + 1));
                    ;
                    return false;
                }
                if (i > 0 && !isNumber(s.charAt(i - 1))) {
                    reason = "05 - Expressão deve conter um numero no lugar de um '" + s.charAt(i - 1) + "' na posição " + (i - 1) + ". \n" + showErrorPosition(s, i - 1);
                    ;
                    return false;
                }
            }

            if (isOpen(element)) {
                if (!onlySpaceAllowed(s, i)) return false;
            }

            if (isClose(element)) {
                if (!onlySpaceAllowed(s, i)) return false;
            }

            if (isSpace(element)) {
                if (isSpace(s.charAt((i + 1)))) {
                    reason = "06 - Expressão com expaço duplo identificado na posição " + (i + 1) + ". \n" + showErrorPosition(s, (i + 1));
                    ;
                    return false;
                }
                if (i > 0 && isSpace(s.charAt(i - 1))) {
                    reason = "07 - Expressão com expaço duplo identificado na posição " + (i - 1) + ". \n" + showErrorPosition(s, i - 1);
                    ;
                    return false;
                }
            }

            if (isOperator(element)) {
                if (element != Operator.Subtraction.getOperator()) {

                    if (!isSpace(s.charAt((i + 1)))) {
                        reason = "08 - Expressão deve conter um espaço no lugar de um '" + s.charAt((i + 1)) + "' na posição " + (i + 1) + ". \n" + showErrorPosition(s, (i + 1));
                        ;
                        return false;
                    }
                    if (i > 0 && !isSpace(s.charAt(i - 1))) {
                        reason = "09 - Expressão deve conter um espaço no lugar de um '" + s.charAt(i - 1) + "' na posição " + (i - 1) + ". \n" + showErrorPosition(s, i - 1);

                        return false;
                    }

                    if (i + 2 < s.length() && !isNumber(s.charAt(i + 2)) && !isOpen(s.charAt(i + 2))) {
                        if (s.charAt(i + 2) != Operator.Subtraction.getOperator()) {
                            reason = "10 - Expressão deve conter um numero ou inicio de expressão no lugar de um '" + s.charAt(i + 2) + "' na posição " + (i + 2) + ". \n" + showErrorPosition(s, i + 2);
                            return false;
                        }
                    }

                    if (i > 3 && !isNumber(s.charAt(i - 2)) && !isClose(s.charAt(i - 2))) {
                        reason = "11 - Expressão deve conter um numero ou fim de expressão no lugar de um '" + s.charAt(i - 2) + "' na posição " + (i - 2) + ". \n" + showErrorPosition(s, i - 2);

                        return false;
                    }

                } else {
                    if (i > 0 && !isSpace(s.charAt(i - 1))) {
                        reason = "12 - Expressão deve conter um espaço no lugar de um '" + s.charAt(i - 1) + "' na posição " + (i - 1) + ". \n" + showErrorPosition(s, i - 1);

                        return false;
                    }
                    if (!isSpace(s.charAt((i + 1)))) {
                        if (!isNumber(s.charAt((i + 1)))) {

                            reason = "13 - Expressão deve conter um espaço no lugar de um '" + s.charAt((i + 1)) + "' na posição " + ((i + 1)) + ". \n" + showErrorPosition(s, (i + 1));

                            return false;
                        }

                        if (!isNumber(s.charAt((i + 1))) || !isOpen(s.charAt((i + 1)))) {
                            reason = "14 - Expressão deve conter um numero ou inicio de expressão no lugar de um '" + s.charAt((i + 1)) + "' na posição " + (i + 1) + ". \n" + showErrorPosition(s, (i + 1));

                            return false;
                        }
                    }


                }


            }

        }

        return true;
    }

    private static boolean onlySpaceAllowed(String s, int i) {
        if (i > 0 && !isSpace(s.charAt(i - 1))) {
            reason = "15 - Expressão deve conter um espaço no lugar de um '" + s.charAt(i - 1) + "' na posição " + (i - 1) + ". \n" + showErrorPosition(s, i - 1);
            ;
            return false;
        }

        if (!isSpace(s.charAt((i + 1)))) {
            reason = "16 - Expressão deve conter um espaço no lugar de um '" + s.charAt((i + 1)) + "' na posição " + ((i + 1)) + ". \n" + showErrorPosition(s, (i + 1));
            ;
            return false;
        }
        return true;
    }


    static boolean isNumber(char element) {
        try {
            Integer.parseInt(String.valueOf(element));
            return true;
        } catch (NumberFormatException err) {
            return false;
        }
    }


    static boolean isOperator(char element) {
        return element == Operator.Multiplication.getOperator() || element == Operator.Addition.getOperator() || element == Operator.Division.getOperator() || element == Operator.Exponential.getOperator() || element == Operator.Subtraction.getOperator();
    }

    static boolean isOpen(char element) {
        return element == '[' || element == '{' || element == '(';
    }

    static boolean isClose(char element) {
        return element == ']' || element == '}' || element == ')';
    }

    static boolean isDot(char element) {
        return element == '.';
    }

    static boolean isSpace(char element) {
        return element == ' ';
    }

    private static String verificaErroDaExpressao(String s) {
        Stack pilha = new Stack();
        Stack pilhaInicial = new Stack();
        Stack pilhaChaves = new Stack();
        Stack pilhaColchetes = new Stack();
        Stack pilhaParenteses = new Stack();
        char reverseRight = '0';
        char wrong = '0';
        char right = '0';
        String expression = "ERRO!!!";

        // Primeira verificação, se tudo que abre - {, [ e ( - fecha - }, ] e ).
        for (int i = 0; i < s.length(); i++) {
            // Pega cada caractere da string
            char c = s.charAt(i);


            if (c == '{' || c == '[' || c == '(' || c == '}' || c == ']' || c == ')') {
                pilhaInicial.push(c);
            }
        }

        if (pilhaInicial.size() % 2 != 0) {
            pilhaChaves.clear();
            pilhaColchetes.clear();
            pilhaParenteses.clear();
            //Verificando qual abre-fecha que está faltando
            for (int i = 0; i < s.length(); i++) {
                // Pega cada caractere da string
                char c = s.charAt(i);

                if (c == '{' || c == '}') {
                    pilhaChaves.push(c);

                } else if (c == '[' || c == ']') {
                    pilhaColchetes.push(c);

                } else if (c == '(' || c == ')') {
                    pilhaParenteses.push(c);

                }
            }
            if (pilhaChaves.size() % 2 != 0) {
                expression = "Está falando um }";
            } else if (pilhaColchetes.size() % 2 != 0) {
                expression = "Está falando um ]";
            } else if (pilhaParenteses.size() % 2 != 0) {
                expression = "Está falando um )";
            }
        } else {

            for (int i = 0; i < s.length(); i++) {
                // Pega cada caractere da string
                char c = s.charAt(i);

                // Verifica se eh um "abre" para empilhar
                if (c == '{' || c == '[' || c == '(') {
                    pilha.push(c);
                }

                // Senao, desempilha e verifica se o "par" esta correto
                else {
                    if (c == '}' || c == ']' || c == ')') {
                        char aux = pilha.pop();
                        // Verifica se o "par" está correto
                        if (c == '}' && aux != '{') {
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{') right = '}';
                            if (reverseRight == '[') right = ']';
                            if (reverseRight == '(') right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                        if (c == ']' && aux != '[') {
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{') right = '}';
                            if (reverseRight == '[') right = ']';
                            if (reverseRight == '(') right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                        if (c == ')' && aux != '(') {
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{') right = '}';
                            if (reverseRight == '[') right = ']';
                            if (reverseRight == '(') right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                    }
                }
            }
        }
        return "Expressão: " + s + "\n" + "Erro de sintaxe: " + expression + " na expressão!";
    }
}
