import java.util.ArrayList;
import java.util.List;

class Node {
    String name;
    List<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }
}

public class RecursiveTreePreview {

    // 1. 模擬檔案系統計算檔案數（總檔案數）
    public static int countFiles(Node node) {
        if (node.children.isEmpty()) return 1;
        int count = 0;
        for (Node child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    // 2. 印出多層選單結構（遞迴印出樹狀結構）
    public static void printTree(Node node, String prefix) {
        System.out.println(prefix + node.name);
        for (Node child : node.children) {
            printTree(child, prefix + "  ");
        }
    }

    // 3. 展平巢狀陣列（將樹狀結構展平成 List）
    public static List<String> flattenTree(Node node) {
        List<String> result = new ArrayList<>();
        result.add(node.name);
        for (Node child : node.children) {
            result.addAll(flattenTree(child));
        }
        return result;
    }

    // 4. 計算最大深度
    public static int maxDepth(Node node) {
        if (node.children.isEmpty()) return 1;
        int max = 0;
        for (Node child : node.children) {
            int depth = maxDepth(child);
            if (depth > max) max = depth;
        }
        return max + 1;
    }

    public static void main(String[] args) {
        Node root = new Node("root");
        Node folder1 = new Node("folder1");
        Node folder2 = new Node("folder2");
        Node file1 = new Node("file1.txt");
        Node file2 = new Node("file2.txt");
        Node file3 = new Node("file3.txt");

        folder1.addChild(file1);
        folder1.addChild(file2);
        folder2.addChild(file3);
        root.addChild(folder1);
        root.addChild(folder2);

        System.out.println("總檔案數: " + countFiles(root));

        System.out.println("樹狀結構:");
        printTree(root, "");

        System.out.println("展平成 List: " + flattenTree(root));

        System.out.println("最大深度: " + maxDepth(root));
    }
}
