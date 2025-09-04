import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String[] arr = sc.nextLine().split(" ");
        sc.close();

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (String s : arr) {
            cur.next = new ListNode(Integer.parseInt(s));
            cur = cur.next;
        }

        ListNode head = reverseKGroup(dummy.next, k);
        printList(head);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) return dummy.next;
            }

            ListNode nextGroup = tail.next;
            ListNode[] reversed = reverse(prev.next, tail);
            prev.next = reversed[0];
            reversed[1].next = nextGroup;
            prev = reversed[1];
        }
    }

    // 回傳 newHead, newTail
    private static ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode prev = null, cur = head, end = tail.next;
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
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
