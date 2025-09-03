import java.util.*;

class Solution {
    private static final String[] KEYS = {
        "",    "",    "abc", "def", "ghi", "jkl",
        "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder sb, String digits, int idx) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }

        String letters = KEYS[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(res, sb, digits, idx + 1);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 建立數字到字母的映射表（模擬手機鍵盤）。
2. 使用回溯 (Backtracking)：
   - 每次選擇一個字母加入當前組合。
   - 遞迴處理下一位數字。
   - 回溯刪除最後一個字母，繼續嘗試下一個。
3. 當 idx == digits.length()，將組合加入結果。
4. 時間複雜度 O(3^n * 4^m)，其中 n,m 為不同數字數量（因為有些鍵 3 個字母，有些 4 個）。
5. 空間複雜度 O(n)，為遞迴深度。
*/