package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/burst-balloons/">...</a>
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        // 首尾扩展，方便计算边界得分
        int[] extNums = new int[len + 2];
        extNums[0] = extNums[len + 1] = 1;
        for (int i = 1; i <= len; i++) {
            extNums[i] = nums[i - 1];
        }
        int[][] dp = new int[len + 2][len + 2];
        for (int i = 0; i < len + 2; i++) {
            for (int j = 0; j < len + 2; j++) {
                dp[i][j] = -1;
            }
        }
        return process(extNums, 1, len, dp);
    }

    /**
     * nums[l...r] 范围上，选择最优的打爆顺序，返回最大的得分。
     * 想要调用递归，必须满足如下条件：
     * nums[l-1] 的气球，一定没爆；
     * nums[r+1] 的气球，一定没爆。
     */
    public int process(int[] nums, int l, int r, int[][] dp) {
        // base case
        // 只剩最后一个气球了
        if (l == r) {
            return nums[l] * nums[l - 1] * nums[r + 1];
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        // 最后打爆 l 位置的气球
        int p1 = nums[l] * nums[l - 1] * nums[r + 1] + process(nums, l + 1, r, dp);
        // 最后打爆 r 位置的气球
        int p2 = nums[r] * nums[l - 1] * nums[r + 1] + process(nums, l, r - 1, dp);
        int ans = Math.max(p1, p2);
        // 最后打爆 l...r 中间某个位置的气球
        for (int i = l + 1; i < r; i++) {
            int left = process(nums, l, i - 1, dp);
            int mid = nums[i] * nums[l - 1] * nums[r + 1];
            int right = process(nums, i + 1, r, dp);
            ans = Math.max(ans, left + mid + right);
        }
        dp[l][r] = ans;
        return ans;
    }

    public static int maxCoins1(int[] nums) {
        int len = nums.length;
        // 首尾扩展，方便计算边界得分
        int[] extNums = new int[len + 2];
        extNums[0] = extNums[len + 1] = 1;
        for (int i = 1; i <= len; i++) {
            extNums[i] = nums[i - 1];
        }
        int[][] dp = new int[len + 2][len + 2];
        for (int i = 1; i <= len; i++) {
            dp[i][i] = extNums[i] * extNums[i - 1] * extNums[i + 1];
        }
        for (int i = len; i >= 1; i--) {
            for (int j = i + 1; j <= len; j++) {
                // 最后打爆 i 位置的气球
                int p1 = extNums[i] * extNums[i - 1] * extNums[j + 1] + dp[i + 1][j];
                // 最后打爆 j 位置的气球
                int p2 = extNums[j] * extNums[i - 1] * extNums[j + 1] + dp[i][j - 1];
                int ans = Math.max(p1, p2);
                // 最后打爆 i...j 中间某个位置的气球
                for (int k = i + 1; k < j; k++) {
                    int left = dp[i][k - 1];
                    int mid = extNums[k] * extNums[i - 1] * extNums[j + 1];
                    int right = dp[k + 1][j];
                    ans = Math.max(ans, left + mid + right);
                }
                dp[i][j] = ans;
            }
        }
        // 生成一条路径
        pi = len - 1;
        int[] p = new int[len];
        generatePath(dp, extNums, 1, len, p);
        // 还原成原始的气球编号
        for (int i = 0; i < len; i++) {
            p[i] --;
        }
        // 生成所有路径
        generateAllPath(dp, extNums, 1, len, len);
        return dp[1][len];
    }

    public static int pi;

    public static void generatePath(int[][] dp, int[] nums, int l, int r, int[] path) {
        if (l == r) {
            // 只剩一个气球了
            path[pi--] = l;
        } else {
            if (dp[l][r] == nums[l] * nums[l - 1] * nums[r + 1] + dp[l + 1][r]) {
                // 最后打爆 l 位置的气球
                path[pi--] = l;
                generatePath(dp, nums, l + 1, r, path);
            } else if (dp[l][r] == nums[r] * nums[l - 1] * nums[r + 1] + dp[l][r - 1]) {
                // 最后打爆 r 位置的气球
                path[pi--] = r;
                generatePath(dp, nums, l, r - 1, path);
            } else {
                // 最后打爆 l...r 中间某个位置的气球
                for (int i = l + 1; i < r; i++) {
                    int left = dp[l][i - 1];
                    int mid = nums[i] * nums[l - 1] * nums[r + 1];
                    int right = dp[i + 1][r];
                    if (left + right + mid == dp[l][r]) {
                        path[pi--] = i;
                        generatePath(dp, nums, l, i - 1, path);
                        generatePath(dp, nums, i + 1, r, path);
                        break;
                    }
                }
            }
        }
    }

    public static List<List<Integer>> allPath = new ArrayList<>();
    public static List<Integer> path = new ArrayList<>();

    public static void generateAllPath(int[][] dp, int[] nums, int l, int r, int len) {
        if (l == r) {
            // 只剩一个气球了
            path.add(l);
            if (path.size() == len) {
                addPath(path, allPath);
            }
            path.remove(path.size() - 1);
        } else {
            if (dp[l][r] == nums[l] * nums[l - 1] * nums[r + 1] + dp[l + 1][r]) {
                // 最后打爆 l 位置的气球
                path.add(l);
                generateAllPath(dp, nums, l + 1, r, len);
                path.remove(path.size() - 1);
            }
            if (dp[l][r] == nums[r] * nums[l - 1] * nums[r + 1] + dp[l][r - 1]) {
                // 最后打爆 r 位置的气球
                path.add(r);
                generateAllPath(dp, nums, l, r - 1, len);
                path.remove(path.size() - 1);
            }
            // 最后打爆 l...r 中间某个位置的气球
            for (int i = l + 1; i < r; i++) {
                int left = dp[l][i - 1];
                int mid = nums[i] * nums[l - 1] * nums[r + 1];
                int right = dp[i + 1][r];
                if (left + right + mid == dp[l][r]) {
                    path.add(i);
                    generateAllPath(dp, nums, l, i - 1, len);
                    generateAllPath(dp, nums, i + 1, r, len);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void addPath(List<Integer> path, List<List<Integer>> allPath) {
        List<Integer> realPath = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            realPath.add(path.get(i) - 1);
        }
        allPath.add(realPath);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 3, 3};
        System.out.println(maxCoins1(nums));
    }
}
