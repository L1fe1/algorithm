package dp;

/**
 * 两个字符串的最长公共子序列问题
 */
public class LongestCommonSubsequence {
    public static int lcse(char[] str1, char[] str2) {
        int l1 = str1.length, l2 = str2.length;
        // dp[i][j]：str1 0~i 的子串和 str2 0~j 的子串的最长公共子序列的长度
        int[][] dp = new int[l1][l2];
        // 只有一个字符，只有 str1[0] 和 str2[0] 相等，才有公共子序列，且长度为 1
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        // str2 只有一个字符，那么只要 str1[i] 和 str2[0] 相等，那么有公共子序列
        // 而且 0~ i 位置后续所有的子串都有公共子序列，长度为 1
        for (int i = 1; i < l1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        // str1 只有一个字符，那么只要 str2[i] 和 str1[0] 相等，那么有公共子序列
        // 而且 0~ i 位置后续所有的子串都有公共子序列，长度为 1
        for (int i = 1; i < l2; i++) {
            dp[0][i] =  Math.max(dp[0][i - 1], str1[0] == str2[i] ? 1 : 0);
        }
        for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                // dp[i][j] 有四种情况：
                // 1. 最长公共子序列既不以 str1 的 i 字符结尾，也不以 str2 的 j 字符结尾
                // 那么 dp[i][j] 等价于 dp[i - 1][j - 1]
                // 2. 最长公共子序列以 str1 的 i 字符结尾，而不以 str2 的 j 字符结尾
                // 那么 dp[i][j] 等价于 dp[i][j - 1]
                // 3. 最长公共子序列以 str2 的 j 字符结尾，而不以 str1 的 i 字符结尾
                // 那么 dp[i][j] 等价于 dp[i - 1][j]
                // 4. 最长公共子序列既以 str1 的 i 字符结尾，又以 str2 的 j 字符结尾
                // 那么必有 str1[i] = str2[j]，且 dp[i][j] 等价于 dp[i - 1][j - 1] + 1
                // 如果 4 成立，那么 4 必定比 1 大，而且 2 和 3 在计算时依赖于 1 ，因此 2、3
                // 不可能比 1 小，所以可以省略 1 的判断
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[l1 - 1][l2 - 1];
    }

    public static void main(String[] args) {
        char[] str1 = {'g', 'b', 'c', 'd', '1', '5', '3'};
        char[] str2 = {'a', 'b', 'c', 'e', '1', '2', '3'};
        System.out.println(lcse(str1, str2));
    }
}
