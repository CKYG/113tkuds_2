class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        
        dp[0][0] = true;

        
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                
                    dp[i][j] = dp[i][j - 2];
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == sc) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}

public class lt_10_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution sol = new Solution();

   
        String s1 = "aa", p1 = "a";
        System.out.println("Example 1: " + sol.isMatch(s1, p1)); // false

 
        String s2 = "aa", p2 = "a*";
        System.out.println("Example 2: " + sol.isMatch(s2, p2)); // true


        String s3 = "ab", p3 = ".*";
        System.out.println("Example 3: " + sol.isMatch(s3, p3)); // true
    }
}
/*
解題思路：
1. 使用動態規劃，dp[i][j] 表示 s[0..i-1] 是否能匹配 p[0..j-1]。
2. 若字元相同或 p 為 '.' → 直接比對前一個狀態。
3. 若 p[j-1] 為 '*' → 可視為 0 次 (dp[i][j-2])，或多次 (若前字元能匹配)。
4. 時間複雜度 O(m*n)，空間複雜度 O(m*n)。
*/