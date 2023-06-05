package array;

/**
 * <a href="https://leetcode.cn/problems/valid-sudoku/">...</a>
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] buckets = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 桶编号
                int bucket = 3 * (i / 3) + j / 3;
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (rows[i][num] || cols[j][num] || buckets[bucket][num]) {
                        // 行/列/桶有重复的数字，无效
                        return false;
                    }
                    rows[i][num] = true;
                    cols[j][num] = true;
                    buckets[bucket][num] = true;
                }
            }
        }
        return true;
    }
}
