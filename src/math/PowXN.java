package math;

/**
 * <a href="https://leetcode.cn/problems/powx-n/">...</a>
 */
public class PowXN {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        // 将 n 变为正数，如果 n 为 int 最小值，将它加 1 防止越界
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double t = x;
        double ans = 1.0;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                // pow 二进制最低位为 1
                ans *= t;
            }
            // t -> x^1, x^2, x^4, x^8...
            t *= t;
            pow = pow >> 1;
        }
        if (n == Integer.MIN_VALUE) {
            // n 为 int 最小值，结果需要多除一个 x
            return 1 / (ans * x);
        }
        return n < 0 ? 1 / ans : ans;
    }
}
