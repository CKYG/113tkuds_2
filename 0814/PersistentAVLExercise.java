import java.util.*;

public class PersistentAVLExercise {
    static class Node {
        final int key, height;
        final Node left, right;
        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }
    }

    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null);
    }

    private static int height(Node n) { return n == null ? 0 : n.height; }
    private static int balanceFactor(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, T2, y.right));
    }

    private static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, T2), y.right);
    }

    private static Node rebalance(Node node) {
        int bf = balanceFactor(node);
        if (bf > 1 && balanceFactor(node.left) >= 0) return rotateRight(node);
        if (bf < -1 && balanceFactor(node.right) <= 0) return rotateLeft(node);
        if (bf > 1 && balanceFactor(node.left) < 0) return rotateRight(new Node(node.key, rotateLeft(node.left), node.right));
        if (bf < -1 && balanceFactor(node.right) > 0) return rotateLeft(new Node(node.key, node.left, rotateRight(node.right)));
        return node;
    }

    public void insert(int key) {
        Node newVersion = insert(versions.get(versions.size() - 1), key);
        versions.add(newVersion);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null);
        if (key < node.key) return rebalance(new Node(node.key, insert(node.left, key), node.right));
        if (key > node.key) return rebalance(new Node(node.key, node.left, insert(node.right, key)));
        return node;
    }

    public boolean search(int version, int key) {
        Node cur = versions.get(version);
        while (cur != null) {
            if (key == cur.key) return true;
            cur = key < cur.key ? cur.left : cur.right;
        }
        return false;
    }

    public void printInorder(int version) {
        inorder(versions.get(version));
        System.out.println();
    }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        PersistentAVLExercise pavl = new PersistentAVLExercise();
        pavl.insert(10);
        pavl.insert(20);
        pavl.insert(5);
        pavl.insert(15);

        pavl.printInorder(1);
        pavl.printInorder(2);
        pavl.printInorder(3);
        pavl.printInorder(4);

        System.out.println("Search 15 in version 2: " + pavl.search(2, 15));
    }
}