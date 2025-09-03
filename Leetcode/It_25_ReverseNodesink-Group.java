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
    /**
     * 解題邏輯與思路：
     * 本題的核心是迭代地處理鏈表，每次處理 k 個節點。
     *
     * 1. 虛擬頭節點：使用 dummy 節點來簡化對頭部節點的處理。
     * 2. 分組翻轉：
     * - 使用一個 `prev` 指針，指向當前要翻轉的 k 個節點的前一個節點。
     * - 使用一個 `end` 指針，從 `prev` 開始走 k 步，找到這 k 個節點的末尾。
     * - 如果 `end` 變為 `null`，表示剩餘節點不足 k 個，終止循環。
     * - 否則，開始翻轉這 k 個節點。
     * 3. 翻轉操作：
     * - 提取出待翻轉的子鏈表：頭部是 `prev.next`，尾部是 `end`。
     * - 使用經典的鏈表翻轉算法，將這段子鏈表翻轉。
     * 4. 重新鏈接：
     * - 將 `prev` 的 next 指針指向翻轉後的子鏈表頭部（即原來的 `end`）。
     * - 將翻轉後子鏈表的尾部（即原來的 `start`）的 next 指針，指向下一組的開頭。
     * 5. 更新指針：將 `prev` 指針移動到當前翻轉組的末尾，準備處理下一組。
     *
     * 時間複雜度：O(N)，其中 N 是鏈表節點總數。每個節點只會被處理常數次。
     * 空間複雜度：O(1)，我們只使用了幾個固定的指針變量。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            // 找到當前組的末尾
            ListNode end = prev;
            for (int i = 0; i < k; i++) {
                if (end.next == null) {
                    return dummy.next; // 剩餘不足 k 個，直接返回
                }
                end = end.next;
            }

            // 提取要翻轉的子鏈表
            ListNode start = prev.next;
            ListNode nextGroupStart = end.next;
            end.next = null; // 斷開連接，以便獨立翻轉

            // 翻轉子鏈表
            ListNode reversedHead = reverseList(start);

            // 重新鏈接翻轉後的子鏈表
            prev.next = reversedHead;
            start.next = nextGroupStart;

            // 移動 prev 指針到下一組的開頭
            prev = start;
        }
    }

    // 輔助函數：翻轉一個鏈表
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}