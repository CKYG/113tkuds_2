import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        sc.close();

        int newLength = removeDuplicates(arr);
        System.out.println(newLength);
        for (int i = 0; i < newLength; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    private static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int write = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }
}
