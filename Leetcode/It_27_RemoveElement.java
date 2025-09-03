class Solution {
    /**
     * 解題邏輯與思路：
     * 本題同樣使用「快慢指標」來實現原地移除。
     *
     * 1. 初始化慢指標 `slow`，代表新陣列的尾部；快指標 `fast`，負責遍歷原陣列。
     * 2. 快指標 `fast` 從頭到尾遍歷陣列。
     * 3. 如果 `nums[fast]` 不等於 `val`，說明這個元素需要保留。
     * - 將 `nums[fast]` 複製到 `nums[slow]` 的位置。
     * - `slow` 指標向前移動一步，為下一個保留元素做準備。
     * 4. 如果 `nums[fast]` 等於 `val`，則跳過該元素，只移動 `fast` 指標。
     * 5. 遍歷結束後，`slow` 的值就是新陣列的長度。
     *
     * 時間複雜度：O(n)，n 為陣列長度，因為我們只遍歷了一次陣列。
     * 空間複雜度：O(1)，我們在原陣列上操作，沒有使用額外的空間。
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        return slow;
    }
}