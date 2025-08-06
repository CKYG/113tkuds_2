public class RecursionVsIteration {

    public static int factorialRecursion(int n) {
        if (n <= 1) return 1;
        return n * factorialRecursion(n - 1);
    }

    public static int factorialIteration(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public static int productArrayRecursion(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productArrayRecursion(arr, index + 1);
    }

    public static int productArrayIteration(int[] arr) {
        int product = 1;
        for (int val : arr) product *= val;
        return product;
    }

    public static int countFirstCharRecursion(String str, char ch) {
        if (str.isEmpty()) return 0;
        int count = (str.charAt(0) == ch) ? 1 : 0;
        return count + countFirstCharRecursion(str.substring(1), ch);
    }

    public static int countFirstCharIteration(String str, char ch) {
        int count = 0;
        for (char c : str.toCharArray()) if (c == ch) count++;
        return count;
    }

    public static boolean checkBracketsRecursion(String str, int index, int count) {
        if (count < 0) return false;
        if (index == str.length()) return count == 0;
        if (str.charAt(index) == '(') return checkBracketsRecursion(str, index + 1, count + 1);
        if (str.charAt(index) == ')') return checkBracketsRecursion(str, index + 1, count - 1);
        return checkBracketsRecursion(str, index + 1, count);
    }

    public static boolean checkBracketsIteration(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println("階乘(遞迴): " + factorialRecursion(5));
        System.out.println("階乘(迭代): " + factorialIteration(5));

        int[] arr = {1, 2, 3, 4};
        System.out.println("陣列乘積(遞迴): " + productArrayRecursion(arr, 0));
        System.out.println("陣列乘積(迭代): " + productArrayIteration(arr));

        String test = "banana";
        System.out.println("字串中 'b' 數量(遞迴): " + countFirstCharRecursion(test, 'b'));
        System.out.println("字串中 'b' 數量(迭代): " + countFirstCharIteration(test, 'b'));

        String brackets1 = "(())()";
        String brackets2 = "(()))(";
        System.out.println("括號檢查(遞迴): " + checkBracketsRecursion(brackets1, 0, 0));
        System.out.println("括號檢查(迭代): " + checkBracketsIteration(brackets1));
        System.out.println("括號檢查(遞迴): " + checkBracketsRecursion(brackets2, 0, 0));
        System.out.println("括號檢查(迭代): " + checkBracketsIteration(brackets2));
    }
}
