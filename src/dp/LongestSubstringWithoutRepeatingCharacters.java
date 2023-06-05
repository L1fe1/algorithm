package dp;

import java.util.Arrays;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int preAns = 1;
        // 某个字符之前出现的位置
        int[] preIndex = new int[256];
        Arrays.fill(preIndex, -1);
        char[] chars =  s.toCharArray();
        preIndex[chars[0]] = 0;
        int ans = 1;
        for (int i = 1; i < len; i++) {
            char c = chars[i];
            // 如果 i 位置的字符之前出现的位置到 i 位置的距离大于 i - 1 位置的的最长子串长度，
            // i 位置的最长子串长度为 i - 1 位置的的最长子串长度加 1；
            // 否则 i 位置的最长子串长度为 i 位置的字符之前出现的位置到 i 位置的距离
            preAns = Math.min(preAns + 1, i - preIndex[c]);
            ans = Math.max(ans, preAns);
            preIndex[c] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
