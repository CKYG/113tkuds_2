class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { val = v; }
}

public class BinaryTreeBasicOperations {

    public static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    public static double average(TreeNode root) {
        int[] result = sumAndCount(root);
        return result[0] * 1.0 / result[1];
    }

    private static int[] sumAndCount(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = sumAndCount(root.left);
        int[] right = sumAndCount(root.right);
        return new int[]{
            root.val + left[0] + right[0],
            1 + left[1] + right[1]
        };
    }

    public static int maxValue(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(maxValue(root.left), maxValue(root.right)));
    }

    public static int minValue(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(minValue(root.left), minValue(root.right)));
    }

    public static int width(TreeNode root) {
        int maxWidth = 0;
        for (int i = 0; i < height(root); i++) {
            int width = getWidth(root, i);
            if (width > maxWidth) maxWidth = width;
        }
        return maxWidth;
    }

    private static int getWidth(TreeNode root, int level) {
        if (root == null) return 0;
        if (level == 0) return 1;
        return getWidth(root.left, level - 1) + getWidth(root.right, level - 1);
    }

    private static int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static boolean isComplete(TreeNode root) {
        if (root == null) return true;
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) end = true;
            else {
                if (end) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);

        System.out.println("節點總和: " + sum(root));
        System.out.println("平均值: " + average(root));
        System.out.println("最大值: " + maxValue(root));
        System.out.println("最小值: " + minValue(root));
        System.out.println("樹寬度: " + width(root));
        System.out.println("是否完全二元樹: " + isComplete(root));
    }
}
