import java.util.*;

class TreeNodeR {
    int val;
    TreeNodeR left, right;
    TreeNodeR(int v) { val = v; }
}

public class TreeReconstruction {

    public static TreeNodeR buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) idxMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, idxMap);
    }

    private static TreeNodeR buildPreIn(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> idxMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNodeR root = new TreeNodeR(preorder[preStart]);
        int inRoot = idxMap.get(root.val);
        int leftSize = inRoot - inStart;
        root.left = buildPreIn(preorder, preStart + 1, preStart + leftSize, inorder, inStart, inRoot - 1, idxMap);
        root.right = buildPreIn(preorder, preStart + leftSize + 1, preEnd, inorder, inRoot + 1, inEnd, idxMap);
        return root;
    }

    public static TreeNodeR buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) idxMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, idxMap);
    }

    private static TreeNodeR buildPostIn(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> idxMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        TreeNodeR root = new TreeNodeR(postorder[postEnd]);
        int inRoot = idxMap.get(root.val);
        int leftSize = inRoot - inStart;
        root.left = buildPostIn(postorder, postStart, postStart + leftSize - 1, inorder, inStart, inRoot - 1, idxMap);
        root.right = buildPostIn(postorder, postStart + leftSize, postEnd - 1, inorder, inRoot + 1, inEnd, idxMap);
        return root;
    }

    public static TreeNodeR buildTreeLevel(int[] levelorder) {
        if (levelorder.length == 0) return null;
        TreeNodeR root = new TreeNodeR(levelorder[0]);
        Queue<TreeNodeR> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelorder.length) {
            TreeNodeR node = queue.poll();
            if (i < levelorder.length) {
                node.left = new TreeNodeR(levelorder[i++]);
                queue.offer(node.left);
            }
            if (i < levelorder.length) {
                node.right = new TreeNodeR(levelorder[i++]);
                queue.offer(node.right);
            }
        }
        return root;
    }

    public static boolean validateTree(TreeNodeR root) {
        return root != null;
    }
}
