
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode[] lists = new ListNode[k];
        sc.nextLine();

        for (int i = 0; i < k; i++) {
            String[] arr = sc.nextLine().split(" ");
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            for (String s : arr) {
                int v = Integer.parseInt(s);
                if (v == -1) break;
                cur.next = new ListNode(v);
                cur = cur.next;
            }
            lists[i] = dummy.next;
        }
        sc.close();

        ListNode merged = mergeKLists(lists);
        printList(merged);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) if (node != null) pq.offer(node);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        boolean first = true;
        while (cur != null) {
            if (!first) System.out.print(" ");
            System.out.print(cur.val);
            first = false;
            cur = cur.next;
        }
        System.out.println();
    }
}
