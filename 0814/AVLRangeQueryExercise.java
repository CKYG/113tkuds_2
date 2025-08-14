import java.util.*;

public class AVLRangeQueryExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }

    Node root;

    static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    static void updateHeight(Node n) {
        if (n != null) {
            n.height = Math.max(height(n.left), height(n.right)) + 1;
        }
    }

    static int balanceFactor(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
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

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node;
        updateHeight(node);
        return rebalance(node, key);
    }

    private Node rebalance(Node node, int key) {
        int bf = balanceFactor(node);
        if (bf > 1 && key < node.left.key) return rotateRight(node);
        if (bf < -1 && key > node.right.key) return rotateLeft(node);
        if (bf > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (bf < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        return result;
    }

    private void rangeQuery(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.key > min) rangeQuery(node.left, min, max, result);
        if (node.key >= min && node.key <= max) result.add(node.key);
        if (node.key < max) rangeQuery(node.right, min, max, result);
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] keys = {30, 20, 40, 10, 25, 35, 50};
        for (int k : keys) tree.insert(k);
        List<Integer> range = tree.rangeQuery(15, 40);
        System.out.println(range);
    }
}