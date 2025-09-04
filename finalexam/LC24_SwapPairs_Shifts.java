import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        sc.close();

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (String s : arr) {
            cur.next = new ListNode(Integer.parseInt(s));
            cur = cur.next;
        }

        ListNode head = swapPairs(dummy.next);
        printList(head);
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            prev.next = b;
            a.next = b.next;
            b.next = a;

            prev = a;
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
