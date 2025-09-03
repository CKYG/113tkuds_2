import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 避免重複解

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 先將陣列排序，方便處理重複解與雙指針。
2. 外層固定一個數 nums[i]，內層用雙指針 (left, right) 找和為 0。
3. 若 sum == 0，加入答案，並移動雙指針略過重複數字。
4. 若 sum < 0 → left++；若 sum > 0 → right--。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)（不計輸出）。
*/