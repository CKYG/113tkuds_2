import java.util.*;

class MultiWayNode {
    String val;
    List<MultiWayNode> children;

    public MultiWayNode(String v) {
        val = v;
        children = new ArrayList<>();
    }

    public void addChild(MultiWayNode child) {
        children.add(child);
    }
}

public class MultiWayTreeAndDecisionTree {

    // 1. 建立多路樹，支援任意子節點數

    // 2. 深度優先遍歷
    public void dfs(MultiWayNode root) {
        if (root == null) return;
        System.out.println(root.val);
        for (MultiWayNode child : root.children) {
            dfs(child);
        }
    }

    // 2. 廣度優先遍歷
    public void bfs(MultiWayNode root) {
        if (root == null) return;
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayNode node = queue.poll();
            System.out.println(node.val);
            for (MultiWayNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 3. 模擬簡單決策樹結構 (例：猜數字遊戲)
    public void decisionTreeExample() {
        MultiWayNode root = new MultiWayNode("猜數字遊戲");
        MultiWayNode low = new MultiWayNode("太小");
        MultiWayNode high = new MultiWayNode("太大");
        MultiWayNode correct = new MultiWayNode("答對了");

        root.addChild(low);
        root.addChild(high);
        root.addChild(correct);

        System.out.println("決策樹範例：");
        dfs(root);
    }

    // 4. 計算多路樹高度
    public int height(MultiWayNode root) {
        if (root == null) return 0;
        int maxHeight = 0;
        for (MultiWayNode child : root.children) {
            int childHeight = height(child);
            if (childHeight > maxHeight) maxHeight = childHeight;
        }
        return maxHeight + 1;
    }

    // 計算每個節點的度數
    public void printDegrees(MultiWayNode root) {
        if (root == null) return;
        System.out.println(root.val + " 的度數: " + root.children.size());
        for (MultiWayNode child : root.children) {
            printDegrees(child);
        }
    }

    public static void main(String[] args) {
        MultiWayTreeAndDecisionTree tree = new MultiWayTreeAndDecisionTree();
        MultiWayNode root = new MultiWayNode("根節點");
        MultiWayNode child1 = new MultiWayNode("子節點1");
        MultiWayNode child2 = new MultiWayNode("子節點2");
        MultiWayNode child3 = new MultiWayNode("子節點3");

        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(child3);

        System.out.println("深度優先遍歷:");
        tree.dfs(root);

        System.out.println("廣度優先遍歷:");
        tree.bfs(root);

        tree.decisionTreeExample();

        System.out.println("多路樹高度: " + tree.height(root));
        System.out.println("各節點度數:");
        tree.printDegrees(root);
    }
}
