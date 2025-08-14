public class AVLDeleteExercise {
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

    public void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node successor = minValueNode(node.right);
                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }
        }

        if (node == null) return null;
        updateHeight(node);
        return rebalanceAfterDelete(node);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
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

    private Node rebalanceAfterDelete(Node node) {
        int bf = balanceFactor(node);
        if (bf > 1 && balanceFactor(node.left) >= 0) return rotateRight(node);
        if (bf > 1 && balanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (bf < -1 && balanceFactor(node.right) <= 0) return rotateLeft(node);
        if (bf < -1 && balanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    public void inorderPrint() {
        inorderPrint(root);
        System.out.println();
    }

    private void inorderPrint(Node node) {
        if (node != null) {
            inorderPrint(node.left);
            System.out.print(node.key + " ");
            inorderPrint(node.right);
        }
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        int[] keys = {30, 20, 40, 10, 25, 35, 50};
        for (int k : keys) tree.insert(k);

        tree.inorderPrint();
        tree.delete(10);
        tree.inorderPrint();
        tree.delete(20);
        tree.inorderPrint();
        tree.delete(30);
        tree.inorderPrint();
    }
}