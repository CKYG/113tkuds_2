import java.util.*;

public class StockMaximizer {

    
    public static int maxProfitHeap(int[] prices, int K) {
        if (prices == null || prices.length <= 1 || K <= 0) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int n = prices.length;
        int i = 0;
        while (i < n - 1) {
       
            while (i < n - 1 && prices[i] >= prices[i + 1]) i++;
            int valley = i;

            while (i < n - 1 && prices[i] <= prices[i + 1]) i++;
            int peak = i;

            if (peak > valley) {
                maxHeap.offer(prices[peak] - prices[valley]);
            }
        }

        int profit = 0;
        while (K-- > 0 && !maxHeap.isEmpty()) {
            profit += maxHeap.poll();
        }
        return profit;
    }

    
    public static int maxProfitDP(int[] prices, int K) {
        if (prices == null || prices.length <= 1 || K <= 0) return 0;
        int n = prices.length;

   
        if (K >= n / 2) {
            int free = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) free += prices[i] - prices[i - 1];
            }
            return free;
        }

        int[] dpPrev = new int[n];
        int[] dpCur  = new int[n];

        for (int t = 1; t <= K; t++) {
            int bestBuy = -prices[0]; 
            dpCur[0] = 0;
            for (int i = 1; i < n; i++) {
                dpCur[i] = Math.max(dpCur[i - 1], prices[i] + bestBuy);
                bestBuy = Math.max(bestBuy, dpPrev[i - 1] - prices[i]);
            }
            // 滾動
            int[] tmp = dpPrev; dpPrev = dpCur; dpCur = tmp;
        }
        return dpPrev[n - 1];
    }

    // --- 測試 ---
    public static void main(String[] args) {
        test(new int[]{2,4,1}, 2, 2);
        test(new int[]{3,2,6,5,0,3}, 2, 7);
        test(new int[]{1,2,3,4,5}, 2, 4);

        // 其他自測
        test(new int[]{5,4,3,2,1}, 3, 0); 
        test(new int[]{1,5,3,8}, 1, 7);   
        test(new int[]{1,5,3,8}, 2, 9);  
    }

    private static void test(int[] prices, int K, int expect) {
        int a = maxProfitHeap(prices, K);
        int b = maxProfitDP(prices, K);
        System.out.printf("prices=%s, K=%d | Heap=%d, DP=%d, expect=%d | ok=%b%n",
                Arrays.toString(prices), K, a, b, expect, (a == expect && b == expect));
    }
}