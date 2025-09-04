class Solution {

    /**
     * 解決數獨問題
     *
     * @param board 9x9 的數獨盤，用字元陣列表示，'.' 代表空單元格
     */
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    /**
     * 回溯函數，嘗試填滿數獨盤
     *
     * @param board 待填滿的數獨盤
     * @return 如果成功找到解，則回傳 true；否則回傳 false。
     */
    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // 找到第一個空格
                if (board[row][col] == '.') {
                    // 嘗試填入 1-9 的數字
                    for (char num = '1'; num <= '9'; num++) {
                        if (isSafe(board, row, col, num)) {
                            // 如果數字安全，則填入
                            board[row][col] = num;

                            // 遞迴嘗試填滿下一個空格
                            if (solve(board)) {
                                return true; // 找到解，直接回傳 true
                            } else {
                                // 如果遞迴失敗，則回溯，將當前空格重置
                                board[row][col] = '.';
                            }
                        }
                    }
                    // 如果嘗試了 1-9 都無法填入，則此路不通，回傳 false
                    return false;
                }
            }
        }
        // 如果沒有找到空格，表示所有位置都已填滿，數獨已解！
        return true;
    }

    /**
     * 檢查在指定位置填入指定數字是否合法
     *
     * @param board 數獨盤
     * @param row   要檢查的行
     * @param col   要檢查的列
     * @param num   要填入的數字
     * @return 如果合法，則回傳 true；否則回傳 false。
     */
    private boolean isSafe(char[][] board, int row, int col, char num) {
        // 檢查同一列是否有重複
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // 檢查同一行是否有重複
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        // 檢查所在的 3x3 子網格是否有重複
        // 計算子網格的起始列和起始行
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        // 所有檢查都通過，數字在此位置是安全的
        return true;
    }
}