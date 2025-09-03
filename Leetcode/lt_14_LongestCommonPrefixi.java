class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 初始共同前綴設為第一個字串。
2. 從第二個字串開始逐一比對：
   - 若當前字串不以 prefix 開頭，就逐步縮短 prefix。
   - 直到找到符合的共同前綴，或縮短為空字串。
3. 回傳最後得到的 prefix。
4. 時間複雜度 O(n * m)，n = 字串數量，m = 最長字串長度。
5. 空間複雜度 O(1)。
*/