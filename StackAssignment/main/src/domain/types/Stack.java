package StackAssignment.main.src.domain.types;/*
Implementação de uma stack usando estruturas encadeadas, que deve ter exatamente os seguintes métodos,
 conforme visto em aula: push(e), pop(), top(), size(), isEmpty() e clear().
 */

import java.util.EmptyStackException;

public class Stack {


    // Referencia para o sentinela de inicio da stack usando estrutura encadeada.
    private Node header;
    // Referencia para o sentinela de fim da stack usando estrutura encadeada.
    private Node trailer;
    // Contador do numero de elementos da stack.
    private int count;


    private class Node {
        public char element;
        public Node next;
        public Node prev;

        public Node(char e) {
            element = e;
            next = null;
            prev = null;
        }
    }

    public Stack() {
        header = new Node('0');
        trailer = new Node('0');
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    /**
     * Topo da stack -> header.next
     * Adiciona um elemento no topo da stack
     *
     * @param element elemento a ser adicionado
     */
    public void push(Character element) {
        // Primeiro cria o nodo
        Node nodo = new Node(element);
        // Conecta o nodo criado na stack
        nodo.prev = header;
        nodo.next = header.next;
        // Atualiza os encadeamentos
        header.next.prev = nodo;
        header.next = nodo;
        // Atualiza count
        count++;
    }


    /**
     * Topo da stack -> header.next
     * Remove o elemento que está no topo da stack
     *
     * @return o elemento que estava no topo da stack
     */
    public Character pop() {
        // Primeiro verifica se a stack está vazia
        if (count <= 0) throw new EmptyStackException();

        Node aux = header.next;
        char elemento = aux.element;
        header.next = aux.next;
        aux.next.prev = header;
        count--;

        return elemento;
    }


    /**
     * Topo da stack -> header.next
     * Retorna o elemento que se encontra no topo da lista
     *
     * @throws EmptyStackException se a stack estiver vazia
     */
    public Character top() {
        // Primeiro verifica se a stack está vazia
        if (count <= 0) throw new EmptyStackException();
        Node aux = header.next;
        return aux.element;
    }

    /**
     * @return o numero de elementos da stack
     */
    public int size() {
        return count;
    }

    /**
     * @return true se a stack não contem elementos
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Esvazia a stack
     */
    public void clear() {

        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    public static Stack clone(Stack stack) {
        Stack cpy = new Stack();
        Stack backup = new Stack();

        while (!stack.isEmpty()) {
            backup.push(stack.pop());
        }

        while (!backup.isEmpty()) {
            char element = backup.pop();
            cpy.push(element);
            stack.push(element);

        }

        return cpy;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        Stack backup = new Stack();

        while (!isEmpty()) {
            backup.push(pop());
        }

        while (!backup.isEmpty()) {
            char element = backup.pop();
            string.append(element);
            push(element);

        }

        return string.toString();
    }


    public void flip() {
        char[] stackArray = new char[count];
        int i = 0;
        while (!isEmpty()) {
            stackArray[i] = pop();
            i++;
        }

        for (int k = 0; k < i; k++) {
            push(stackArray[k]);
        }

    }

}