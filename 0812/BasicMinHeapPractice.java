import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BasicMinHeapPractice {

   
    static class MinHeap {
        private final ArrayList<Integer> heap = new ArrayList<>();

    
        public void insert(int val) {
            heap.add(val);
            heapifyUp(heap.size() - 1);
        }

       
        public int extractMin() {
            if (isEmpty()) throw new NoSuchElementException("Heap is empty");
            int min = heap.get(0);
            int lastIdx = heap.size() - 1;
            swap(0, lastIdx);
            heap.remove(lastIdx);
            if (!heap.isEmpty()) heapifyDown(0);
            return min;
        }

        
        public int getMin() {
            if (isEmpty()) throw new NoSuchElementException("Heap is empty");
            return heap.get(0);
        }

        public int size() {
            return heap.size();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

   
        private void heapifyUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap.get(i) < heap.get(parent)) {
                    swap(i, parent);
                    i = parent;
                } else {
                    break;
                }
            }
        }

        private void heapifyDown(int i) {
            int n = heap.size();
            while (true) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int smallest = i;

                if (left < n && heap.get(left) < heap.get(smallest)) {
                    smallest = left;
                }
                if (right < n && heap.get(right) < heap.get(smallest)) {
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

        private void swap(int i, int j) {
            int tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
        }
    }


    public static void main(String[] args) {
        MinHeap h = new MinHeap();

        
        int[] inputs = {15, 10, 20, 8, 25, 5};
        for (int v : inputs) h.insert(v);

      
        System.out.print("extractMin order: ");
        boolean first = true;
        while (!h.isEmpty()) {
            int x = h.extractMin();
            if (!first) System.out.print(", ");
            System.out.print(x);
            first = false;
        }
        System.out.println();
    }
}