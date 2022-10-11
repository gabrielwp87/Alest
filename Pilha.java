/*
Implementação de uma pilha usando estruturas encadeadas, que deve ter exatamente os seguintes métodos,
 conforme visto em aula: push(e), pop(), top(), size(), isEmpty() e clear().
 */

import java.util.EmptyStackException;

public class Pilha {

    //public class DoubleLinkedListOfInteger {
        // Referencia para o sentinela de inicio da pilha usando estrtura encadeada.
        private Node header;
        // Referencia para o sentinela de fim da pilha usando estrtura encadeada.
        private Node trailer;
        // Contador do numero de elementos da pilha.
        private int count;

        private class Node {
            public Integer element;
            public Node next;
            public Node prev;
            public Node(Integer e) {
                element = e;
                next = null;
                prev = null;
            }
        }

        public Pilha() {
            header = new Node(null);
            trailer = new Node(null);
            header.next = trailer;
            trailer.prev = header;
            count = 0;
        }

        /**
         * Adiciona um elemento no topo da pilha
         * @param element elemento a ser adicionado
         */
        public void push(Integer element) {
            // Primeiro cria o nodo
            Node nodo = new Node(element);
            // Conecta o nodo criado na pilha
            nodo.prev = trailer.prev;
            nodo.next = trailer;
            // Atualiza os encadeamentos
            trailer.prev.next = nodo;
            trailer.prev = nodo;
            // Atualiza count
            count++;
        }


        /**
         * Remove o elemento que está no topo da pilha
         * @return o elemento que estava no topo da pilha
         */
        public Integer pop() {
            // Primeiro verifica se a pilha está vazia
            if (count <= 0) throw new EmptyStackException();

            Node aux = trailer.prev;
            Integer elemento = aux.element;
            aux.prev.next = aux.next;
            aux.next.prev = aux.prev;
            count--;

            return elemento;
        }



        /**
         * Retorna o elemento que se encontra no topo da lista
         * @throws EmptyStackException se a pilha estiver vazia
         */
        public Integer top() {
            // Primeiro verifica se a pilha está vazia
            if (count <= 0) throw new EmptyStackException();
            Node aux = trailer.prev;

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
            header = new Node(null);
            trailer = new Node(null);
            header.next = trailer;
            trailer.prev = header;
            count = 0;
        }
}
