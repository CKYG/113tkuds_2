import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 使用哈希集合來儲存視窗內的字元
        Set<Character> charSet = new HashSet<>();
        
        int left = 0;
        int right = 0;
        int maxLength = 0;
        
        // 遍歷字串
        while (right < s.length()) {
            char currentChar = s.charAt(right);
            
            // 如果哈希集合中已經有這個字元，收縮左邊窗口
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            
            // 將當前字元加入哈希集合
            charSet.add(currentChar);
            
            // 更新最長長度
            maxLength = Math.max(maxLength, right - left + 1);
            
            // 擴展右邊窗口
            right++;
        }
        
        return maxLength;
    }
}