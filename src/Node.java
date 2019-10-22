public class Node {
    private int letter;
    private int count;
    private Node left;
    private Node right;
    private boolean root;
    private boolean leaf;
    private Node pai;

    public Node(int letra, int freq){
        this.letter = letra;
        this.count = freq;
        this.left = null;
        this.right = null;
    }

    public Node(Node copy){
        this.letter = copy.getLetter();
        this.count = copy.getCount();
        this.left = copy.getLeft();
        this.right = copy.getRight();
    }

    public int getCount() {
        return count;
    }

    public int getLetter() {
        return letter;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public Node getPai() {
        return pai;
    }

    @Override
    public String toString() {
        return (char)getLetter() + " " + getCount(); //Sobrescreve o método toString e transforma o valor numerico de letter em caracter e imprime junto de seu contador
    }
}
