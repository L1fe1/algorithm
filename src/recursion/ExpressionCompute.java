package recursion;

import java.util.LinkedList;

/**
 * 实现一个基本的计算器来计算简单的表达式字符串。
 * 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 )
 * 整数除法需要向下截断
 * 你可以假定给定的表达式总是有效的。所有的中间结果的范围均满足 [-2^31, 2^31 - 1]
 * 注意：你不能使用任何将字符串作为表达式求值的内置函数，比如 eval()
 */
public class ExpressionCompute {
    public static int calculate(String str) {
        return process(str.toCharArray(), 0)[0];
    }

    private static int[] process(char[] chars, int i) {
        // 运算队列
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        // 中间结果
        int[] midRes;
        while (i < chars.length && chars[i] != ')') {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                // 收集数字
                cur = cur * 10 + (c - '0');
                i ++;
            } else if (c == '(') {
                // 遇到左括号，计算括号里面的表达式
                midRes = process(chars, i + 1);
                // 获取中间结果
                cur = midRes[0];
                // 获取完成上面的运算之后的位置
                i = midRes[1] + 1;
            } else {
                // 将表达式加入队列
                addExp(queue, cur, c);
                cur = 0;
                i ++;
            }
        }
        // 将最后收集的数字加入队列
        addExp(queue, cur, '+');
        // 从头到尾进行运算
        int ans = Integer.parseInt(queue.pollFirst());
        while (queue.size() > 1) {
            String op = queue.pollFirst();
            int num = Integer.parseInt(queue.pollFirst());
            ans += "+".equals(op) ? num : -num;
        }
        return new int[]{ans, i};
    }

    private static void addExp(LinkedList<String> queue, int num, char op) {
        if (!queue.isEmpty() && ("*".equals(queue.peekLast()) || "/".equals(queue.peekLast()))) {
            // 遇到 * 或 / 运算之后再加入队列
            num = "*".equals(queue.pollLast()) ? Integer.parseInt(queue.pollLast()) * num :
                    Integer.parseInt(queue.pollLast()) / num;
        }
        queue.addLast(String.valueOf(num));
        queue.addLast(String.valueOf(op));
    }

    public static void main(String[] args) {
        System.out.println(calculate("20*(1+2*3+(10/5))*(10+(4-2))"));
    }
}
