class Solution {
    /**
     * 搜尋插入位置
     *
     * @param nums 已排序的不重複整數陣列
     * @param target 目標值
     * @return 如果找到目標值，則回傳其索引；否則回傳目標值應插入的索引。
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 當 left <= right 時，表示搜尋範圍有效
        while (left <= right) {
            // 計算中間索引，避免整數溢位
            int mid = left + (right - left) / 2;

            // 如果中間元素等於目標值，則直接回傳索引
            if (nums[mid] == target) {
                return mid;
            }
            // 如果中間元素小於目標值，則目標值可能在右半邊
            else if (nums[mid] < target) {
                left = mid + 1; // 搜尋範圍縮小到右半邊
            }
            // 如果中間元素大於目標值，則目標值可能在左半邊
            else {
                right = mid - 1; // 搜尋範圍縮小到左半邊
            }
        }

        // 迴圈結束時，left 就是目標值應該插入的位置
        // 因為此時 left > right，且 left 指向第一個大於等於 target 的元素（或陣列結尾）
        return left;
    }
}