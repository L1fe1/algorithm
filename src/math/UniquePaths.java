package math;

/**
 * <a href="https://leetcode.cn/problems/unique-paths/">...</a>
 */
public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        // 需要往右走的步数
        int right = n - 1;
        // 总共需要走的步数
        int all = m + n - 2;
        // 最终结果为排列组合 C(all, right)
        long o1 = 1;
        long o2 = 1;
        for (int i = all, j = m - 1; i > right; i--, j --) {
            o1 *= i;
            o2 *= j;
            // 计算最大公约数
            long gcd = gcd(o1, o2);
            o1 /= gcd;
            o2 /= gcd;
        }
        return (int) o1;
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
    }
}
