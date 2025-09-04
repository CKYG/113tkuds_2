import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * 判斷一個數獨盤是否合法
     *
     * @param board 9x9 的數獨盤，用字元陣列表示，'.' 代表空單元格
     * @return 如果數獨盤合法，則回傳 true；否則回傳 false。
     */
    public boolean isValidSudoku(char[][] board) {
        // 檢查每一行
        for (int i = 0; i < 9; i++) {
            if (!isUnitValid(board, i, 0, i, 8)) {
                return false;
            }
        }

        // 檢查每一列
        for (int j = 0; j < 9; j++) {
            if (!isUnitValid(board, 0, j, 8, j)) {
                return false;
            }
        }

        // 檢查每一個 3x3 的九宮格
        for (int boxRowStart = 0; boxRowStart < 9; boxRowStart += 3) {
            for (int boxColStart = 0; boxColStart < 9; boxColStart += 3) {
                if (!isUnitValid(board, boxRowStart, boxColStart, boxRowStart + 2, boxColStart + 2)) {
                    return false;
                }
            }
        }

        // 如果所有檢查都通過，則數獨盤合法
        return true;
    }

    /**
     * 檢查一個「單位」（行、列或九宮格）是否合法
     *
     * @param board   數獨盤
     * @param row1    起始列
     * @param col1    起始行
     * @param row2    結束列
     * @param col2    結束行
     * @return 如果該單位合法，則回傳 true；否則回傳 false。
     */
    private boolean isUnitValid(char[][] board, int row1, int col1, int row2, int col2) {
        Set<Character> seen = new HashSet<>();
        // 根據 row1, col1, row2, col2 的組合來迭代，確保能覆蓋行、列、九宮格
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                char currentVal = board[i][j];

                // 只檢查非空單元格
                if (currentVal != '.') {
                    // 如果該數字已在集合中，表示有重複，該單位不合法
                    if (seen.contains(currentVal)) {
                        return false;
                    }
                    // 將數字加入集合
                    seen.add(currentVal);
                }
            }
        }
        // 沒有發現重複數字，該單位合法
        return true;
    }
}