package string;

/**
 * <a href="https://leetcode.cn/problems/roman-to-integer/">...</a>
 */
public class RomanToInteger {
    public static int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            // 转换成数字
            int cur = transferToInt(chars[i]);
            int next = transferToInt(chars[i + 1]);
            // 如果当前数字比它下一个数字小，累加上它的负数值，否则累加上它的值
            res = res + (cur < next ? -cur : cur);
        }
        res += transferToInt(chars[len - 1]);
        return res;
    }

    private static int transferToInt(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

}
