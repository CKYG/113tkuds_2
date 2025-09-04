class Solution {
    /**
     * 生成「數數看」序列的第 n 個元素。
     *
     * @param n 序列的位置 (從 1 開始計數)。
     * @return 第 n 個「數數看」字串。
     */
    public String countAndSay(int n) {
        // 基本情況：序列的第一項是 "1"
        if (n == 1) {
            return "1";
        }

        // 從第一項開始
        String previousSequence = "1";

        // 迭代生成從第 2 項到第 n 項的序列
        for (int i = 2; i <= n; i++) {
            StringBuilder currentSequence = new StringBuilder();
            int count = 0; // 用於記錄連續相同字元的個數
            char currentChar = ' '; // 用於記錄當前連續相同的字元

            // 遍歷前一個序列，進行行程長度編碼
            for (int j = 0; j < previousSequence.length(); j++) {
                // 如果是前一個序列的第一個字元，初始化 count 和 currentChar
                if (j == 0) {
                    currentChar = previousSequence.charAt(j);
                    count = 1;
                }
                // 如果當前字元與前一個字元相同，則個數加一
                else if (previousSequence.charAt(j) == currentChar) {
                    count++;
                }
                // 如果當前字元與前一個字元不同
                // 將前一個字元的個數和字元加入到當前序列，然後重置 count 和 currentChar
                else {
                    currentSequence.append(count);
                    currentSequence.append(currentChar);
                    currentChar = previousSequence.charAt(j);
                    count = 1;
                }
            }
            // 迴圈結束後，別忘了將最後一組連續字元的個數和字元加入
            currentSequence.append(count);
            currentSequence.append(currentChar);

            // 更新 previousSequence，準備下一輪迭代
            previousSequence = currentSequence.toString();
        }

        return previousSequence;
    }
}