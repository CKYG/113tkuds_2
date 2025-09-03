import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1, right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 將陣列排序，方便處理重複解與雙指針。
2. 先固定前兩個數 (i, j)，再用雙指針 (left, right) 找四元組。
3. 若總和 == target → 加入答案，並略過重複數字。
4. 若總和 < target → left++；若總和 > target → right--。
5. 為避免整數溢位，sum 使用 long。
6. 時間複雜度 O(n^3)，空間複雜度 O(1)（不計輸出）。
*/