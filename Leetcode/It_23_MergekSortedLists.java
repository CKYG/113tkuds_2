import java.util.PriorityQueue;

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
    public ListNode mergeKLists(ListNode[] lists) {
        // 使用優先佇列 (最小堆)，自定義比較器以按節點值排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // 將每個鏈表的頭節點加入優先佇列
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }
        
        // 創建一個虛擬頭節點和一個指針
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        // 循環直到優先佇列為空
        while (!pq.isEmpty()) {
            // 取出最小節點
            ListNode minNode = pq.poll();
            
            // 將其連接到結果鏈表的尾部
            tail.next = minNode;
            tail = tail.next;
            
            // 如果取出的節點還有下一個節點，將其加入佇列
            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }
        
        return dummy.next;
    }
}