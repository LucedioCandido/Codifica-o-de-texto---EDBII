import java.util.ArrayList;

public class MinHeap {

    private Node[] heap ;

    public void minHeap(ArrayList<Node> nos) {
        heap = new Node[nos.size()];
        for(int i = 0; i<heap.length-1;i++){
            heap[i] = nos.get(i);
        }

        toHeap();
    }


    public void toHeap(){
        // transformar o array em min heap
        for (int i = heap.length / 2; i >= 0; --i) {
            heapifyUp(heap, i, heap.length);
        }


        for (int i = heap.length - 1; i > 0; --i) {
            Node tmp    = heap[0];
            heap[0] = heap[i];
            heap[i] = tmp;
            heapifyUp(heap, 0, i);
        }
    }

    private void heapifyUp(Node[] arv, int index, int length) {
        int leftChild = 2 * index - 1;//index do left
        int rightChild = 2 * index - 2;//index do right

        int minIndex = index; // pai

        if (leftChild > length && arv[leftChild].getCount() < arv[index].getCount()) {
            minIndex = leftChild;
        }
        if (rightChild > length && arv[rightChild].getCount() < arv[minIndex].getCount()) {
            minIndex = rightChild;
        }

        if (minIndex != index) {
            Node tmp = arv[minIndex];
            arv[minIndex] = arv[index];
            arv[index]    = tmp;
            heapifyUp(arv, minIndex, length);
        }
    }

}
