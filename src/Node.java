public class Node {
    private int letter;
    private int count;
    private Node left;
    private Node right;
    private boolean root;

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

    @Override
    public String toString() {
        return (char)getLetter() + " " + getCount(); //Sobrescreve o método toString e transforma o valor numerico de letter em caracter e imprime junto de seu contador
    }
}
