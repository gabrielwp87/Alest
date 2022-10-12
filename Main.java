import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        Path path1 = Paths.get("expressoes2.txt"); //Começar com algo mais simples de testar,
        //depois voltar para o arquivo: expressoes2.txt
        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;
            while ((line = reader.readLine()) != null) {

                System.out.println("Expressão do arquivo é válida? " + verificaExpressao(line)); //tirar

                if (!verificaExpressao(line)) {
                    System.out.println(verificaErroDaExpressao(line));
                }
//                 else {
//                    System.out.println(Calculator(line));
//                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }


//        System.out.println("{[()]} ? " + verificaExpressao("{[()]}"));
//        System.out.println("{[(2)]} ? " + verificaExpressao("{[(2)]}"));
//        System.out.println("{[(2)]} ? " + verificaExpressao("{ [ ( 2 ) ] }"));
//        System.out.println("{[(2)]} ? " + verificaExpressao("{ [ ( 2  + 2 ) ] }"));
//        System.out.println("{[(2)]} ? " + verificaExpressao("{ 2 * [ 2 * ( 2  + 2 ) * 2 ] * 2 }"));
//        System.out.println("{[(]} ? " + verificaExpressao("{[(]}"));
//        System.out.println("{[()}} ? " + verificaExpressao("{[()}}"));
//
//        System.out.println("{[(]} ? " + verificaErroDaExpressao("{[(]}"));
//        System.out.println("{[()}} ? " + verificaErroDaExpressao("{[()}}"));

    }

        public static boolean verificaExpressao (String s){
            Pilha pilha = new Pilha();

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
                    }
                    else if (c == '}' || c == ']' || c == ')') {
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

        public static String verificaErroDaExpressao(String s) {
            Pilha pilha = new Pilha();
            Pilha pilhaInicial = new Pilha();
            boolean problem = false;
            boolean braceProblem = false;
            boolean bracketProblem = false;
            boolean parenthesisProblem = false;
            char reverseRight = '0';
            char wrong = '0';
            char right = '0';
            String expression = "ERRO!!!";

            //Primeira verificação, se temos pares de {}, [] ou ()
            for (int i = 0; i < s.length(); i++) {
                // Pega cada caractere da string
                char c = s.charAt(i);

                if (c == '{' || c == '}') {
                    pilhaInicial.push(c);
                    if (pilhaInicial.size() % 2 != 0) {
                        expression = "Está falando um } na expressão!";
                    }
                }
                if (c == '[' || c == ']') {
                    pilhaInicial.push(c);
                    if (pilhaInicial.size() % 2 != 0) {
                        expression = "Está falando um ] na expressão!";
                    }
                }
                if (c == '(' || c == ')') {
                    pilhaInicial.push(c);
                    if (pilhaInicial.size() % 2 != 0) {
                        expression = "Está falando um ) na expressão!";

                    }
                }
            }



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
                            problem = true;
                            braceProblem = true;
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{') right = '}';
                            if (reverseRight == '[') right = ']';
                            if (reverseRight == '(') right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                        if (c == ']' && aux != '[') {
                            problem = true;
                            bracketProblem = true;
                            reverseRight = aux;
                            wrong = c;
                            if (reverseRight == '{') right = '}';
                            if (reverseRight == '[') right = ']';
                            if (reverseRight == '(') right = ')';
                            expression = wrong + " no lugar de " + right;
                            break;
                        }
                        if (c == ')' && aux != '(') {
                            problem = true;
                            parenthesisProblem = true;
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
//            if (reverseRight == '{') right = '}';
//            if (reverseRight == '[') right = ']';
//            if (reverseRight == '(') right = ')';



        return "Expressão: " + s + "\n" + "Erro de sintaxe: " + expression + "\n" +
                "tamanho da pilha que sobrou: " + pilha.size();


        }


}