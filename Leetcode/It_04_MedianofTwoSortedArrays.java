class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        // 確保 nums1 是較短的數組，以優化二分查找的範圍
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int low = 0;
        int high = m;
        int totalLeft = (m + n + 1) / 2;

        while (low <= high) {
            int i = low + (high - low) / 2;
            int j = totalLeft - i;

            int nums1Left = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1Right = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2Left = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2Right = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
                // 找到了正確的切點
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1Left, nums2Left);
                } else {
                    return (double) (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2.0;
                }
            } else if (nums1Left > nums2Right) {
                // nums1 的左半部分太大，向左移動切點
                high = i - 1;
            } else {
                // nums1 的右半部分太大，向右移動切點
                low = i + 1;
            }
        }
        
        return 0.0; // 根據題意，不會執行到這裡
    }
}