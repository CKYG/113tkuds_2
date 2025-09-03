class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;

        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            max = Math.max(max, width * h);

        
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}

public class lt_11_ContainerWithMostWater {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] h1 = {1,8,6,2,5,4,8,3,7};
        System.out.println("Example 1: " + sol.maxArea(h1)); // 49

    
        int[] h2 = {1,1};
        System.out.println("Example 2: " + sol.maxArea(h2)); // 1
    }
}
/*
解題思路：
1. 使用雙指針，初始在最左與最右。
2. 計算面積 = (right - left) * min(height[left], height[right])。
3. 移動較矮的指針，因為高度才可能變大。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/