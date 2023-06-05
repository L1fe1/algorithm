package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/generate-parentheses/submissions/">...</a>
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[2 * n];
        process(path, 0, 0, n, ans);
        return ans;
    }

    /**
     * @param path 0...i-1 位置选择的括号
     * @param i 当前来到 i 位置做决定
     * @param leftMinusRight 左括号减右括号的数量
     * @param leftRest 剩余的左括号数量
     * @param ans 所有有效括号组合
     */
    public void process(char[] path, int i, int leftMinusRight, int leftRest, List<String> ans) {
        if (i == path.length) {
            ans.add(String.valueOf(path));
        } else {
            if (leftRest > 0) {
                // 剩余左括号大于 0，可以选择左括号
                path[i] = '(';
                process(path, i + 1, leftMinusRight + 1, leftRest - 1, ans);
            }
            if (leftMinusRight > 0) {
                // 只有左括号数量多于右括号时，才能选择右括号
                path[i] = ')';
                process(path, i + 1, leftMinusRight - 1, leftRest, ans);
            }
        }
    }
}
