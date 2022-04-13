package stack;

import java.util.Stack;

/**
 * 【题目】一个整数数组 A，找到每个元素：右边第一个比我小的下标位置，没有则用 -1 表示。输入：[5, 2]
 * 输出：[1, -1]
 * 解释：因为元素 5 的右边离我最近且比我小的位置应该是 A[1]，最后一个元素 2 右边没有比 2 小的元素，所以应该输出 -1。
 */
public class FindRightSmall {
    public static int[] findRightSmall(int[] arr) {
        if (arr == null || arr.length < 1) {
            return new int[0];
        }
        // 结果数组
        int ans[] = new int[arr.length];
        // 维持一个递增栈记录元素下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 当有入栈元素不满足递增栈特性时，消除栈中不满足的元素
            while (!stack.empty() && arr[stack.peek()] > arr[i]) {
                // 消除时，记录结果
                ans[stack.peek()] = i;
                // 将更大的值出栈以维持递增栈的特性
                stack.pop();
            }
            // 如果满足递增栈特性，入栈
            stack.push(i);
        }
        // 栈中剩下的元素为找不到结果的元素，因此，将结果设置为 -1。
        while (!stack.empty()) {
            ans[stack.peek()] = -1;
            stack.pop();
        }
        return ans;
    }

    private static int findRightSmall(int[] A, int i) {
        for (int j = i + 1; j < A.length; j++) {
            if (A[j] < A[i]) {
                return j;
            }
        }
        return -1;
    }

    private static void check(int[] A, int[] ans) {
        if (A == null || A.length == 0) {
            return;
        }

        final int N = A.length;
        for (int i = 0; i < N; i++) {
            final int r = ans[i];
            if (r != findRightSmall(A, i)) {
                System.out.println("ERROR");
            }
        }
    }

    public static void doubleCheck(int[] A) {
        int[] ans = findRightSmall(A);
        check(A, ans);
    }

    private static int nextInt() {
        final double d = Math.random();
        final int i = (int) (d * 1000);
        return i;
    }

    public static void randomCheck() {
        for (int i = 0; i < 100; i++) {
            final int len = nextInt() + 1;
            int[] A = new int[len];
            for (int j = 0; j < len; j++) {
                A[j] = nextInt();
            }

            doubleCheck(A);
        }
    }

    public static void main(String[] args) {
        doubleCheck(new int[] { 5, 4 });
        doubleCheck(new int[] { 1, 2, 4, 9, 4, 0, 5 });
        randomCheck();
    }
}
