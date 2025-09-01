class Solution {
    public boolean isPalindrome(int x) {
        // 负数和以0结尾的非0数都不是回文数
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 如果原始数字是奇数位数，那么revertedNumber会比x多一位
        // 例如：x = 12321，循环结束后 x = 12, revertedNumber = 123
        // 这时需要将 revertedNumber 除以 10
        return x == revertedNumber || x == revertedNumber / 10;
    }
}