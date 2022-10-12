public class PilhaTeste2 {
    // Classe interna Node
    private class Node {
        public char element;
        public Node next;

        public Node(Character element) {
            this.element = element;
            next = null;
        }

        public Node(Character element, Node next) {
            this.element = element;
            this.next = next;
        }
    }


    // Referência para o primeiro elemento da lista encadeada.
    private Node head;
    // Referência para o último elemento da lista encadeada.
    private Node tail;
    // Contador para a quantidade de elementos que a lista contem.
    private int count;


    /**
     * Construtor da lista.
     */
    public PilhaTeste2() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void push(char element)  {
        Node n = new Node(element);
        if (head == null) {
            head = n;
        } else {
            n = head.next;
            head = n;
        }
        count++;
    }

    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public char top() {
        if (!isEmpty()) {
            return head.next.element;
        }
        return '0'; // AJEITAR ************************
    }


    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente.
     * @param element o elemento a ser removido
     * @return true se a lista contem o elemento especificado
     */
    public char pop() {
        if (count == 0)
            return '0';  // AJEITAR ************************

        if (count == 1) { // se tem apenas 1 elemento na pilha
            tail = null;
        }
        char exit = head.next.element;
        head = head.next.next;
        count--;
        return exit;
        }


}
