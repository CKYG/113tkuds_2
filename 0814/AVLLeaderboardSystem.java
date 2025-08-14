import java.util.*;

public class AVLLeaderboardSystem {
    static class Node {
        String player;
        int score, height, size;
        Node left, right;
        Node(String p, int s) { player = p; score = s; height = 1; size = 1; }
    }

    Node root;

    static int height(Node n) { return n == null ? 0 : n.height; }
    static int size(Node n) { return n == null ? 0 : n.size; }

    static void update(Node n) {
        if (n != null) {
            n.height = Math.max(height(n.left), height(n.right)) + 1;
            n.size = size(n.left) + size(n.right) + 1;
        }
    }

    static int balanceFactor(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    static Node rotateRight(Node y) {
        Node x = y.left; Node T2 = x.right;
        x.right = y; y.left = T2;
        update(y); update(x);
        return x;
    }

    static Node rotateLeft(Node x) {
        Node y = x.right; Node T2 = y.left;
        y.left = x; x.right = T2;
        update(x); update(y);
        return y;
    }

    public void addOrUpdate(String player, int score) {
        root = addOrUpdate(root, player, score);
    }

    private Node addOrUpdate(Node node, String player, int score) {
        if (node == null) return new Node(player, score);
        if (player.equals(node.player)) {
            node.score = score;
        } else if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
            node.left = addOrUpdate(node.left, player, score);
        } else {
            node.right = addOrUpdate(node.right, player, score);
        }
        update(node);
        return rebalance(node);
    }

    private Node rebalance(Node node) {
        int bf = balanceFactor(node);
        if (bf > 1 && balanceFactor(node.left) >= 0) return rotateRight(node);
        if (bf < -1 && balanceFactor(node.right) <= 0) return rotateLeft(node);
        if (bf > 1 && balanceFactor(node.left) < 0) { node.left = rotateLeft(node.left); return rotateRight(node); }
        if (bf < -1 && balanceFactor(node.right) > 0) { node.right = rotateRight(node.right); return rotateLeft(node); }
        return node;
    }

    public int getRank(String player) {
        return getRank(root, player);
    }

    private int getRank(Node node, String player) {
        if (node == null) return -1;
        if (player.equals(node.player)) {
            return size(node.left) + 1;
        } else if (comparePlayer(player, node) < 0) {
            return getRank(node.left, player);
        } else {
            int rightRank = getRank(node.right, player);
            return rightRank == -1 ? -1 : size(node.left) + 1 + rightRank;
        }
    }

    private int comparePlayer(String player, Node node) {
        // Higher score first, then lex order
        int cmp = Integer.compare(node.score, getScore(player));
        if (cmp != 0) return cmp;
        return player.compareTo(node.player);
    }

    private int getScore(String player) {
        return getScore(root, player);
    }

    private int getScore(Node node, String player) {
        if (node == null) return -1;
        if (player.equals(node.player)) return node.score;
        if (player.compareTo(node.player) < 0) return getScore(node.left, player);
        return getScore(node.right, player);
    }

    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        topK(root, k, result);
        return result;
    }

    private void topK(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        topK(node.left, k, result);
        if (result.size() < k) result.add(node.player + ":" + node.score);
        topK(node.right, k, result);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addOrUpdate("Alice", 50);
        lb.addOrUpdate("Bob", 70);
        lb.addOrUpdate("Charlie", 60);
        lb.addOrUpdate("Alice", 80);

        System.out.println("Rank of Alice: " + lb.getRank("Alice"));
        System.out.println("Top 2: " + lb.topK(2));
    }
}