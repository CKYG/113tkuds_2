import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // 恰好等於 target，直接回傳
                }
            }
        }
        return closest;
    }
}

/*
解題思路：
1. 先排序陣列，方便用雙指針法。
2. 外層固定一個數 nums[i]，內層用 (left, right) 找最接近 target 的三元組。
3. 每次計算 sum，若比目前的 closest 更接近 target，就更新 closest。
4. 根據 sum 與 target 的大小關係移動指針：
   - sum < target → left++；
   - sum > target → right--；
   - sum == target → 直接回傳 sum。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)。
*/