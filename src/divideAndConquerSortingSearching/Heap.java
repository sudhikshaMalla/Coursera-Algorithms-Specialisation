package src.divideAndConquerSortingSearching;

public class Heap {
    private static int[] heap;
    private static int size;
    private static int maxSize;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heap = new int[this.maxSize];
    }

    public static void main(String[] args) {
        Heap minHeap = new Heap(15);

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        printHeap();

        minHeap.remove();
        printHeap();

        minHeap.remove();
        printHeap();

        insert(45);
        printHeap();
    }

    private static void insert(int element) {
        if(size >= maxSize) {
            System.out.println("Heap is full. Element cannot be added to heap.");
        }
        heap[size] = element;

        int current = size;
        while(heap[current]<heap[parent(current)]) {
            swap(current,parent(current));
            current=parent(current);
        }

        ++size;
    }

    private static int parent(int position) {
        return (position-1)/2;
    }

    public static void swap(int position1, int position2) {
        int temp = heap[position1];
        heap[position1] = heap[position2];
        heap[position2] = temp;
    }

    public int remove()
    {
        int popped = heap[0];
        heap[0] = heap[--size];
        minHeapify(0);

        return popped;
    }

    private static void minHeapify(int position) {
        if(!isLeaf(position)) {
            int swapPos = heap[leftChild(position)]<heap[rightChild(position)]?leftChild(position):rightChild(position);

            if(heap[position]>heap[leftChild(position)] || heap[position]> heap[rightChild(position)]){
                swap(position,swapPos);
                minHeapify(swapPos);
            }
        }
    }

    private static boolean isLeaf(int position) {
        if((2*position)+1 <= size) {
            return false;
        }
        return true;
    }

    private static int leftChild(int position) {
        return (2*position)+1;
    }

    private static int rightChild(int position) {
        return leftChild(position)+1;
    }

    private static void printHeap() {
        for(int i=0; i<size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}