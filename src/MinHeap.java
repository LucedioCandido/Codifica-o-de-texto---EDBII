public class MinHeap {
    private int[] tree_heap;
    private int tamanho;
    private int tamMaximo;

    private static final int FRONT = 1;

    public MinHeap(int tamMaximo) {
        this.tamMaximo = tamMaximo;
        this.tamanho = 0;
        tree_heap = new int[this.tamMaximo + 1];
        tree_heap[0] = Integer.MIN_VALUE;
    }

    // Function to return the position of 
    // the parent for the node currently 
    // at pos 
    private int parent(int pos) {
        return pos / 2;
    }

    // Function to return the position of the 
    // left child for the node currently at pos 
    private int leftChild(int pos) {
        return (2 * pos);
    }

    // Function to return the position of 
    // the right child for the node currently 
    // at pos 
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    // Function that returns true if the passed 
    // node is a leaf node 
    private boolean isLeaf(int pos) {
        if (pos >= (tamanho / 2) && pos <= tamanho) {
            return true;
        }
        return false;
    }

    // Function to swap two nodes of the heap 
    private void swap(int fpos, int spos) {
        int tmp;
        tmp = tree_heap[fpos];
        tree_heap[fpos] = tree_heap[spos];
        tree_heap[spos] = tmp;
    }

    // Function to heapify the node at pos 
    private void minHeapify(int pos) {

        // If the node is a non-leaf node and greater 
        // than any of its child 
        if (!isLeaf(pos)) {
            if (tree_heap[pos] > tree_heap[leftChild(pos)]
                    || tree_heap[pos] > tree_heap[rightChild(pos)]) {

                // Swap with the left child and heapify 
                // the left child 
                if (tree_heap[leftChild(pos)] < tree_heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify 
                // the right child 
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    // Function to insert a node into the heap 
    public void insert(int element) {
        if (tamanho >= tamMaximo) {
            return;
        }
        tree_heap[++tamanho] = element;
        int current = tamanho;

        while (tree_heap[current] < tree_heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Function to print the contents of the heap 
    public void print() {
        for (int i = 1; i <= tamanho / 2; i++) {
            System.out.print(" PARENT : " + tree_heap[i]
                    + " LEFT CHILD : " + tree_heap[2 * i]
                    + " RIGHT CHILD :" + tree_heap[2 * i + 1]);
            System.out.println();
        }
    }

    // Function to build the min heap using 
    // the minHeapify 
    public void minHeap() {
        for (int pos = (tamanho / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    // Function to remove and return the minimum 
    // element from the heap 
    public int remove() {
        int popped = tree_heap[FRONT];
        tree_heap[FRONT] = tree_heap[tamanho--];
        minHeapify(FRONT);
        return popped;
    }
}

