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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. 創建虛擬頭節點以簡化頭節點的刪除操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // 2. 快指標先走 n + 1 步
        // 這樣 fast 和 slow 之間就有 n 個節點的間隔
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 3. 兩個指標同時移動，直到快指標到達鏈表末尾
        // 這時，慢指標剛好位於要刪除節點的前一個節點
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 4. 刪除節點
        slow.next = slow.next.next;

        // 5. 返回修改後的鏈表頭
        return dummy.next;
    }
}

/*
解題思路：
1. **使用快慢指針**：這是一種只需一次遍歷即可解決鏈表問題的常用技巧。
2. **快指標先行**：讓 `fast` 指標先從虛擬頭節點開始走 n+1 步，這樣它和 `slow` 指標之間會形成一個 n 個節點的間距。
3. **同步移動**：然後，`fast` 和 `slow` 指標同時向前移動，直到 `fast` 到達鏈表的末尾（即 null）。
4. **定位並刪除**：此時，`slow` 指標正好位於要刪除節點的前一個位置。我們只需調整 `slow` 的 next 指標，跳過並刪除目標節點。
5. **時間與空間複雜度**：
   - **時間複雜度**: O(L)，其中 L 是鏈表的長度，因為我們只遍歷了鏈表一次。
   - **空間複雜度**: O(1)，我們只使用了幾個固定的指針變量。
*/