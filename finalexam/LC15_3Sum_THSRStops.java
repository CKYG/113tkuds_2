import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        List<List<Integer>> res = threeSum(nums);

        for (List<Integer> triplet : res) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // 提前結束：若最小值已經 > 0，不可能再湊出 0
            if (nums[i] > 0) break;

            // 跳過重複的 i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int L = i + 1, R = n - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    L++;
                    R--;

                    // 跳過重複的 L
                    while (L < R && nums[L] == nums[L - 1]) L++;
                    // 跳過重複的 R
                    while (L < R && nums[R] == nums[R + 1]) R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return ans;
    }
}
