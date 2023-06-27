package array;

/**
 * <a href="https://leetcode.cn/problems/set-matrix-zeroes/">...</a>
 */
public class SetMatrixZeroes {
    public void setZeroes1(int[][] matrix) {
        // 第 0 行是否有 0
        boolean row0Zero = false;
        // 第 0 列是否有 0
        boolean col0Zero = false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                row0Zero = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0Zero = true;
                break;
            }
        }
        // 标记 1...m-1 行， 1...n-1 列出现的 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 1...m-1 行， 1...n-1 列置 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 0 行置 0
        if (row0Zero) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        // 0 列置 0
        if (col0Zero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        // 第 0 列是否有 0
        boolean col0Zero = false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0Zero = true;
                break;
            }
        }
        // matrix[0][0] 记录第 0 行是否有 0
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                matrix[0][0] = 0;
                break;
            }
        }
        // 标记 1...m-1 行， 1...n-1 列出现的 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 1...m-1 行， 1...n-1 列置 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 0 行置 0
        if (matrix[0][0] == 0) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        // 0 列置 0
        if (col0Zero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
