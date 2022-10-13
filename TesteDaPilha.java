public class TesteDaStack {
    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println("Recém criada a stack e ela está vazia? " + stack.isEmpty());
        stack.push('g');
        System.out.println("Após adicionar um elemento a stack está vazia? " + stack.isEmpty());
        stack.push('a');
        stack.push('b');
        stack.push('r');
        stack.push('i');
        stack.push('e');
        stack.push('l');

        System.out.println("Tamanho da stack: " + stack.size());

        int tam = stack.size();
        for (int i = 0; i < tam; i++)
            System.out.println("Letra " + (i + 1) + " : " + stack.pop());
        System.out.println("Após usar o método pop() na stack inteira a stack está vazia? " + stack.isEmpty());

        stack.push('g');
        System.out.println("Após adiconado um item, stack está vazia? " + stack.isEmpty());
        stack.clear();
        System.out.println("Após método clear() a stack está vazia? " + stack.isEmpty());

        System.out.println();
        System.out.println("------------------------------------------------");
        Stack stack2 = new Stack();
        System.out.println("Recém criada a stack 2 e ela está vazia? " + stack.isEmpty());
        stack2.push('d');
        System.out.println("Após adicionar um elemento a stack 2 está vazia? " + stack.isEmpty());
        stack2.push('u');
        stack2.push('p');
        stack2.push('l');
        stack2.push('a');
        System.out.println("Tamanho da stack 2: " + stack2.size());

        int tam2 = stack2.size();
        for (int i = 0; i < tam2; i++)
            System.out.println("Letra " + (i + 1) + " : " + stack2.pop());
        System.out.println("Após usar o método pop() na stack inteira a stack 2 está vazia? " + stack2.isEmpty());

        stack2.push('m');
        System.out.println("Após adiconado um item, stack 2 está vazia? " + stack2.isEmpty());
        stack.clear();
        System.out.println("Após método clear() a stack 2 está vazia? " + stack2.isEmpty());
    }
}
