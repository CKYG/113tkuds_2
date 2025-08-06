import java.util.*;

class TreeNodeP {
    int val;
    TreeNodeP left, right;
    TreeNodeP(int v) { val = v; }
}

public class TreePathProblems {

    public static List<List<Integer>> allRootToLeafPaths(TreeNodeP root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    private static void dfs(TreeNodeP node, List<Integer> path, List<List<Integer>> res) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            if (node.left != null) dfs(node.left, path, res);
            if (node.right != null) dfs(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    public static boolean hasPathSum(TreeNodeP root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == target;
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    public static int maxRootToLeafPathSum(TreeNodeP root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        int leftSum = maxRootToLeafPathSum(root.left);
        int rightSum = maxRootToLeafPathSum(root.right);
        return root.val + Math.max(leftSum, rightSum);
    }

    public static int diameter(TreeNodeP root) {
        int[] diameter = new int[1];
        depth(root, diameter);
        return diameter[0];
    }

    private static int depth(TreeNodeP node, int[] diameter) {
        if (node == null) return 0;
        int leftDepth = depth(node.left, diameter);
        int rightDepth = depth(node.right, diameter);
        diameter[0] = Math.max(diameter[0], leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
