class TreeNodeV {
    int val;
    TreeNodeV left, right;
    TreeNodeV(int v) { val = v; }
}

public class BSTValidationAndRepair {

    private TreeNodeV firstElement = null;
    private TreeNodeV secondElement = null;
    private TreeNodeV prevElement = new TreeNodeV(Integer.MIN_VALUE);

    public boolean isValidBST(TreeNodeV root) {
        firstElement = null;
        secondElement = null;
        prevElement = new TreeNodeV(Integer.MIN_VALUE);
        inorder(root);
        return firstElement == null;
    }

    private void inorder(TreeNodeV root) {
        if (root == null) return;
        inorder(root.left);
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;
        inorder(root.right);
    }

    public void recoverTree(TreeNodeV root) {
        inorder(root);
        if (firstElement != null && secondElement != null) {
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }

    // 計算需要移除多少節點才能成為有效 BST，示範用簡化方法
    public int countInvalidNodes(TreeNodeV root) {
        return countInvalidHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int countInvalidHelper(TreeNodeV node, int min, int max) {
        if (node == null) return 0;
        if (node.val <= min || node.val >= max) return 1 + countInvalidHelper(node.left, min, max) + countInvalidHelper(node.right, min, max);
        return countInvalidHelper(node.left, min, node.val) + countInvalidHelper(node.right, node.val, max);
    }
}
