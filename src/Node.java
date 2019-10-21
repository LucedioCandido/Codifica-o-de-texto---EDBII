public class Node {
    private int letter;
    private int count;
    private Node left;
    private Node right;

    public Node(int letra, int freq){
        this.letter = letra;
        this.count = freq;
        this.left = null;
        this.right = null;
    }

    public int getCount() {
        return count;
    }

    public int getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        return (char)getLetter() + " " + getCount(); //Sobrescreve o método toString e transforma o valor numerico de letter em caracter e imprime junto de seu contador
    }
}
