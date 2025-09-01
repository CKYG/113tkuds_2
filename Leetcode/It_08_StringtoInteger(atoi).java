class Solution {
    public int myAtoi(String s) {
        int index = 0;
        int sign = 1;
        long result = 0;

        // 1. 忽略開頭的空白字元
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }

        // 2. 處理正負號
        if (index < s.length()) {
            if (s.charAt(index) == '+') {
                index++;
            } else if (s.charAt(index) == '-') {
                sign = -1;
                index++;
            }
        }

        // 3. 轉換數字並檢查溢位
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            // 4. 處理溢位
            if (sign == 1 && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7))) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 8))) {
                return Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            index++;
        }
        
        return (int) (result * sign);
    }
}