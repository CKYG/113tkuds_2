import java.util.*;

public class M01_BuildHeap {
    static boolean isMax;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        isMax = type.equals("max");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        buildHeap(arr, n);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i != n - 1) System.out.print(" ");
        }
    }

    private static void buildHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largestOrSmallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[largestOrSmallest])) {
            largestOrSmallest = left;
        }
        if (right < n && compare(arr[right], arr[largestOrSmallest])) {
            largestOrSmallest = right;
        }

        if (largestOrSmallest != i) {
            int temp = arr[i];
            arr[i] = arr[largestOrSmallest];
            arr[largestOrSmallest] = temp;
            heapify(arr, n, largestOrSmallest);
        }
    }

    private static boolean compare(int a, int b) {
        return isMax ? a > b : a < b;
    }
}

/*
 * Time Complexity: O(n)
 * 說明: 建堆從最後一個非葉節點開始，每次 heapify 最多 O(log n)，但總成本是 O(n)。
 */
