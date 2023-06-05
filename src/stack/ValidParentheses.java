package stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        int len = s.length();
        // 栈
        int[] stack = new int[len];
        int size = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '(' || chars[i] == '{' || chars[i] == '[') {
                // 遇到左括号，将对应的右括号入栈
                stack[size++] = chars[i] == '(' ? ')' : chars[i] == '{' ? '}' : ']';
            } else {
                // 遇到右括号
                if (size == 0) {
                    // 栈为空，无效
                    return false;
                }
                if (stack[--size] != chars[i]) {
                    // 左右括号匹配不上，无效
                    return false;
                }
            }
        }
        // 如果栈中还有未匹配的括号，无效
        return size == 0;
    }
}
