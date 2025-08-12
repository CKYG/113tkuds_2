import java.util.*;

public class MultiLevelCacheSystem {

    static final int L1 = 0, L2 = 1, L3 = 2;

    static class Entry {
        final int key;
        String value;
        int freq;
        long last;
        int level;
        int gen;
        Entry(int key, String value, int level, long ts) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.freq = 1;
            this.last = ts;
            this.gen = 0;
        }
    }

    static class Node {
        final Entry e;
        final int levelSnapshot;
        final int genSnapshot;
        Node(Entry e) {
            this.e = e;
            this.levelSnapshot = e.level;
            this.genSnapshot = e.gen;
        }
    }

    static class MLCache {
        final int[] cap = {2, 5, 10};
        final int[] cost = {1, 3, 10};
        final PriorityQueue<Node>[] evictPQ;
        final int[] sz = new int[3];
        final Map<Integer, Entry> dir = new HashMap<>();
        long ts = 0;

        @SuppressWarnings("unchecked")
        MLCache() {
            evictPQ = new PriorityQueue[3];
            Comparator<Node> cmp = (a, b) -> {
                if (a.e.freq != b.e.freq) return Integer.compare(a.e.freq, b.e.freq);
                if (a.e.last != b.e.last) return Long.compare(a.e.last, b.e.last);
                if (a.levelSnapshot != b.levelSnapshot) return Integer.compare(a.levelSnapshot, b.levelSnapshot);
                return Integer.compare(a.e.key, b.e.key);
            };
            for (int i = 0; i < 3; i++) evictPQ[i] = new PriorityQueue<>(cmp);
        }

        public String get(int key) {
            Entry e = dir.get(key);
            if (e == null) return null;
            e.freq++;
            e.last = ++ts;
            touch(e);
            maybePromote(e);
            return e.value;
        }

        public void put(int key, String value) {
            Entry e = dir.get(key);
            if (e != null) {
                e.value = value;
                e.freq++;
                e.last = ++ts;
                touch(e);
                maybePromote(e);
                return;
            }
            Entry ne = new Entry(key, value, L1, ++ts);
            dir.put(key, ne);
            addToLevel(ne, L1);
            cascadeIfOverflow(L1);
        }

        private void touch(Entry e) {
            e.gen++;
            evictPQ[e.level].offer(new Node(e));
        }

        private void addToLevel(Entry e, int level) {
            e.level = level;
            e.gen++;
            sz[level]++;
            evictPQ[level].offer(new Node(e));
        }

        private void removeFromLevel(Entry e, int level) {
            sz[level]--;
            e.gen++;
            evictPQ[level].offer(new Node(e));
        }

        private Entry popValidVictim(int level) {
            PriorityQueue<Node> pq = evictPQ[level];
            while (!pq.isEmpty()) {
                Node top = pq.peek();
                if (top.e.level != level || top.genSnapshot != top.e.gen) {
                    pq.poll();
                    continue;
                }
                return top.e;
            }
            return null;
        }

        private void cascadeIfOverflow(int level) {
            int cur = level;
            while (cur <= L3 && sz[cur] > cap[cur]) {
                Entry victim = popValidVictim(cur);
                if (victim == null) break;
                removeFromLevel(victim, cur);
                int down = cur + 1;
                if (down > L3) {
                    dir.remove(victim.key);
                } else {
                    addToLevel(victim, down);
                }
                cur = down;
            }
        }

        private void maybePromote(Entry e) {
            while (e.level > L1) {
                int up = e.level - 1;
                Entry worstUp = popValidVictim(up);
                double myScore = score(e, up);
                double worstScore = worstUp == null ? Double.NEGATIVE_INFINITY : score(worstUp, up);
                if (worstUp == null || myScore > worstScore) {
                    removeFromLevel(e, e.level);
                    addToLevel(e, up);
                    if (sz[up] > cap[up]) {
                        Entry victim = popValidVictim(up);
                        if (victim != null && victim != e) {
                            removeFromLevel(victim, up);
                            int down = up + 1;
                            if (down > L3) {
                                dir.remove(victim.key);
                            } else {
                                addToLevel(victim, down);
                                cascadeIfOverflow(down);
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        }

        private double score(Entry e, int targetLevel) {
            long age = Math.max(0, ts - e.last);
            double f = e.freq;
            double c = cost[targetLevel];
            return f / c - age * 0.001;
        }

        public List<Integer> keysInLevel(int level) {
            List<Entry> list = new ArrayList<>();
            for (Entry e : dir.values()) if (e.level == level) list.add(e);
            list.sort((a, b) -> {
                if (a.last != b.last) return Long.compare(a.last, b.last);
                return Integer.compare(a.key, b.key);
            });
            List<Integer> keys = new ArrayList<>();
            for (Entry e : list) keys.add(e.key);
            return keys;
        }
    }

    public static void main(String[] args) {
        MLCache cache = new MLCache();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println("L1: " + cache.keysInLevel(L1) + ", L2: " + cache.keysInLevel(L2) + ", L3: " + cache.keysInLevel(L3));

        cache.get(1);
        cache.get(1);
        cache.get(2);
        System.out.println("L1: " + cache.keysInLevel(L1) + ", L2: " + cache.keysInLevel(L2) + ", L3: " + cache.keysInLevel(L3));

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        System.out.println("L1: " + cache.keysInLevel(L1) + ", L2: " + cache.keysInLevel(L2) + ", L3: " + cache.keysInLevel(L3));

        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("L1: " + cache.keysInLevel(L1) + ", L2: " + cache.keysInLevel(L2) + ", L3: " + cache.keysInLevel(L3));
    }
}