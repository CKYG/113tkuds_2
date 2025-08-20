import java.util.*;

public class M03_TopKConvenience {
    static class Product {
        String name;
        int qty;

        Product(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        PriorityQueue<Product> pq = new PriorityQueue<>((a, b) -> {
            if (a.qty == b.qty) return a.name.compareTo(b.name); 
            return a.qty - b.qty; 
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            Product p = new Product(name, qty);

            if (pq.size() < k) {
                pq.offer(p);
            } else if (pq.peek().qty < qty || (pq.peek().qty == qty && pq.peek().name.compareTo(name) > 0)) {
                pq.poll();
                pq.offer(p);
            }
        }

        List<Product> ans = new ArrayList<>(pq);
        ans.sort((a, b) -> {
            if (b.qty == a.qty) return a.name.compareTo(b.name);
            return b.qty - a.qty;
        });

        for (Product p : ans) {
            System.out.println(p.name + " " + p.qty);
        }
    }
}

/*
 * Time Complexity: O(n log k)
 * 說明: 使用大小為 k 的 Min-Heap 維護 Top-K 資料，
 * 插入與彈出操作為 O(log k)，總計 O(n log k)。
 */
