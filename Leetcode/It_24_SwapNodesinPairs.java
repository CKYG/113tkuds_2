/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 創建一個虛擬頭節點，簡化操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 循環直到沒有足夠的節點進行兩兩交換
        while (prev.next != null && prev.next.next != null) {
            // 定義要交換的兩個節點
            ListNode firstNode = prev.next;
            ListNode secondNode = prev.next.next;

            // 調整指針進行交換
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            prev.next = secondNode;

            // 移動 prev 指針到下一個交換對的前一個節點
            prev = firstNode;
        }

        return dummy.next;
    }
}