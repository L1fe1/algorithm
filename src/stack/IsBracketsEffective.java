package stack;

import java.util.Stack;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 怎么判断一个括号字符串有效？
 */
public class IsBracketsEffective {
    public static boolean isBracketsEffective(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                count ++;
            } else {
                count --;
            }
            // 如果 count < 0，说明有无法匹配的右括号，返回 false
            if (count < 0) {
                return false;
            }
        }
        // 如果最终 count 为 0，说明所有左右括号都能匹配
        return count == 0;
    }

    public static boolean isBracketsEffectiveUseStack(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        // 字符串长度为奇数，不可能构成有效的括号字符串
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 左括号压栈
                stack.push(c);
            } else {
                if (stack.empty()) {
                    // 第一个字符为右括号或者匹配了一组有效括号之后先遇到了右括号，不合法
                    return false;
                }
                // 遇到右括号出栈
                stack.pop();
            }
        }
        // 如果最终 stack 为 空，说明所有左右括号都能匹配
        return stack.empty();
    }
}
