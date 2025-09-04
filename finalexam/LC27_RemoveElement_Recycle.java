import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        sc.close();

        int newLength = removeElement(arr, val);
        System.out.println(newLength);
        for (int i = 0; i < newLength; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    private static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) nums[write++] = x;
        }
        return write;
    }
}
