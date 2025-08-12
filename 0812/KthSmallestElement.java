import java.util.*;
import java.util.stream.Collectors;

public class KthSmallestElement {

    
    public static int kthSmallestUsingMaxHeapOfSizeK(int[] arr, int K) {
        check(arr, K);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // Max Heap
        for (int x : arr) {
            if (maxHeap.size() < K) {
                maxHeap.offer(x);
            } else if (x < maxHeap.peek()) {
                maxHeap.poll();      
                maxHeap.offer(x);
            }
        }
        return maxHeap.peek(); 
    }

   
    public static int kthSmallestUsingMinHeapExtractK(int[] arr, int K) {
        check(arr, K);
     
        List<Integer> data = Arrays.stream(arr).boxed().collect(Collectors.toList());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(data); // Min Heap：線性建堆
        for (int i = 1; i < K; i++) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    private static void check(int[] arr, int K) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("陣列不可為空");
        if (K < 1 || K > arr.length) throw new IllegalArgumentException("K 必須介於 1..n");
    }


    public static void main(String[] args) {
        int[] A = {7, 10, 4, 3, 20, 15}; int k1 = 3; // ans 7
        int[] B = {1};                   int k2 = 1; // ans 1
        int[] C = {3, 1, 4, 1, 5, 9, 2, 6}; int k3 = 4; // ans 3

        runCase(A, k1);
        runCase(B, k2);
        runCase(C, k3);
    }

    private static void runCase(int[] arr, int k) {
        int a = kthSmallestUsingMaxHeapOfSizeK(arr, k);
        int b = kthSmallestUsingMinHeapExtractK(arr, k);
        System.out.println("陣列: " + Arrays.toString(arr) + ", K=" + k);
        System.out.println("方法1(MaxHeap大小K): " + a);
        System.out.println("方法2(MinHeap取K次): " + b);
        System.out.println("一致性: " + (a == b));
        System.out.println("----");
    }
}
