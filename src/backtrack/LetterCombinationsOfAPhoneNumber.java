package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/">...</a>
 */
public class LetterCombinationsOfAPhoneNumber {
    public static char[][] phone = new char[][]{
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        char[] path = new char[digits.length()];
        process(digits, 0, path, ans);
        return ans;
    }

    // [0...i-1] 的数字已经拨过了，形成的路径放在 path 里面，现在拨到了 i 位置的数字
    public static void process(String digits, int i, char[] path, List<String> ans) {
        if (i == digits.length()) {
            ans.add(String.valueOf(path));
        } else {
            char[] chars = phone[digits.charAt(i) - '2'];
            // 每种可能的路径都走一遍
            for (int j = 0; j < chars.length; j++) {
                path[i] = chars[j];
                process(digits, i + 1, path, ans);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
