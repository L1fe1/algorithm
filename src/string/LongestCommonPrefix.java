package string;

/**
 * <a href="https://leetcode.cn/problems/longest-common-prefix/">...</a>
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        String common = strs[0];
        int index = 0;
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < common.length() && j < str.length(); j++) {
                if (common.charAt(j) == str.charAt(j)) {
                    index = j + 1;
                } else {
                    break;
                }
            }
            common = common.substring(0, index);
            index = 0;
        }
        return common;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"a", "a", "b"}));
    }
}
