package dp;

import java.util.Arrays;

public class DecodeWays {
    public static long MOD = 1000000007;

    public static int numDecodings1(String s) {
        long[] dp = new long[s.length()];
        Arrays.fill(dp, -1);
        return (int) process(s.toCharArray(), 0, dp);
    }

    // 0...i-1 的字符已经转化完了，现在来到 i 位置开始转化
    public static long process(char[] chars, int i, long[] dp) {
        if (i == chars.length) {
            // 所有字符都转化完了
            return 1;
        }
        char c = chars[i];
        if (c == '0') {
            // 当前字符是 0，无法转化
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        long ans;
        if (c != '*') {
            // i 位置不为 *
            long p1 = process(chars, i + 1, dp);
            if (i == chars.length - 1) {
                p1 %= MOD;
                dp[i] = p1;
                return p1;
            }
            char next = chars[i + 1];
            long p2 = 0;
            if (next != '*') {
                int num = (c - '0') * 10 + (next - '0');
                p2 = num <= 26 ? process(chars, i + 2, dp) : 0;
            } else {
                if (c == '1') {
                    // 有 11~19 9 种转化方式
                    p2 = 9 * process(chars, i + 2, dp);
                }
                if (c == '2') {
                    // 有 21~26 6 种转化方式
                    p2 = 6 * process(chars, i + 2, dp);
                }
            }
            ans = p1 + p2;
        } else {
            // i 位置为 *，i 位置可以表示为 1~9
            long p1 = 9 * process(chars, i + 1, dp);
            if (i == chars.length - 1) {
                p1 %= MOD;
                dp[i] = p1;
                return p1;
            }
            char next = chars[i + 1];
            long p2;
            if (next != '*') {
                // 如果 i + 1 位置小于等于 6，那么 i 位置可以表示为 1 或者 2；否则 i 位置只能表示为 1
                p2 = next <= '6' ? 2 * process(chars, i + 2, dp) : process(chars, i + 2, dp);
            } else {
                // 有 11~19 和 21~26 总共 15 种转化方式
                p2 = 15 * process(chars, i + 2, dp);
            }
            ans = p1 + p2;
        }
        ans %= MOD;
        dp[i] = ans;
        return ans;
    }

    public static int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        long[] dp = new long[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                dp[i] = dp[i + 1] * (chars[i] == '*' ? 9 : 1);
                if (chars[i] == '1' || chars[i] == '2' || chars[i] == '*') {
                    if (i + 1 < len) {
                        if (chars[i + 1] == '*') {
                            dp[i] += dp[i + 2] * (chars[i] == '*' ? 15 : (chars[i] == '1' ? 9 : 6));
                        } else {
                            if (chars[i] == '*') {
                                dp[i] += dp[i + 2] * (chars[i + 1] <= '6' ? 2 : 1);
                            } else {
                                dp[i] += ((chars[i] - '0') * 10 + chars[i + 1] - '0') <= 26 ? dp[i + 2] : 0;
                            }
                        }
                    }
                }
                dp[i] %= MOD;
            }
        }
        return (int) dp[0];
    }


    public static void main(String[] args) {
        System.out.println(numDecodings2("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*"));
    }
}
