import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        int k = sc.nextInt();
        sc.close();

        ListNode head = removeNthFromEnd(dummy.next, k);
        printList(head);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n 步
        for (int i = 0; i < n; i++) fast = fast.next;

        // 同步移動
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除節點
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void printList(ListNode head) {
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
