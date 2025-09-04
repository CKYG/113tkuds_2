import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        sc.close();

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, target, 0, new ArrayList<>(), res);
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(comb.get(i));
            }
            System.out.println();
        }
    }

    private static void backtrack(int[] nums, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            if (nums[i] > target) break;
            path.add(nums[i]);
            backtrack(nums, target - nums[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
