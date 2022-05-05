package dp;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * 从左往右的尝试模型
 */
public class Knapsack {
    public static int getMaxValue1(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);
    }

    /**
     * 0 ~ i-1 已经选完了，从 i 开始选
     * @param w 重量数组
     * @param v 价值数组
     * @param i i 号货物
     * @param curWeight 当前已经拿了的货物重量
     * @return 最大价值
     */
    private static int process(int[] w, int[] v, int i, int curWeight, int bag) {
        // base case 1
        // 当前重量超过了背包载重
        if (curWeight > bag) {
            return -1;
        }
        // base case 2
        // 已经没有货物可以拿了，返回 0
        if (i == w.length) {
            return 0;
        }
        // 不拿 i 号货物
        int notTake = process(w, v, i + 1, curWeight, bag);
        // 如果拿了 i 号货物之后，当前货物重量不超过背包载重，拿 i 号货物
        int take = process(w, v, i + 1, curWeight + w[i], bag);
        if (take != -1) {
            take += v[i];
        }
        return Math.max(notTake, take);
    }

    public static int getMaxValue2(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    /**
     * 0 ~ i-1 已经选完了，从 i 开始选
     * @param w 重量数组
     * @param v 价值数组
     * @param i i 号货物
     * @param rest 背包剩余载重
     * @return 最大价值
     */
    private static int process(int[] w, int[] v, int i, int rest) {
        // base case 1
        // 背包没有剩余空间了，返回 0
        if (rest <= 0) {
            return 0;
        }
        // base case 2
        // 已经没有货物可以拿了，返回 0
        if (i == w.length) {
            return 0;
        }
        // 不拿 i 号货物
        int notTake = process(w, v, i + 1, rest);
        // 如果 i 号货物重量小于等于背包剩余载重，拿 i 号货物
        int take = Integer.MIN_VALUE;
        if (rest >= w[i]) {
            take = v[i] + process(w, v, i + 1, rest - w[i]);
        }
        return Math.max(notTake, take);
    }

    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        // dp[i][j]：当前拿 i (0~N)号货物，剩余 j (0~bag)重量，可以装的货物的最大价值
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...] = 0，从 N - 1 行开始填
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 1; j <= bag; j++) {
                // 不拿 i 号货物
                dp[i][j] = dp[i + 1][j];
                // 如果当前剩余载重大于等于当前货物重量，可以选择拿 i 号货物
                // 取不拿 i 号货物和拿 i 号货物的较大值
                if (j >= w[i]) {
                    dp[i][j] = Math.max(v[i] + dp[i + 1][j - w[i]], dp[i][j]);
                }
            }
        }
        // 返回从 0 号货物开始拿，剩余 bag 载重时可以拿到的货物的最大价值
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(getMaxValue1(weights, values, bag));
        System.out.println(getMaxValue2(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }
}
