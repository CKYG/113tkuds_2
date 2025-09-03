import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append('(');
            backtrack(res, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(')');
            backtrack(res, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

/*
解題思路：
1. 使用回溯 (Backtracking)。
2. 兩個條件：
   - 只要還能放左括號 (open < n)，就嘗試加 '('。
   - 只要右括號數量小於左括號 (close < open)，就嘗試加 ')'。
3. 當字串長度等於 2n 時，加入結果。
4. 時間複雜度 O(4^n / sqrt(n))（卡特蘭數），空間複雜度 O(n)。
*/