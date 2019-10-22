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

    public void setRoot(boolean root) {
        this.root = root;
    }

    //Sobrescreve o método toString e transforma o valor
    // numerico de letter em caracter e imprime junto de seu contador
    @Override
    public String toString() {
        return (char)getLetter() + " " + getCount();
    }

    public void insert(Node node) {
        if (node.count < this.count) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.insert(node);
            }
        } else if (node.count > this.count) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.insert(node);
            }
        }
    }
}
