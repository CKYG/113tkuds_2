import java.util.ArrayList;
import java.util.List;

class BSTNodeK {
    int val;
    BSTNodeK left, right;
    BSTNodeK(int v) { val = v; }
}

public class BSTKthElement {

    private List<Integer> sortedElements = new ArrayList<>();

    public void inorderTraversal(BSTNodeK root) {
        if (root == null) return;
        inorderTraversal(root.left);
        sortedElements.add(root.val);
        inorderTraversal(root.right);
    }

    public int kthSmallest(BSTNodeK root, int k) {
        sortedElements.clear();
        inorderTraversal(root);
        return sortedElements.get(k - 1);
    }

    public int kthLargest(BSTNodeK root, int k) {
        sortedElements.clear();
        inorderTraversal(root);
        return sortedElements.get(sortedElements.size() - k);
    }

    public List<Integer> rangeBetween(BSTNodeK root, int low, int high) {
        List<Integer> result = new ArrayList<>();
        rangeHelper(root, low, high, result);
        return result;
    }

    private void rangeHelper(BSTNodeK root, int low, int high, List<Integer> result) {
        if (root == null) return;
        if (root.val > low) rangeHelper(root.left, low, high, result);
        if (root.val >= low && root.val <= high) result.add(root.val);
        if (root.val < high) rangeHelper(root.right, low, high, result);
    }

    // 4. 支援插入刪除的 BST 節點查詢 (簡易版插入與刪除)
    public BSTNodeK insert(BSTNodeK root, int val) {
        if (root == null) return new BSTNodeK(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    public BSTNodeK delete(BSTNodeK root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            BSTNodeK minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = delete(root.right, minNode.val);
        }
        return root;
    }

    private BSTNodeK findMin(BSTNodeK root) {
        while (root.left != null) root = root.left;
        return root;
    }

    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        BSTNodeK root = null;
        int[] values = {20, 8, 22, 4, 12, 10, 14};
        for (int v : values) root = bst.insert(root, v);

        System.out.println("第3小元素: " + bst.kthSmallest(root, 3));
        System.out.println("第2大元素: " + bst.kthLargest(root, 2));
        System.out.println("範圍[10,20]元素: " + bst.rangeBetween(root, 10, 20));

        root = bst.delete(root, 10);
        System.out.println("刪除10後範圍[10,20]元素: " + bst.rangeBetween(root, 10, 20));
    }
}
