public class AVLRotations {

    // --- 最小可用的 AVLNode（空高=0、葉高=1） ---
    static class AVLNode {
        int data;
        AVLNode left, right;
        int height = 1;

        AVLNode(int data) { this.data = data; }

        private static int h(AVLNode n) { return (n == null) ? 0 : n.height; }

        public int getBalance() { return h(left) - h(right); }

        public void updateHeight() {
            this.height = Math.max(h(left), h(right)) + 1;
        }

        @Override
        public String toString() {
            return String.format("Node %d: h=%d, BF=%d", data, height, getBalance());
        }
    }

    // 右旋操作：O(1) 時間 / 空間
    public static AVLNode rightRotate(AVLNode y) {
        if (y == null || y.left == null) return y; // 簡單防呆

        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 旋轉
        x.right = y;
        y.left  = T2;

        // 先更新被降級節點，再更新新根
        y.updateHeight();
        x.updateHeight();

        return x; // 新子樹根
    }

    // 左旋操作：O(1) 時間 / 空間
    public static AVLNode leftRotate(AVLNode x) {
        if (x == null || x.right == null) return x; // 簡單防呆

        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 旋轉
        y.left  = x;
        x.right = T2;

        // 高度更新
        x.updateHeight();
        y.updateHeight();

        return y; // 新子樹根
    }

    // --- Demo: 建立 LL 失衡並右旋修正 ---
    public static void main(String[] args) {
        // 建：10 (左) 5 (左) 3
        AVLNode n10 = new AVLNode(10);
        AVLNode n5  = new AVLNode(5);
        AVLNode n3  = new AVLNode(3);
        n10.left = n5;
        n5.left  = n3;

        // 自底向上更新高度
        n3.updateHeight();
        n5.updateHeight();
        n10.updateHeight();

        System.out.println("Before rightRotate (LL unbalanced):");
        System.out.println(n10);
        System.out.println(n5);
        System.out.println(n3);
        // 期望：10 的 BF=2（失衡），5 的 BF=1，3 的 BF=0

        // 對根 10 做右旋
        AVLNode root = rightRotate(n10);

        System.out.println("\nAfter rightRotate at 10:");
        // 旋轉後新根是 5
        // 再印出 5、其左右（3、10）
        System.out.println(root);          // 5
        System.out.println(root.left);     // 3
        System.out.println(root.right);    // 10
        // 期望：三者 BF 都落在 [-1,1]；在此例剛好都是 0
    }
}