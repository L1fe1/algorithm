package array;

/**
 * <a href="https://leetcode.cn/problems/sudoku-solver/">...</a>
 */
public class SudokuSolver {
    public static void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] buckets = new boolean[9][10];
        init(board, rows, cols, buckets);
        process(board, 0, 0, rows, cols, buckets);
    }

    private static void init(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] buckets) {
        // 初始化
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bucket = 3 * (i / 3) + j / 3;
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    buckets[bucket][num] = true;
                }
            }
        }
    }

    // 当前来到 (i,j) 位置填数字，如果已经有数字，跳到下一个位置，如果没有数字，填写数字（不能和行/列/桶重复）
    public static boolean process(char[][] board, int i, int j, boolean[][] rows, boolean[][] cols, boolean[][] buckets) {
        if (i == 9) {
            // 所有数字都填完了
            return true;
        }
        int nextI = j == 8 ? i + 1 : i;
        int nextJ = j == 8 ? 0 : j + 1;
        if (board[i][j] == '.') {
            // 填数字
            int bucket = 3 * (i / 3) + j / 3;
            for (int num = 1; num <= 9; num++) {
                if (!rows[i][num] && !cols[j][num] && !buckets[bucket][num]) {
                    // 尝试所有能填的数字
                    rows[i][num] = true;
                    cols[j][num] = true;
                    buckets[bucket][num] = true;
                    board[i][j] = (char) (num + '0');
                    if (process(board, nextI, nextJ, rows, cols, buckets)) {
                        return true;
                    }
                    // 恢复现场
                    board[i][j] = '.';
                    rows[i][num] = false;
                    cols[j][num] = false;
                    buckets[bucket][num] = false;
                }
            }
            return false;
        } else {
            // 跳到下一个位置
            return process(board, nextI, nextJ, rows, cols, buckets);
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        System.out.println();
    }
}
