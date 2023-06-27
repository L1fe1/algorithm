package math;

/**
 * <a href="https://leetcode.cn/problems/sqrtx/">...</a>
 */
public class SqrtX {
    public static int mySqrt(int x) {
        int l = 0;
        int r = x;
        int mid;
        int ans = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}
