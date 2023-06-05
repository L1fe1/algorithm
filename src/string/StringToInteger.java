package string;

/**
 * <a href="https://leetcode.cn/problems/string-to-integer-atoi/">...</a>
 */
public class StringToInteger {
    public static int myAtoi(String s) {
        int res = 0;
        String str = s.trim();
        boolean isNegative = false;
        int minQ = Integer.MIN_VALUE / 10;
        int minR = Integer.MIN_VALUE % 10;
        for (int i = 0; i < str.length(); i ++) {
            char c = str.charAt(i);
            if (i == 0) {
                // 正负判断
                isNegative = c == '-';
                if (c == '+' || c == '-') {
                    continue;
                } else if (c < '0' || c > '9') {
                    // 无效数字
                    return 0;
                }
            }
            if (c >= '0' && c <= '9') {
                // 越界判断
                if (res < minQ || (res == minQ && ('0' - c) <= minR)) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                res = res * 10 + ('0' - c);
            } else {
                break;
            }
        }
        return isNegative ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("2147483648"));
    }
}
