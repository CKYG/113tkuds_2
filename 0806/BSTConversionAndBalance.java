
class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int v) { val = v; }
}

public class BSTConversionAndBalance {

    // 1. 將BST轉換為排序雙向鏈結串列 (中序遍歷)
    private BSTNode head = null;
    private BSTNode prev = null;

    public BSTNode bstToDoublyLinkedList(BSTNode root) {
        if (root == null) return null;
        bstToDoublyLinkedList(root.left);
        if (prev == null) head = root;
        else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        bstToDoublyLinkedList(root.right);
        return head;
    }

    // 2. 排序陣列轉換為平衡BST
    public BSTNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private BSTNode buildBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        BSTNode node = new BSTNode(nums[mid]);
        node.left = buildBST(nums, start, mid - 1);
        node.right = buildBST(nums, mid + 1, end);
        return node;
    }

    // 3. 檢查BST是否平衡並計算平衡因子
    public boolean isBalanced(BSTNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(BSTNode node) {
        if (node == null) return 0;
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 4. 將BST中每個節點值改為大於等於該節點值之總和（反中序遍歷）
    private int sum = 0;

    public void convertBSTToGreaterTree(BSTNode root) {
        if (root == null) return;
        convertBSTToGreaterTree(root.right);
        sum += root.val;
        root.val = sum;
        convertBSTToGreaterTree(root.left);
    }
}
