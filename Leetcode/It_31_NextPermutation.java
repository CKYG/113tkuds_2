class Solution {
    /**
     * 解題邏輯與思路：
     *
     * 本題要求找出給定整數陣列的下一個字典序排列，並要求原地修改，空間複雜度為 O(1)。
     *
     * 核心思想是基於「字典序」的定義來找到下一個更大的排列。
     *
     * 1. **尋找「打破升序」的點**：
     * - 從陣列的右邊開始，尋找第一個滿足 `nums[i] < nums[i+1]` 的索引 `i`。
     * - 這個 `i` 標誌著從右邊開始的升序排列被打破的地方。
     * - 如果找不到這樣的 `i`（即整個陣列是降序排列的），則表示當前排列是最大的，
     * 下一個排列就是最小的排列（升序）。此時，直接將整個陣列反轉即可。
     *
     * 2. **尋找「交換點」**：
     * - 如果找到了索引 `i`，則表示 `nums[i]` 是需要被交換的元素。
     * - 接著，從右邊開始尋找第一個大於 `nums[i]` 的元素，設其索引為 `j`。
     * - 也就是尋找 `nums[j] > nums[i]`。
     *
     * 3. **進行交換**：
     * - 交換 `nums[i]` 和 `nums[j]` 的值。
     *
     * 4. **反轉後續部分**：
     * - 交換後，`nums[i]` 右邊的部分（即從 `i+1` 開始到陣列末尾）仍然是降序排列的。
     * - 為了得到下一個「最小」的排列，需要將 `nums[i+1]` 到陣列末尾的部分進行反轉，使其變為升序。
     *
     * **時間複雜度**：O(n)，其中 n 是陣列的長度。我們最多遍歷三次陣列（尋找 i，尋找 j，反轉）。
     * **空間複雜度**：O(1)，因為我們只使用了幾個額外的變數來存儲索引和臨時值。
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        // 1. 從右往左找到第一個比右邊元素小的數字 nums[i]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 如果 i >= 0，表示找到了打破升序的點
        if (i >= 0) {
            // 2. 從右往左找到第一個比 nums[i] 大的數字 nums[j]
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // 3. 交換 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 4. 反轉 i 後面的所有元素 (從 i+1 開始到結尾)
        // 如果 i < 0 (陣列是完全降序的)，則反轉整個陣列，得到最小排列
        int left = i + 1;
        int right = n - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    // 輔助函數：交換陣列中兩個索引的元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}