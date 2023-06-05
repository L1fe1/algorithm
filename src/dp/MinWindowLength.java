package dp;

/**
 * <a href="https://leetcode.cn/problems/minimum-window-substring/">...</a>
 */
public class MinWindowLength {
    public static String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return "";
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        // t 中字符数量统计
        int[] target = new int[256];
        for (char c : tChars) {
            target[c] ++;
        }
        // t 所有字符数量
        int charNum = tLen;
        int l = 0;
        int r = 0;
        // 最小子串长度
        int min = Integer.MAX_VALUE;
        // 最小子串起始位置
        int minL = -1;
        // 最小子串终止位置
        int minR = -1;
        while (r < sLen) {
            if (--target[sChars[r]] >= 0) {
                // 如果遇到了 t 中的字符，且字符个数还没有满足，charNum 减一
                charNum --;
            }
            // t 中所有字符个数都得到了满足
            if (charNum == 0) {
                // 只要还能满足覆盖 t 中所有字符的条件，左指针就往前收缩窗口
                while (target[sChars[l]] < 0) {
                    target[sChars[l++]] ++;
                }
                // 更新最小子串信息
                if (min > r - l + 1) {
                    min = r - l + 1;
                    minL = l;
                    minR = r;
                }
                charNum ++;
                target[sChars[l++]] ++;
            }
            // 右指针往前走
            r ++;
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(minL, minR + 1);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
