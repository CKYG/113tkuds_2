class Solution {
    public int reverse(int x) {
        long reversed = 0;
        
        while (x != 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }
        
        // 檢查是否超出 32 位元整數的範圍
        if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int) reversed;
    }
}