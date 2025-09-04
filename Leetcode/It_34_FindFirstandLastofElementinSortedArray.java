class Solution {
    /**
     * 解題邏輯與思路：
     *
     * 本題要求在一個已排序的陣列 `nums` 中，找到目標值 `target` 的第一個和最後一個出現的索引。
     * 如果目標值不存在，則返回 `[-1, -1]`。
     * 要求演算法的時間複雜度為 $O(\log n)$，這提示我們應該使用二分搜尋法。
     *
     * 1. **搜尋第一個出現的位置**：
     * - 使用改良版的二分搜尋。當找到 `nums[mid] == target` 時，我們不能立即停止，
     * - 因為這可能是 `target` 的第一個出現位置，也可能是中間或最後一個。
     * - 如果 `nums[mid] == target`，我們記錄下當前的 `mid` 作為可能的答案，並將搜尋範圍縮小到左邊（`right = mid - 1`），
     * 試圖尋找更靠左的 `target`。
     * - 如果 `nums[mid] < target`，則目標在右邊，`left = mid + 1`。
     * - 如果 `nums[mid] > target`，則目標在左邊，`right = mid - 1`。
     *
     * 2. **搜尋最後一個出現的位置**：
     * - 類似地，使用另一個改良版的二分搜尋。
     * - 當找到 `nums[mid] == target` 時，我們記錄下當前的 `mid` 作為可能的答案，並將搜尋範圍縮小到右邊（`left = mid + 1`），
     * 試圖尋找更靠右的 `target`。
     * - 如果 `nums[mid] < target`，則目標在右邊，`left = mid + 1`。
     * - 如果 `nums[mid] > target`，則目標在左邊，`right = mid - 1`。
     *
     * 3. **組合結果**：
     * - 分別調用兩個二分搜尋函數，找到第一個和最後一個出現的索引。
     * - 如果第一個搜尋函數返回 `-1`，則表示目標值不存在，直接返回 `[-1, -1]`。
     * - 否則，將兩個搜尋結果組合起來，作為最終的答案。
     *
     * **時間複雜度**：$O(\log n)$，因為我們執行了兩次二分搜尋。
     * **空間複雜度**：$O(1)$，我們只使用了幾個額外的變數。
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        
        // 尋找第一個出現的位置
        result[0] = findFirst(nums, target);
        
        // 如果第一個位置是 -1，則目標不存在，直接返回
        if (result[0] == -1) {
            return result;
        }
        
        // 尋找最後一個出現的位置
        result[1] = findLast(nums, target);
        
        return result;
    }
    
    // 輔助函數：尋找 target 在 nums 中第一次出現的索引
    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int firstPos = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                firstPos = mid; // 找到一個可能的答案，繼續向左尋找更小的索引
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid - 1;
            }
        }
        return firstPos;
    }
    
    // 輔助函數：尋找 target 在 nums 中最後一次出現的索引
    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int lastPos = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                lastPos = mid; // 找到一個可能的答案，繼續向右尋找更大的索引
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid - 1;
            }
        }
        return lastPos;
    }
}