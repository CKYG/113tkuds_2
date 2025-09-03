class Solution {
    /**
     * 解題邏輯與思路：
     * 本題要求不使用乘除法和取模來實現整數除法，並且要處理溢位。
     * 核心思想是利用位元運算（bitwise operations），特別是左移（left shift）。
     *
     * 1. **處理符號和溢位**：
     * - 首先判斷 `dividend` 和 `divisor` 的符號，用一個變數 `sign` 記錄。
     * - 將兩個數都轉換為長整數（`long`）的負數形式進行計算，以避免正數絕對值溢位（例如 `-2^31` 的絕對值超出 `2^31-1`）。
     * - 在計算結束後，根據 `sign` 調整結果的正負。
     * - 特殊處理 `divisor` 為 0 的情況（題目已保證不會發生）。
     *
     * 2. **主體計算（使用位元運算）**：
     * - 核心思想是用 `divisor` 的倍數來逼近 `dividend`。
     * - 我們從 `divisor` 開始，不斷左移（相當於乘以 2），每次都檢查它與 `dividend` 的大小關係。
     * - 使用一個內部迴圈，從最大的倍數開始，找到最大的 `(divisor << i)` 小於等於 `dividend` 的 `i` 值。
     * - 找到後，將 `dividend` 減去這個倍數，並將 `1 << i` 加到結果中。
     * - 這個過程重複進行，直到 `dividend` 小於 `divisor`。
     *
     * 3. **結果處理**：
     * - 根據最初記錄的 `sign` 符號來確定最終結果的正負。
     * - 處理結果的溢位：如果結果超出了 32 位元帶符號整數的範圍 `[-2^31, 2^31 - 1]`，則返回 `Integer.MAX_VALUE` 或 `Integer.MIN_VALUE`。
     * - 由於我們全程使用負數計算，最後的溢位判斷會更簡潔。
     */
    public int divide(int dividend, int divisor) {
        // 處理特殊溢位情況：當除數為 -1 且被除數為 Integer.MIN_VALUE 時
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 判斷符號，並將兩數轉為負數進行計算以處理 Integer.MIN_VALUE 的特殊情況
        int sign = (dividend > 0) == (divisor > 0) ? 1 : -1;
        long longDividend = Math.abs((long) dividend);
        long longDivisor = Math.abs((long) divisor);

        long result = 0;
        while (longDividend >= longDivisor) {
            long tempDivisor = longDivisor;
            long multiple = 1;
            while (longDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }
            longDividend -= tempDivisor;
            result += multiple;
        }

        // 根據符號返回最終結果
        if (sign == -1) {
            result = -result;
        }

        // 處理結果溢位
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }
}