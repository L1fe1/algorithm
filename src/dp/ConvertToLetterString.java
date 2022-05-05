package dp;

/**
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * 从左往右的尝试模型
 */
public class ConvertToLetterString {
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // 0 ~ i-1 位置的字符已经做过转化了，从 i 位置开始做转化有多少种转化结果
    private static int process(char[] chars, int i) {
        // base case
        // 0 ~ chars.length-1 位置完成转化了，记录为 1 次转化结果
        if (i == chars.length) {
            return 1;
        }
        // i 位置为 '0'，没有转化结果（字母对应的范围为 1~ 26）
        if (chars[i] == '0') {
            return 0;
        }
        // i 位置为 '1'，那么可以有两种转化方式：
        // 1. 1 转化为 A，去下一个位置继续转化
        // 2. 如果 i + 1 不越界，i 和 i + 1 位置转化为某个字母，去 i + 2 位置继续转化
        if (chars[i] == '1') {
            int res = process(chars, i + 1);
            if (i + 1 < chars.length) {
                res += process(chars, i + 2);
            }
            return res;
        }
        // i 位置为 '2'，那么可以有两种转化方式：
        // 1. 2 转化为 B，去下一个位置继续转化
        // 2. 如果 i + 1 不越界，且 i 和 i + 1 位置组成的数字在 20 ~ 26 之间，它们转化为这个范围的某个字母，去 i + 2 位置继续转化
        if (chars[i] == '2') {
            int res = process(chars, i + 1);
            if (i + 1 < chars.length && (chars[i + 1] >= '0' && chars[i + 1] <= '6')) {
                res += process(chars, i + 2);
            }
            return res;
        }
        // i 位置为 '3' ~ '9'，那么 i 只有一种转化方式，去 i + 1 位置继续转化
        return process(chars, i + 1);
    }

    public static int dpWays(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] arr = str.toCharArray();
        int l = str.length();
        // dp[i]：从 i 位置开始做转化有多少种转化结果
        int[] dp = new int[l + 1];
        // 0 ~ str.length()-1 位置完成转化了，记录为 1 次转化结果
        dp[l] = 1;
        // dp[l] = 1，从 l - 1 开始填
        for (int i = l - 1; i >= 0; i--) {
            // i 位置为 '0'，没有转化结果
            if (arr[i] == '0') {
                dp[i] = 0;
            } else if (arr[i] == '1') {
                // i 位置为 '1'
                // 如果 i + 1 不越界，可以 i 位置转化为 A，去下一个位置继续转化
                // 或者 i 和 i + 1 位置转化为某个字母，去 i + 2 位置继续转化
                dp[i] = dp[i + 1];
                if (i + 1 < l) {
                    dp[i] += dp[i + 2];
                }
            } else if (arr[i] == '2') {
                // i 位置为 '2'
                // 如果 i + 1 不越界，且 i 和 i + 1 位置组成的数字在 20 ~ 26 之间
                // 可以 i 位置转化为 B，去下一个位置继续转化
                // 或者它们转化为这个范围的某个字母，去 i + 2 位置继续转化
                dp[i] = dp[i + 1];
                if (i + 1 < l && arr[i + 1] >= '0' && arr[i + 1] <= '6') {
                    dp[i] += dp[i + 2];
                }
            } else {
                // i 位置为 '3' ~ '9'，那么 i 只有一种转化方式，去 i + 1 位置继续转化
                dp[i] = dp[i + 1];
            }
        }
        // 返回从 0 位置开始做转化有多少种转化结果
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("11111"));
        System.out.println(dpWays("11111"));
    }
}
