import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong(); // 避免溢位
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        sc.close();

        List<List<Integer>> res = fourSum(nums, target);
        for (List<Integer> quad : res) {
            System.out.println(quad.get(0) + " " + quad.get(1) + " " + quad.get(2) + " " + quad.get(3));
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, long target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重 i
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重 j

                int L = j + 1, R = n - 1;
                while (L < R) {
                    long sum = (long)nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        L++;
                        R--;

                        // 去重 L
                        while (L < R && nums[L] == nums[L - 1]) L++;
                        // 去重 R
                        while (L < R && nums[R] == nums[R + 1]) R--;
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
        return ans;
    }
}
