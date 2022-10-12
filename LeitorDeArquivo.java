import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class LeitorDeArquivo {
    public static void main(String[] args) {
        BufferedReader reader;
        Path path1 = Paths.get("testeDeExpressoes.txt"); //Começar com algo mais simples de testar,
        //depois voltar para o arquivo: expressoes2.txt
        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("--- Inicio expressao");
                String[] v = line.split(" "); // divide a string pelo espaco em branco
                for (String s : v) {
                    System.out.println(s);
                }
                System.out.println("--- Fim expressao");
            }
            reader.close();
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }


        System.out.println("{[()]} ? " + verificaExpressao("{[()]}"));
        System.out.println("{[(]} ? " + verificaExpressao("{[(]}"));
        System.out.println("{[()}} ? " + verificaExpressao("{[()}}"));
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

            if (pilha.isEmpty()) {
                return true;
            }
            else {
                return false;
            }
        }


}