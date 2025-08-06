class TreeNodeM {
    int val;
    TreeNodeM left, right;
    TreeNodeM(int v) { val = v; }
}

public class TreeMirrorAndSymmetry {

    public static boolean isSymmetric(TreeNodeM root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNodeM t1, TreeNodeM t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static TreeNodeM mirror(TreeNodeM root) {
        if (root == null) return null;
        TreeNodeM mirrored = new TreeNodeM(root.val);
        mirrored.left = mirror(root.right);
        mirrored.right = mirror(root.left);
        return mirrored;
    }

    public static boolean areMirrors(TreeNodeM t1, TreeNodeM t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && areMirrors(t1.left, t2.right)
                && areMirrors(t1.right, t2.left);
    }

    public static boolean isSubtree(TreeNodeM s, TreeNodeM t) {
        if (s == null) return t == null;
        if (isSameTree(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isSameTree(TreeNodeM s, TreeNodeM t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return (s.val == t.val) && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public static void main(String[] args) {
        TreeNodeM root = new TreeNodeM(1);
        root.left = new TreeNodeM(2);
        root.right = new TreeNodeM(2);
        root.left.left = new TreeNodeM(3);
        root.left.right = new TreeNodeM(4);
        root.right.left = new TreeNodeM(4);
        root.right.right = new TreeNodeM(3);

        System.out.println("是否對稱樹: " + isSymmetric(root));
        TreeNodeM mirrored = mirror(root);
        System.out.println("鏡像樹根節點值: " + mirrored.val);
        System.out.println("兩棵樹是否互為鏡像: " + areMirrors(root, mirrored));
        System.out.println("子樹判斷 (root 是否為 root.left 子樹): " + isSubtree(root, root.left));
    }
}
