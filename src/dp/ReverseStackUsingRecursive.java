package dp;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，
 * 不能申请额外的数据结构，
 * 只能使用递归函数。 如何实现?
 */
public class ReverseStackUsingRecursive {
    // 假如栈中有 1 2 3
    // r1 bot = 3, stack = {1,2}, call r2
    // r2 bot = 2, stack = {1}, call r3
    // r3 bot = 1, stack = {}, call r4
    // r4 return
    // r3 push bot 1, stack = {1}
    // r2 push bot 2, stack = {2,1}
    // r1 push bot 3, stack = {3,2,1}
    public static void reverse(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        }
        int bot = process(stack);
        reverse(stack);
        stack.push(bot);
    }

    // 弹出栈底元素并返回
    // 假如栈中有 1 2 3
    // p1 result = 1, stack = {2,3}, last = p2
    // p2 result = 2, stack = {3}, last = p3
    // p3 result = 3, stack = {}, return 3
    // p2 last = p3 3, return last 3, push result 2, stack = {2}
    // p1 last = p2 3, return last 3, push result 1, stack = {1,2}
    public static int process(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.empty()) {
            // 到达栈底，返回
            return result;
        } else {
            // 将栈底的 result 记录下来
            int last = process(stack);
            // 将非栈底的元素压回去
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
