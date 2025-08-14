

 
public class AVLBasicExercise {


    static class Node {
        int key;
        Node left, right;
        Node(int k) { this.key = k; }
    }

    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node cur, int key) {
        if (cur == null) return new Node(key);
        if (key < cur.key) cur.left = insert(cur.left, key);
        else if (key > cur.key) cur.right = insert(cur.right, key);
   
        return cur;
    }

    public boolean search(int key) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) return true;
            cur = (key < cur.key) ? cur.left : cur.right;
        }
        return false;
    }

 
    public int height() { return height(root); }

    private int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }

  
    public boolean isValidAVL() {
        return validate(root, null, null).ok;
    }

    private static class Check {
        boolean ok; 
        int height;   
        Check(boolean ok, int h) { this.ok = ok; this.height = h; }
    }

    private Check validate(Node n, Integer lower, Integer upper) {
        if (n == null) return new Check(true, 0);

        if (lower != null && n.key <= lower) return new Check(false, 0);
        if (upper != null && n.key >= upper) return new Check(false, 0);

        Check L = validate(n.left, lower, n.key);
        if (!L.ok) return new Check(false, 0);
        Check R = validate(n.right, n.key, upper);
        if (!R.ok) return new Check(false, 0);

        int bf = L.height - R.height;
        boolean okHere = Math.abs(bf) <= 1;
        int h = 1 + Math.max(L.height, R.height);
        return new Check(okHere, h);
    }

  
    public void inorderPrint() { inorderPrint(root); System.out.println(); }
    private void inorderPrint(Node n) {
        if (n == null) return;
        inorderPrint(n.left);
        System.out.print(n.key + " ");
        inorderPrint(n.right);
    }


    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        int[] nums = { 10, 5, 15, 3, 7, 12, 18 };
        for (int x : nums) tree.insert(x);

        System.out.print("Inorder: ");
        tree.inorderPrint(); 

        System.out.println("search(7) = " + tree.search(7));   
        System.out.println("search(99) = " + tree.search(99)); 

        System.out.println("height = " + tree.height());
        System.out.println("isValidAVL = " + tree.isValidAVL());

       
        AVLBasicExercise skew = new AVLBasicExercise();
        int[] asc = {1,2,3,4,5};
        for (int v : asc) skew.insert(v);
        System.out.println("height(skew) = " + skew.height());
        System.out.println("isValidAVL(skew) = " + skew.isValidAVL()); 
    }
}