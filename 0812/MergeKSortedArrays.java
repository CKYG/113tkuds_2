import java.util.*;

public class MergeKSortedArrays {


    static class Node {
        int val, arrIdx, elemIdx;
        Node(int v, int ai, int ei) { val = v; arrIdx = ai; elemIdx = ei; }
    }

 
    public static int[] merge(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return new int[0];

  
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        int total = 0;

      
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length > 0) {
                pq.offer(new Node(arrays[i][0], i, 0));
                total += arrays[i].length;
            }
        }
        if (total == 0) return new int[0];

        int[] result = new int[total];
        int idx = 0;

        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            result[idx++] = cur.val;

            int nextIdx = cur.elemIdx + 1;
            int arrI = cur.arrIdx;
            if (nextIdx < arrays[arrI].length) {
                pq.offer(new Node(arrays[arrI][nextIdx], arrI, nextIdx));
            }
        }
        return result;
    }

    
    public static void main(String[] args) {
        int[][] t1 = { {1,4,5}, {1,3,4}, {2,6} };
        int[][] t2 = { {1,2,3}, {4,5,6}, {7,8,9} };
        int[][] t3 = { {1}, {0} };
        int[][] t4 = { {}, {}, {} };
        int[][] t5 = { null, {2,2}, {}, {1} };

        System.out.println(Arrays.toString(merge(t1))); // [1,1,2,3,4,4,5,6]
        System.out.println(Arrays.toString(merge(t2))); // [1,2,3,4,5,6,7,8,9]
        System.out.println(Arrays.toString(merge(t3))); // [0,1]
        System.out.println(Arrays.toString(merge(t4))); // []
        System.out.println(Arrays.toString(merge(t5))); // [1,2,2]
    }
}