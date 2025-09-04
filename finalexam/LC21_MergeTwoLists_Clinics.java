import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ListNode l1 = buildList(sc, n);
        ListNode l2 = buildList(sc, m);
        sc.close();

        ListNode merged = mergeTwoLists(l1, l2);
        printList(merged);
    }

    private static ListNode buildList(Scanner sc, int size) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < size; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        return dummy.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;

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
