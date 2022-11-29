
public class Main {
    public static void main(String[] args) {

    BinarySearchTreeAvlOfInteger avl = new BinarySearchTreeAvlOfInteger();
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        avl.add(8);
        avl.add(9);

        System.out.println("Altura da arvore: "+avl.height());

       avl.GeraDOT();
       avl.clear();

       avl.add(9);
       avl.add(8);
       avl.add(7);
       avl.add(6);
       avl.add(5);
       avl.add(4);
       avl.add(3);
       avl.add(2);
       avl.add(1);


        System.out.println("Conteudo da arvore caminhamento central: \n"+avl.positionsCentral());

        BinarySearchTreeAvlOfInteger clone = avl.clone();
        clone.GeraDOT();
    }
}