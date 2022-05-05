package dp;

/**
 * 给定数组arr，arr中所有的值都为正数且不重复
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张
 * 再给定一个整数 aim，代表要找的钱数
 * 求组成 aim 的方法数
 */
public class CoinsWay {
    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    // 0~i-1 位置的货币选完了，自由使用 i 位置货币的面值，组成 rest 这么多钱，有多少种方法
    private static int process(int[] arr, int i, int rest) {
        // base case
        // 货币使用完了，剩余需要组成的 rest 为 0，返回一种方法
        if (i == arr.length) {
            return rest == 0 ? 1: 0;
        }
        int ans = 0;
        // i 位置的货币使用任意 num 张，但是总面值不要超过 rest
        for (int num = 0; num * arr[i] <= rest; num++) {
            // 去 i + 1 位置做选择，剩余需要组成的 rest 为 rest - num * arr[i]
            ans += process(arr, i + 1, rest - num * arr[i]);
        }
        return ans;
    }

    public static int dpWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int len = arr.length;
        // dp[i][j]：自由使用 i 位置货币的面值，组成 j 这么多钱，有多少种方法
        int[][] dp = new int[len + 1][aim + 1];
        // 货币使用完了，rest 为 0，代表一种方法
        dp[len][0] = 1;
        // 从下往上，从左往右遍历
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                // 任意 dp[i][j] = dp[i+1][j] + dp[i+1][j - arr[i]] + dp[i+1][j - 2 * arr[i]]
                // + ... + dp[i+1][j - num * arr[i]]（j >= num * arr[i]）
                // 可以得出 dp[i][j - arr[i]] = dp[i+1][j - arr[i]] + dp[i+1][j - 2 * arr[i]]
                // + ... + dp[i+1][j - num * arr[i]]（j >= num * arr[i]）
                // 所以如果 j >= arr[i]，那么有 dp[i][j] = dp[i + 1][j] + dp[i][j - arr[i]]
                dp[i][j] = dp[i + 1][j];
                if (j >= arr[i]) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 3, 1 };
        int sum = 350;
        System.out.println(ways(arr, sum));
        System.out.println(dpWays(arr, sum));
    }
}
