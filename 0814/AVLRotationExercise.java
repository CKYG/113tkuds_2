public class AVLRotationExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }

    static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    static void updateHeight(Node n) {
        if (n != null) {
            n.height = Math.max(height(n.left), height(n.right)) + 1;
        }
    }

    static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    static Node rotateLeftRight(Node z) {
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }

    static Node rotateRightLeft(Node z) {
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    static void preorder(Node n) {
        if (n != null) {
            System.out.print(n.key + " ");
            preorder(n.left);
            preorder(n.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);
        updateHeight(root.left.left);
        updateHeight(root.left);
        updateHeight(root);
        root = rotateRight(root);
        preorder(root);
        System.out.println();

        root = new Node(10);
        root.right = new Node(20);
        root.right.right = new Node(30);
        updateHeight(root.right.right);
        updateHeight(root.right);
        updateHeight(root);
        root = rotateLeft(root);
        preorder(root);
        System.out.println();

        root = new Node(30);
        root.left = new Node(10);
        root.left.right = new Node(20);
        updateHeight(root.left.right);
        updateHeight(root.left);
        updateHeight(root);
        root = rotateLeftRight(root);
        preorder(root);
        System.out.println();

        root = new Node(10);
        root.right = new Node(30);
        root.right.left = new Node(20);
        updateHeight(root.right.left);
        updateHeight(root.right);
        updateHeight(root);
        root = rotateRightLeft(root);
        preorder(root);
    }
}