import java.util.*;

public class M12_MergeKTimeTables {
    static class Entry {
        int time;
        int listIndex;
        int indexInList;

        Entry(int time, int listIndex, int indexInList) {
            this.time = time;
            this.listIndex = listIndex;
            this.indexInList = indexInList;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add(sc.nextInt());
            }
            lists.add(list);
        }

        List<Integer> merged = mergeKLists(lists, K);

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i));
            if (i != merged.size() - 1) System.out.print(" ");
        }
    }

    private static List<Integer> mergeKLists(List<List<Integer>> lists, int K) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Entry> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));

        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Entry(lists.get(i).get(0), i, 0));
            }
        }

        while (!pq.isEmpty()) {
            Entry cur = pq.poll();
            result.add(cur.time);

            int nextIndex = cur.indexInList + 1;
            if (nextIndex < lists.get(cur.listIndex).size()) {
                pq.offer(new Entry(lists.get(cur.listIndex).get(nextIndex), cur.listIndex, nextIndex));
            }
        }
        return result;
    }
}
