/**
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 */
public class MaxOneBorderSize {
    public static int getMaxSize(int[][] m) {
        // right[i][j] 为 (i,j) 位置右边有几个 1
        int[][] right = new int[m.length][m[0].length];
        // down[i][j] 为 (i,j) 位置下边有几个 1
        int[][] down = new int[m.length][m[0].length];
        // 计算 right down 数组
        setBorderMap(m, right, down);
        // 遍历所有可能的边长
        for (int size = Math.min(m.length, m[0].length); size > 0; size --) {
            // 判断是否有符合条件的边长
            if (hasSizeBorder(size, right, down)) {
                return size;
            }
        }
        return 0;
    }

    private static boolean hasSizeBorder(int size, int[][] right, int[][] down) {
        // 遍历满足边长为 size 可能的所有位置
        for (int i = 0; i < right.length - size + 1; i ++) {
            for (int j = 0; j < right[0].length - size + 1; j ++) {
                // 判断四个角是否满足条件（边框全为 1）
                if (right[i][j] >= size && right[i + size - 1][j] >= size &&
                        down[i][j] >= size && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        // 计算右下角
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        // 计算右边界
        for (int i = r - 2; i >= 0; i --) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        // 计算下边界
        for (int i = c - 2; i >= 0; i --) {
            if (m[r - 1][i] == 1) {
                down[r - 1][i] = 1;
                right[r - 1][i] = right[r - 1][i + 1] + 1;
            }
        }
        // 计算其余位置
        for (int i = r - 2; i >= 0; i --) {
            for (int j = c - 2; j >= 0; j --) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 8);
        printMatrix(matrix);
        System.out.println(getMaxSize(matrix));
    }
}
