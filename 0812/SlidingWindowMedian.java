import java.util.*;


public class SlidingWindowMedian {

    
    private PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
 
    private PriorityQueue<Integer> upper = new PriorityQueue<>();

    
    private Map<Integer, Integer> delayed = new HashMap<>();

   
    private int sizeLower = 0;
    private int sizeUpper = 0;

   
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new double[0];
        int n = nums.length;
        if (n == 0 || k > n) return new double[0];

        double[] ans = new double[n - k + 1];

       
        for (int i = 0; i < k; i++) addNum(nums[i]);
        ans[0] = getMedian(k);

      
        for (int i = k; i < n; i++) {
            addNum(nums[i]);
            removeNum(nums[i - k]);
            ans[i - k + 1] = getMedian(k);
        }
        return ans;
    }

   
    private void addNum(int x) {
        if (lower.isEmpty() || x <= lower.peek()) {
            lower.offer(x);
            sizeLower++;
        } else {
            upper.offer(x);
            sizeUpper++;
        }
        rebalance();
    }

   
    private void removeNum(int x) {
     
        delayed.put(x, delayed.getOrDefault(x, 0) + 1);

        if (!lower.isEmpty() && x <= lower.peek()) {
            sizeLower--;
            
            if (!lower.isEmpty() && lower.peek() == x) prune(lower);
        } else {
            sizeUpper--;
            if (!upper.isEmpty() && upper.peek() == x) prune(upper);
        }
        rebalance();
    }


    private void rebalance() {
        if (sizeLower > sizeUpper + 1) {
           
            upper.offer(lower.poll());
            sizeLower--;
            sizeUpper++;
            prune(lower);
        } else if (sizeUpper > sizeLower) {
           
            lower.offer(upper.poll());
            sizeUpper--;
            sizeLower++;
            prune(upper);
        }
    }


    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int x = heap.peek();
            Integer cnt = delayed.get(x);
            if (cnt == null || cnt == 0) break;
           
            heap.poll();
            if (cnt == 1) delayed.remove(x);
            else delayed.put(x, cnt - 1);
        }
    }

  
    private double getMedian(int k) {
        if (k % 2 == 1) {
            return lower.peek();
        } else {
           
            return ((double) lower.peek() + (double) upper.peek()) / 2.0;
        }
    }
    public static void main(String[] args) {
        SlidingWindowMedian solver = new SlidingWindowMedian();

        int[] A = {1,3,-1,-3,5,3,6,7}; int k1 = 3;
        double[] res1 = solver.medianSlidingWindow(A, k1);
        System.out.println(format(res1)); 

        int[] B = {1,2,3,4}; int k2 = 2;
        double[] res2 = solver.medianSlidingWindow(B, k2);
        System.out.println(format(res2)); 
    }

    
    private static String format(double[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            double v = arr[i];
            if (Math.abs(v - Math.rint(v)) < 1e-9) sb.append((long)Math.rint(v));
            else sb.append(trim(v));
            if (i + 1 < arr.length) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    private static String trim(double v) {
        
        return String.format(Locale.ROOT, "%.10f", v).replaceAll("(\\.\\d*?[1-9])0+$", "$1").replaceAll("\\.0+$", "");
    }
}