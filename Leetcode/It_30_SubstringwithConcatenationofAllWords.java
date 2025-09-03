import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    /**
     * 解題邏輯與思路：
     *
     * 本題要求找到所有由 `words` 陣列中的單詞以任意順序串聯而成的子字串的起始索引。
     *
     * 1. **預處理**：
     * - 首先，計算所有單詞的總長度 `wordLen * words.length`，這就是我們需要尋找的子字串長度。
     * - 使用一個 `HashMap` `wordCount` 來統計 `words` 陣列中每個單詞出現的頻率。這是為了快速查找和比較。
     *
     * 2. **滑動視窗**：
     * - 我們採用「滑動視窗」的方法，視窗的大小就是所有單詞的總長度。
     * - 由於單詞的排列可以有多種方式，我們不能簡單地從頭開始滑動。
     * - 為了覆蓋所有可能的起始位置，我們將滑動視窗的起點遍歷 `wordLen` 次。
     * - 舉例：如果 `wordLen` 為 3，我們分別從 `s` 的索引 0, 1, 2 開始滑動，這樣可以保證不會遺漏任何可能的組合。
     *
     * 3. **視窗內部處理**：
     * - 在每個滑動視窗內，我們再次使用一個 `HashMap` `subWordCount` 來統計當前視窗中每個單詞的頻率。
     * - 我們以 `wordLen` 為步長，遍歷視窗內的每一個「單詞」。
     * - 如果視窗中的子字串在 `wordCount` 中存在，則在 `subWordCount` 中更新其計數。
     * - 如果某個單詞的計數超過了它在 `wordCount` 中的計數，則表示當前視窗無效，需要從這個單詞的下一個位置重新開始。
     * - 如果視窗中的子字串不在 `wordCount` 中，則當前視窗無效，跳過整個視窗，從當前位置重新開始。
     *
     * 4. **檢查與結果**：
     * - 如果當前視窗內所有單詞的計數都與 `wordCount` 完全匹配，則說明找到了一個有效的串聯子字串。
     * - 將當前滑動視窗的起始索引加入結果列表。
     *
     * 時間複雜度：O(s.length() * wordLen * words.length)。外層迴圈遍歷 s 的每個可能起始位置，內層迴圈在每個視窗內遍歷 `words.length` 次。
     * 空間複雜度：O(words.length) 額外空間用於存儲 HashMaps。
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> currentWordCount = new HashMap<>();
            int left = i;
            int count = 0;

            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String sub = s.substring(j, j + wordLen);

                if (wordCount.containsKey(sub)) {
                    currentWordCount.put(sub, currentWordCount.getOrDefault(sub, 0) + 1);
                    count++;

                    while (currentWordCount.get(sub) > wordCount.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentWordCount.put(leftWord, currentWordCount.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == words.length) {
                        result.add(left);
                        // 找到一個匹配後，滑動視窗
                        String leftWord = s.substring(left, left + wordLen);
                        currentWordCount.put(leftWord, currentWordCount.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // 遇到不匹配的單詞，重置視窗
                    currentWordCount.clear();
                    left = j + wordLen;
                    count = 0;
                }
            }
        }
        return result;
    }
}