class Solution {
    /**
     * 解題邏輯與思路：
     * 本題使用「快慢指標」技巧，可以在原地（in-place）移除重複項。
     *
     * 1. 初始化慢指標 `slow`，指向已去重序列的尾部；快指標 `fast`，負責遍歷整個陣列。
     * 2. 快指標 `fast` 從索引 1 開始遍歷陣列。
     * 3. 如果 `nums[fast]` 與 `nums[slow]` 的值不相等，代表 `nums[fast]` 是一個新的唯一元素。
     * 此時，將 `slow` 指標向前移動一步，然後將 `nums[fast]` 的值覆寫到 `nums[slow]` 的位置。
     * 4. 如果 `nums[fast]` 與 `nums[slow]` 的值相等，代表 `nums[fast]` 是一個重複元素，我們只需繼續移動 `fast` 指標即可。
     * 5. 遍歷結束後，`slow + 1` 就是去重後陣列的長度 `k`。
     *
     * 時間複雜度：O(n)，n 為陣列長度，因為我們只遍歷了一次陣列。
     * 空間複雜度：O(1)，因為我們在原陣列上操作，沒有使用額外的空間。
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int slow = 0;
        
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        
        return slow + 1;
    }
}