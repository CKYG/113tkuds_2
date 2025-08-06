class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int v) { val = v; }
}

public class BSTRangeQuerySystem {

    public static java.util.List<Integer> rangeQuery(BSTNode root, int min, int max) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        helper(root, min, max, result);
        return result;
    }

    private static void helper(BSTNode root, int min, int max, java.util.List<Integer> res) {
        if (root == null) return;
        if (root.val > min) helper(root.left, min, max, res);
        if (root.val >= min && root.val <= max) res.add(root.val);
        if (root.val < max) helper(root.right, min, max, res);
    }

    public static int rangeCount(BSTNode root, int min, int max) {
        return rangeQuery(root, min, max).size();
    }

    public static int rangeSum(BSTNode root, int min, int max) {
        int sum = 0;
        for (int val : rangeQuery(root, min, max)) sum += val;
        return sum;
    }

    public static int closestValue(BSTNode root, int target) {
        int closest = root.val;
        BSTNode current = root;
        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) closest = current.val;
            if (target < current.val) current = current.left;
            else if (target > current.val) current = current.right;
            else break;
        }
        return closest;
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(10);
        root.left = new BSTNode(5);
        root.right = new BSTNode(15);
        root.left.left = new BSTNode(3);
        root.left.right = new BSTNode(7);
        root.right.right = new BSTNode(18);

        System.out.println("範圍查詢 [7,15]: " + rangeQuery(root,7,15));
        System.out.println("範圍計數 [7,15]: " + rangeCount(root,7,15));
        System.out.println("範圍總和 [7,15]: " + rangeSum(root,7,15));
        System.out.println("最接近 8 的值: " + closestValue(root,8));
    }
}
