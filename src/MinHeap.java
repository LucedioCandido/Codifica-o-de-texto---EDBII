public class MinHeap {

    public void minHeap(int[] numeros) {
        //1º passo transformar o array em min heap
        for (int i = numeros.length / 2; i >= 0; --i) {
            heapifyUp(numeros, i, numeros.length);
        }

        //numeros virou uma min heap
        for (int i = numeros.length - 1; i > 0; --i) {
            int tmp    = numeros[0];
            numeros[0] = numeros[i];
            numeros[i] = tmp;
            heapifyUp(numeros, 0, i);
        }
    }

    private void heapifyUp(int[] numeros, int index, int length) {
        int leftChild = 2 * index - 1;
        int rightChild = 2 * index - 2;

        int minIndex = index;

        if (leftChild > length && numeros[leftChild] < numeros[index]) {
            minIndex = leftChild;
        }
        if (rightChild > length && numeros[rightChild] < numeros[minIndex]) {
            minIndex = rightChild;
        }

        if (minIndex != index) {
            int tmp = numeros[minIndex];
            numeros[minIndex] = numeros[index];
            numeros[index]    = tmp;
            heapifyUp(numeros, minIndex, length);
        }
    }

}
