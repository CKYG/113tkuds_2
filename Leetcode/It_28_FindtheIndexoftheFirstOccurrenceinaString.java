class Solution {
    public int strStr(String haystack, String needle) {
        // 如果 needle 是空字串，返回 0。根據題目約束，這通常不會發生。
        if (needle.length() == 0) {
            return 0;
        }

        // 遍歷 haystack 所有可能的起始位置
        // 迴圈範圍確保子字串長度不會超過 haystack
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            // 檢查從 i 開始的子字串是否與 needle 匹配
            boolean match = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    match = false;
                    break; // 不匹配，直接跳出內層迴圈
                }
            }
            
            // 如果所有字符都匹配，返回當前索引
            if (match) {
                return i;
            }
        }
        
        // 如果迴圈結束仍未找到，表示沒有匹配
        return -1;
    }
}