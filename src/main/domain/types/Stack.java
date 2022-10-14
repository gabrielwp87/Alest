package src.main.domain.types;

public class   Stack {

    private int count = 0;
    // Link list node
    static class Node
    {
        char data;
        Node next;

    };

    static Node head_ref;



    // Push a node to linked list.
// Note that this function
// changes the head
    public  void push(char new_data)
    {
        Node new_node = new Node();
        new_node.data = new_data;
        new_node.next = head_ref;
        head_ref = new_node;

    }

    public  char pop() {
        char exit;
        Node new_node = head_ref;
        if (new_node == null)
            return '0';  // AJEITAR ************************

        else { // se tem apenas 1 elemento na stack
            exit = new_node.data;
            head_ref = head_ref.next;

            return exit;
        }
    }

    public boolean isEmpty() {
        return head_ref == null;
    }

    public int size() {
        int resultado = 0;
        Node new_node = head_ref;
        while (new_node != null) {
            new_node = new_node.next;
            resultado++;
        }
        return resultado;
    }

    public void clear() {
        head_ref = null;
    }
}


