package minheap;

public class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public void insert(int numberToInsert) {
        if (size >= heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = numberToInsert;
        bubbleUp(size);
        size++;
    }

    public void delete(int numberToDelete) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (heap[i] == numberToDelete) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("Value not found");
        }

        heap[index] = heap[size - 1];
        size--;
        bubbleUp(index);
        bubbleDown(index);
    }

    public boolean lookup(int numberToFind) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == numberToFind) {
                return true;
            }
        }
        return false;
    }

    private void bubbleUp(int i) {
        while (i > 0) {
            int parent = parent(i);
            if (heap[i] < heap[parent]) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    private void bubbleDown(int i) {
        while (true) {
            int smallest = i;
            int left  = leftChild(i);
            int right = rightChild(i);

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i]  = heap[j];
        heap[j]  = temp;
    }

    private int leftChild(int i)  { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
}