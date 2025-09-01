class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < s.length(); i++) {
            // 處理奇數長度回文串
            int len1 = expandAroundCenter(s, i, i);
            // 處理偶數長度回文串
            int len2 = expandAroundCenter(s, i, i + 1);

            int currentMaxLen = Math.max(len1, len2);
            
            if (currentMaxLen > maxLength) {
                maxLength = currentMaxLen;
                // 計算新的起始索引
                start = i - (currentMaxLen - 1) / 2;
            }
        }
        return s.substring(start, start + maxLength);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 返回回文串的長度
        return right - left - 1;
    }
}