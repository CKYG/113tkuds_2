class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 準備好數字與對應的羅馬符號（包含特殊情況 4, 9, 40, 90, 400, 900）。
2. 從大到小依序檢查，能減就減，並將符號加到結果。
3. 重複直到 num 減到 0。
4. 時間複雜度 O(1)，因為最大值 3999，最多處理 15 個符號。
*/