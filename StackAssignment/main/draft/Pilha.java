package StackAssignment.main.draft;/*
Implementação de uma pilha usando estruturas encadeadas, que deve ter exatamente os seguintes métodos,
 conforme visto em aula: push(e), pop(), top(), size(), isEmpty() e clear().
 */

import java.util.EmptyStackException;

public class Pilha {


        // Referencia para o sentinela de inicio da pilha usando estrutura encadeada.
        private Node header;
        // Referencia para o sentinela de fim da pilha usando estrutura encadeada.
        private Node trailer;
        // Contador do numero de elementos da pilha.
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

        public Pilha() {
            header = new Node('0');
            trailer = new Node('0');
            header.next = trailer;
            trailer.prev = header;
            count = 0;
        }

        /**
         * Topo da pilha -> header.next
         * Adiciona um elemento no topo da pilha
         * @param element elemento a ser adicionado
         */
        public void push(Character element) {
            // Primeiro cria o nodo
            Node nodo = new Node(element);
            // Conecta o nodo criado na pilha
            nodo.prev = header;
            nodo.next = header.next;
            // Atualiza os encadeamentos
            header.next.prev = nodo;
            header.next = nodo;
            // Atualiza count
            count++;
        }


        /**
         * Topo da pilha -> header.next
         * Remove o elemento que está no topo da pilha
         * @return o elemento que estava no topo da pilha
         */
        public Character pop() {
            // Primeiro verifica se a pilha está vazia
            if (count <= 0) throw new EmptyStackException();

            Node aux = header.next;
            char elemento = aux.element;
            header.next = aux.next;
            aux.next.prev = header;
            count--;

            return elemento;
        }



        /**
         * Topo da pilha -> header.next
         * Retorna o elemento que se encontra no topo da lista
         * @throws EmptyStackException se a pilha estiver vazia
         */
        public Character top() {
            // Primeiro verifica se a pilha está vazia
            if (count <= 0) throw new EmptyStackException();
            Node aux = header.next;
            return aux.element;
        }

        /**
         * @return o numero de elementos da pilha
         */
        public int size() {
            return count;
        }

        /**
         * @return true se a pilha não contem elementos
         */
        public boolean isEmpty() {
            return (count == 0);
        }

        /**
         * Esvazia a pilha
         */
        public void clear() {

            header.next = trailer;
            trailer.prev = header;
            count = 0;
        }
}
