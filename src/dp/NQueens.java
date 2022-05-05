package dp;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class NQueens {
    public static int nQueens1(int n) {
        if (n < 1) {
            return 0;
        }
        // 记录第 i 行的皇后放在第几列
        int[] record = new int[n];
        return process1(0, record, n);
    }

    // record[0..i-1]的皇后，任何两个皇后都摆好了（也就是所有皇后都不共行、不共列、不共斜线）
    // 现在开始摆放第 i 行的皇后
    private static int process1(int i, int[] record, int n) {
        // base case
        // 所有行的皇后都摆好了，返回 1 种结果
        if (i == n) {
            return 1;
        }
        int res = 0;
        // 开始摆放皇后
        for (int j = 0; j < n; j++) {
            // 如果第 j 列满足不共行、不共列、不共斜线的条件
            // 在 i 行的 j 列摆放皇后，去 i + 1 行摆放皇后
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        // 遍历当前行 i 行之前的所有行
        for (int k = 0; k < i; k++) {
            // 如果 record[k] == j，说明共列
            // 如果 Math.abs(i - k) == Math.abs(j - record[k])，说明共斜线
            // 这两种情况都属于无效摆放
            if (record[k] == j || Math.abs(i - k) == Math.abs(j - record[k])) {
                return false;
            }
        }
        return true;
    }

    public static int nQueens2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // limit：n 个 1，最终所有皇后都摆放完成的状态
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * 基于列限制，左斜线限制，右斜线限制摆放皇后
     * @param limit 状态限制
     * @param colLimit 列限制，为 1 的位置不能放皇后，为 0 的位置可以
     * @param leftDiaLimit 左斜线限制，为 1 的位置不能放皇后，为 0 的位置可以
     * @param rightDiaLimit 右斜线限制，为 1 的位置不能放皇后，为 0 的位置可以
     * @return 皇后摆放方式
     */
    private static int process2(int limit,
                                int colLimit,
                                int leftDiaLimit,
                                int rightDiaLimit) {
        // base case 所有皇后都摆放好了，返回 1 种摆放方式
        if (colLimit == limit) {
            return 1;
        }
        int res = 0;
        // 可以摆放皇后的候选位置，为 1 的位置可以放皇后，为 0 的位置不能放皇后
        int pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        int mostRightOne;
        // 当有位置可以放皇后时
        while (pos != 0) {
            // 在最右的 1 的位置摆放皇后
            mostRightOne = pos & (~pos + 1);
            // 将摆放了皇后的位置置 0
            pos = pos ^ mostRightOne;
            // 下次摆放的限制：
            // 列限制：之前 colLimit 上所有 1 的位置和 mostRightOne 所代表的 1 的位置已经放了皇后了，下次不能再放了
            // 左斜线限制：之前 leftDiaLimit 和 mostRightOne 左下角的位置不能放皇后了
            // 右斜线限制：之前rightDiaLimit 和 mostRightOne 右下角的位置不能放皇后了
            res += process2(limit,
                    colLimit | mostRightOne,
                    (leftDiaLimit | mostRightOne) << 1,
                    (rightDiaLimit | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(nQueens1(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(nQueens2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
