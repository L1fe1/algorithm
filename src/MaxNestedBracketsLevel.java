/**
 * 给定一个有效的括号字符串
 * 返回最大括号嵌套深度
 * 比如给定这样一个字符串 (())() ，最大嵌套深度就为 2
 */
public class MaxNestedBracketsLevel {
    public static int maxNestedBracketsLevel(String s) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                // count 能到达的最大值就是最大嵌套深度
                res = Math.max(res, ++ count);
            } else {
                count --;
            }
        }
        return res;
    }
}
