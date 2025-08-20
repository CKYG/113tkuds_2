import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color;
        Node left, right;
        Node(int val, char color) {
            this.val = val;
            this.color = color;
        }
    }

    static boolean violationFound = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] vals = new int[n];
        char[] colors = new char[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            colors[i] = sc.next().charAt(0);
        }

        Node root = buildTree(vals, colors, 0);

        if (root != null && root.color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        if (checkRB(root) == -1) {
            if (!violationFound) {
                System.out.println("BlackHeightMismatch");
            }
            return;
        }

        System.out.println("RB Valid");
    }

    private static Node buildTree(int[] vals, char[] colors, int idx) {
        if (idx >= vals.length || vals[idx] == -1) return null;
        Node node = new Node(vals[idx], colors[idx]);
        node.left = buildTree(vals, colors, 2 * idx + 1);
        node.right = buildTree(vals, colors, 2 * idx + 2);
        return node;
    }

    private static int checkRB(Node node) {
        if (node == null) return 1;

        if (node.color == 'R') {
            if ((node.left != null && node.left.color == 'R') ||
                (node.right != null && node.right.color == 'R')) {
                System.out.println("RedRedViolation at index");
                violationFound = true;
                return -1;
            }
        }

        int leftBH = checkRB(node.left);
        if (leftBH == -1) return -1;

        int rightBH = checkRB(node.right);
        if (rightBH == -1) return -1;

        if (leftBH != rightBH) {
            violationFound = true;
            return -1;
        }

        return leftBH + (node.color == 'B' ? 1 : 0);
    }
}
