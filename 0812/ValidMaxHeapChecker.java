import java.util.Arrays;

public class ValidMaxHeapChecker {


  
    public static int firstViolationIndex(int[] a) {
        if (a == null || a.length <= 1) return -1; 
        int n = a.length;
        int lastNonLeaf = (n - 2) / 2;

        for (int parent = 0; parent <= lastNonLeaf; parent++) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;

            if (left < n && a[left] > a[parent]) return left;
            if (right < n && a[right] > a[parent]) return right;
        }
        return -1; 
    }


    public static boolean isValidMaxHeap(int[] a) {
        return firstViolationIndex(a) == -1;
    }

 
    public static void main(String[] args) {
        int[][] tests = {
            {100, 90, 80, 70, 60, 75, 65},        
            {100, 90, 80, 95, 60, 75, 65},        
            {50},                                 
            {}                                    
        };

        for (int[] arr : tests) {
            int bad = firstViolationIndex(arr);
            boolean ok = (bad == -1);
            System.out.printf("%s → %s", Arrays.toString(arr), ok);
            if (!ok) {
                int parent = (bad - 1) / 2;
                System.out.printf(" (索引%d的%d大於父節點索引%d的%d)",
                        bad, arr[bad], parent, arr[parent]);
            }
            System.out.println();
        }
    }
}