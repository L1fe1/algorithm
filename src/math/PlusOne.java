package math;

/**
 * <a href="https://leetcode.cn/problems/plus-one/">...</a>
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] ++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] ans = new int[len + 1];
        ans[0] = 1;
        return ans;
    }
}
