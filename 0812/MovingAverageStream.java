import java.util.*;

public class MovingAverageStream {

    static class MovingAverage {
        private final int k;
        private long sum = 0;
        private int idx = 0;
        private final ArrayDeque<int[]> window = new ArrayDeque<>();
        private final ArrayDeque<int[]> minDQ = new ArrayDeque<>();
        private final ArrayDeque<int[]> maxDQ = new ArrayDeque<>();

        private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Integer> upper = new PriorityQueue<>();
        private final Map<Integer, Integer> delayed = new HashMap<>();
        private int sizeLower = 0, sizeUpper = 0;

        public MovingAverage(int size) {
            if (size <= 0) throw new IllegalArgumentException("size must be > 0");
            this.k = size;
        }

        public double next(int val) {
            window.offerLast(new int[]{idx, val});
            sum += val;
            pushMin(idx, val);
            pushMax(idx, val);
            addNum(val);

            if (window.size() > k) {
                int[] old = window.pollFirst();
                sum -= old[1];
                popMin(old[0]);
                popMax(old[0]);
                removeNum(old[1]);
            }
            idx++;
            return sum * 1.0 / window.size();
        }

        public double getMedian() {
            if (window.isEmpty()) return Double.NaN;
            if (window.size() % 2 == 1) return lower.peek();
            return ((double) lower.peek() + (double) upper.peek()) / 2.0;
        }

        public int getMin() {
            if (window.isEmpty()) throw new NoSuchElementException();
            while (!minDQ.isEmpty() && minDQ.peekFirst()[0] <= idx - window.size() - 1) minDQ.pollFirst();
            return minDQ.peekFirst()[1];
        }

        public int getMax() {
            if (window.isEmpty()) throw new NoSuchElementException();
            while (!maxDQ.isEmpty() && maxDQ.peekFirst()[0] <= idx - window.size() - 1) maxDQ.pollFirst();
            return maxDQ.peekFirst()[1];
        }

        private void pushMin(int i, int v) {
            while (!minDQ.isEmpty() && minDQ.peekLast()[1] > v) minDQ.pollLast();
            minDQ.offerLast(new int[]{i, v});
        }

        private void pushMax(int i, int v) {
            while (!maxDQ.isEmpty() && maxDQ.peekLast()[1] < v) maxDQ.pollLast();
            maxDQ.offerLast(new int[]{i, v});
        }

        private void popMin(int oldIdx) {
            if (!minDQ.isEmpty() && minDQ.peekFirst()[0] == oldIdx) minDQ.pollFirst();
        }

        private void popMax(int oldIdx) {
            if (!maxDQ.isEmpty() && maxDQ.peekFirst()[0] == oldIdx) maxDQ.pollFirst();
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
                Integer c = delayed.get(x);
                if (c == null || c == 0) break;
                heap.poll();
                if (c == 1) delayed.remove(x);
                else delayed.put(x, c - 1);
            }
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(fmt(ma.next(1)));   
        System.out.println(fmt(ma.next(10)));  
        System.out.println(fmt(ma.next(3)));   
        System.out.println(fmt(ma.next(5)));   
        System.out.println(fmt(ma.getMedian())); 
        System.out.println(ma.getMin());      
        System.out.println(ma.getMax());       
    }

    private static String fmt(double v) {
        if (Double.isNaN(v)) return "NaN";
        long r = Math.round(v);
        if (Math.abs(v - r) < 1e-9) return Long.toString(r);
        return String.format(Locale.ROOT, "%.10f", v).replaceAll("(\\.\\d*?[1-9])0+$", "$1").replaceAll("\\.0+$", "");
    }
}