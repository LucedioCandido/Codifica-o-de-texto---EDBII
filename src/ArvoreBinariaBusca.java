public class ArvoreBinariaBusca {

    private Node raiz = null;

    public boolean isEmpty() {
        return raiz == null;
    }

    public Node getRoot() {
        return raiz;
    }

    public void insert(Node node) {
        if (raiz == null) {
            raiz = node;
            return;
        }
        raiz.insert(node);
    }

    public void insert(Character letter,int count) {
        Node n = new Node(letter,count);
        insert(n);
    }

}
