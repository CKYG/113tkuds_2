public class RecursiveMathCalculator {

    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static int catalan(int n) {
        if (n <= 1) return 1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += catalan(i) * catalan(n - 1 - i);
        }
        return res;
    }

    public static int hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    public static boolean isPalindrome(int num) {
        String s = Integer.toString(num);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("C(5,2) = " + combination(5, 2));
        System.out.println("Catalan(5) = " + catalan(5));
        System.out.println("Hanoi(4) = " + hanoi(4));
        System.out.println("12321 是迴文數? " + isPalindrome(12321));
        System.out.println("12345 是迴文數? " + isPalindrome(12345));
    }
}
