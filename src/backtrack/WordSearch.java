package backtrack;

/**
 * <a href="https://leetcode.cn/problems/word-search/">...</a>
 */
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 来到 (i,j) 位置，如果能搜索到单词，则返回 true
                if (process(board, chars, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 目前已经来到 (i,j) 位置，需要搜索到 word 中 k 位置及以后的字符
    public static boolean process(char[][] board, char[] chars, int i, int j, int k) {
        if (k == chars.length) {
            // 所有字符都已经搞定了
            return true;
        }
        // chars[k] 还有字符但是 i 或 j 越界了
        if (i < 0 || i == board.length || j < 0 || j == board[i].length) {
            return false;
        }
        char c = board[i][j];
        if (c != chars[k]) {
            // 当前字符不匹配
            return false;
        }
        // 走过的字符标记为 0，防止重复使用
        board[i][j] = 0;
        // 往上下左右四个方向尝试
        boolean res = process(board, chars, i - 1, j, k + 1) || process(board, chars, i, j - 1, k + 1)
                || process(board, chars, i + 1, j, k + 1) || process(board, chars, i, j + 1, k + 1);
        // 还原字符
        board[i][j] = c;
        return res;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }
}
