package StackAssignment.main.draft;

public class TesteDaPilha {
    public static void main(String[] args) {
        Pilha pilha = new Pilha();

        System.out.println("Recém criada a pilha e ela está vazia? " + pilha.isEmpty());
        pilha.push('g');
        System.out.println("Após adicionar um elemento a pilha está vazia? " + pilha.isEmpty());
        pilha.push('a');
        pilha.push('b');
        pilha.push('r');
        pilha.push('i');
        pilha.push('e');
        pilha.push('l');

        System.out.println("Tamanho da pilha: " + pilha.size());
        System.out.println("O que há no topo da pilha: " + pilha.top());

        int tam = pilha.size();
        for (int i = 0; i < tam; i++)
            System.out.println("Letra " + (i + 1) + " : " + pilha.pop());
        System.out.println("Após usar o método pop() na pilha inteira a pilha está vazia? " + pilha.isEmpty());

        pilha.push('g');
        System.out.println("Após adiconado um item, pilha está vazia? " + pilha.isEmpty());
        pilha.clear();
        System.out.println("Após método clear() a pilha está vazia? " + pilha.isEmpty());

        System.out.println();
        System.out.println("------------------------------------------------");

        Pilha pilha2 = new Pilha();
        System.out.println("Recém criada a pilha 2 e ela está vazia? " + pilha.isEmpty());
        pilha2.push('d');
        System.out.println("Após adicionar um elemento a pilha 2 está vazia? " + pilha.isEmpty());
        pilha2.push('u');
        pilha2.push('p');
        pilha2.push('l');
        pilha2.push('a');
        System.out.println("Tamanho da pilha 2: " + pilha2.size());
        System.out.println("O que há no topo da pilha: " + pilha2.top());


        int tam2 = pilha2.size();
        for (int i = 0; i < tam2; i++)
            System.out.println("Letra " + (i + 1) + " : " + pilha2.pop());
        System.out.println("Após usar o método pop() na pilha inteira a pilha 2 está vazia? " + pilha2.isEmpty());

        pilha2.push('m');
        System.out.println("Após adiconado um item, pilha 2 está vazia? " + pilha2.isEmpty());
        pilha.clear();
        System.out.println("Após método clear() a pilha 2 está vazia? " + pilha2.isEmpty());
    }
}
