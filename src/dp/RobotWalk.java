package dp;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk {
    public static int ways1(int N, int M, int K, int P) {
        // 无效参数直接返回0
        if (N < 2 || M > N || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        // 总共 N 个位置，从 M 点出发，还剩 K 步，返回最终能达到 P 的方法数
        return walk(N, M, K, P);
    }

    /**
     * 只能在 1~n 这些位置上移动，当前在 cur 位置，走完 rest 步之后，可以停在 p 位置的方法数
     * @param n 位置数
     * @param cur 当前位置
     * @param rest 剩余步数
     * @param p 终点位置
     * @return 方法数
     */
    private static int walk(int n, int cur, int rest, int p) {
        // base case
        // 步数走完了，如果当前位置 cur 在终点 p 上，返回 1 种走法；否则返回 0 种
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }
        // 当前位置来到了 1，下一步只能走 2，剩下 rest - 1 步可以走
        if (cur == 1) {
            return walk(n, 2, rest - 1, p);
        }
        // 当前位置来到了 n，下一步只能走 n - 1，剩下 rest - 1 步可以走
        if (cur == n) {
            return walk(n, n - 1, rest - 1, p);
        }
        // 当前位置来到了 1 和 n 之外中间的某个位置
        // 下一步可以走左边，cur 来到 cur - 1 的位置
        // 下一步也可以走右边，cur 来到 cur + 1 的位置
        // 剩下 rest - 1 步可以走
        return walk(n, cur - 1, rest - 1, p) + walk(n, cur + 1, rest - 1, p);
    }

    public static int ways2(int N, int M, int K, int P) {
        // 无效参数直接返回0
        if (N < 2 || M > N || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        // dp[i][j]：当前剩余 i 步，当前在 j 位置（可以在 1 ~N 位置走），最终能走到 P 的方法数
        int[][] dp = new int[K + 1][N + 1];
        // 剩余 0 步，当前在 P 位置，记录 1 种走法
        dp[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    // 当前位置在 1，只能往 2 走
                    dp[i][1] = dp[i - 1][2];
                } else if (j == N) {
                    // 当前位置在 N，只能往 N - 1 走
                    dp[i][N] = dp[i - 1][N - 1];
                } else {
                    // 当前不在 1 或者 N 位置，可以往左走或往右走
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }

    public static int ways3(int N, int M, int K, int P) {
        // 无效参数直接返回0
        if (N < 2 || M > N || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return 0;
    }

    public static int ways4(int N, int M, int K, int P) {
        // 无效参数直接返回0
        if (N < 2 || M > N || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return 0;
    }

    public static int ways5(int N, int M, int K, int P) {
        // 无效参数直接返回0
        if (N < 2 || M > N || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
        System.out.println(ways3(7, 4, 9, 5));
        System.out.println(ways4(7, 4, 9, 5));
        System.out.println(ways5(7, 4, 9, 5));
    }
}
