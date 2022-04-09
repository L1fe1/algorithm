/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 返回一个括号字符串中，最长的括号有效子串的长度
 */
public class LongestEffectiveBracketsSubStringLength {
    public static int longestEffectiveBracketsSubStringLength(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int res = 0;
        // 记录以第 i 位置结尾的字符最长有效括号子串的长度
        int[] dp = new int[s.length()];
        int pre = 0;
        for (int i = 1; i < s.length(); i ++) {
            // 如果是右括号，再往前找；否则 dp[i] = 0
            if (s.charAt(i) == ')') {
                // 找到 i 之前跨过最长有效括号子串长度的前一个位置
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && s.charAt(pre) == '(') {
                    /**
                     * 如果 pre 没有越界，则 dp[i] 至少为 dp[i - 1] + 2（i 位置和 pre 位置匹配的两个括号）
                     * 如果 pre > 0，则继续累加 pre 前一个位置的最长有效括号子串长度
                     */
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
