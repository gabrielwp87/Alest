import java.util.Objects;

public class BinarySearchTreeAvlOfInteger {

    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        public Integer element;

        public int balance;

        public Node(Integer element) {
            father = null;
            left = null;
            right = null;
            balance = 0;
            this.element = element;
        }
    }

    // Atributos
    private int count; //contagem do número de nodos
    private Node root; //referência para o nodo raiz

    public BinarySearchTreeAvlOfInteger() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public void add(Integer element) {//Notação: O(n)
        root =  add(root,element,null);
        count++;
        balance();
        checkBalance();
        balance();
    }
    private Node add(Node aux,Integer element,Node father){
        if(aux  == null) {
            Node n =  new Node(element);
            n.father = father;
            return n;
        }

        if(aux.element>element){
            if(aux.left!=null){
                add(aux.left,element,aux);
            }else{
                aux.left =  new Node(element);
                aux.left.father = father;
            }
        }
        else{
            if(aux.right!=null){
                add(aux.right,element,aux);
            }else{
                aux.right =  new Node(element);
                aux.right.father = father;
            }
        }

        return aux;

    }

    public Integer getParent(Integer element) {
        Node aux = searchNodeRef(element,root);
        if(aux.father==null)throw new NodeNotFoundException();
        return aux.father.element;

    }

    public BinarySearchTreeAvlOfInteger clone(){//Notação: O(n)
        LinkedListOfInteger positions = positionsWidth();
        BinarySearchTreeAvlOfInteger aux = new BinarySearchTreeAvlOfInteger();
        for(int i =0 ;i<positions.size();i++){
            aux.add(positions.get(i));
        }
        return aux;
    }

    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            res.add(n.element); //Visita o nodo
            positionsPreAux(n.left, res); //Visita a subárvore da esquerda
            positionsPreAux(n.right, res); //Visita a subárvore da direita
        }
    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsPosAux(n.left, res); //Visita a subárvore da esquerda
            positionsPosAux(n.right, res); //Visita a subárvore da direita
            res.add(n.element); //Visita o nodo
        }
    }

    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsCentralAux(n.left, res); //Visita a subárvore da esquerda
            res.add(n.element); //Visita o nodo
            positionsCentralAux(n.right, res); //Visita a subárvore da direita
        }
    }

    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        Node atual = null;
        LinkedListOfInteger res = new LinkedListOfInteger();
        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                if (atual.left != null) {
                    fila.enqueue(atual.left);
                }
                if (atual.right != null) {
                    fila.enqueue(atual.right);
                }
                res.add(atual.element);
            }
        }
        return res;
    }

    public boolean contains(Integer element) {//Notacao: O(n)
        try{
            searchNodeRef(element,root);
            return true;
        }catch (NodeNotFoundException err){
            return false;
        }
    }

    private Node searchNodeRef(Integer element, Node n) {
        if(n == null)throw new NodeNotFoundException();
        if(n.left==null&&n.right==null)throw new NodeNotFoundException();

        if(n.left!=null&&n.right!=null)
        {
            if(Objects.equals(element, n.left.element))return n.left;
            if(Objects.equals(element, n.right.element))return n.right;

            if(element>n.left.element){
                //procura na direita
                return searchNodeRef(element,n.right);
            }
            else {
                //procura na esquerda
                return searchNodeRef(element,n.left);
            }

        }
        if(n.left!=null){
            if(Objects.equals(element, n.left.element))return n.left;

            if(element>n.left.element){
                //procura na direita
                return searchNodeRef(element,n.left.right);
            }
            else {
                //procura na esquerda
                return searchNodeRef(element,n.left.left);
            }
        }
        if(Objects.equals(element, n.right.element))return n.right;
        if(element>n.right.element){
            //procura na direita
            return searchNodeRef(element,n.right.right);
        }
        else {
            //procura na esquerda
            return searchNodeRef(element,n.right.left);
        }

    }


    public int height(){
            return heigth(root);
    }
    private int heigth(Node n){
        if(n==null)
            return 0;
        if(n.left==null&&n.right==null)
            return 1;
        if(n.left!=null&&n.right==null)
            return 1 + heigth(n.left);
        if(n.left == null)
            return 1 + heigth(n.right);
        return 1 + Math.max(heigth(n.right),heigth(n.left));
    }

    public void balance(){
        balance(root);
    }

    private void balance(Node n){
        if(n == null)
            return;
        if(n.right==null&&n.left==null)
            n.balance = 0;
        else if(n.left == null)
            n.balance = heigth(n.right);
        else if(n.right == null)
            n.balance = -heigth(n.left);
        else{
            int rHeight = heigth(n.right);
            int lHeight = heigth(n.left);
            n.balance =  rHeight-lHeight;
        }

        if(n.left!=null) balance(n.left);
        if(n.right!=null) balance(n.right);

    }


    public void checkBalance(){
       root = checkBalance(root);
    }

    private Node checkBalance(Node n){
        if(n.balance>=2||n.balance<=-2) {
            if (n.balance>=2){
                if(n.balance*n.right.balance>0){
                    //System.out.println("simpleRightRotation");
                    return simpleRightRotation(n);

                }else{
                   //System.out.println("dubleRightRotation");
                    return dubleRightRotation(n);

                }
            }else{
                if(n.balance*n.left.balance>0){
                    //System.out.println("simpleLeftRotation");
                    return simpleLeftRotation(n);

                }else{
                    //System.out.println("dubleLeftRotation");
                    return dubleLeftRotation(n);
                }
            }
        }
        balance(n);
        if(n.left!=null)n.left = checkBalance(n.left);
        if(n.right!=null)n.right = checkBalance(n.right);

        return n;
    }

private Node simpleRightRotation(Node n){
        Node rSun = n.right;
        Node rSunl = null;
        if(n.right!=null&&n.right.left!=null){
                rSunl = n.right.left;
        }

    assert n.right != null;
    rSun.left = n;
    n.right = rSunl;

    return rSun;
}

    private Node simpleLeftRotation(Node n){
        Node lSun = n.left;
        Node lSunr =null;
        if(n.left!=null&&n.left.right!=null){
                lSunr = n.left.right;
        }

        assert n.left != null;
        lSun.right = n;
        n.left = lSunr;

        return lSun;
    }

    private Node dubleRightRotation(Node n){

     Node aux = n.right.left;

     n.right.left = aux.right;
     aux.right = n.right;
     n.right = aux;

     Node newRightSun = n.right;
     n.right = null;
     newRightSun.left = n;

     return  newRightSun;


    }

    private Node dubleLeftRotation(Node n){

    Node aux = n.left.right;

     n.left.right = aux.left;
     aux.left = n.left;
     n.left = aux;

     Node newLeftSun = n.left;
     n.left = null;
     newLeftSun.right = n;
     return newLeftSun;

    }






    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" " + "\n");
        }
        //"[label = " << nodo->hDir << "]" <<endl;
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element +" , "+ nodo.balance +"| <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline
    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }


}
