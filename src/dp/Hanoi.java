package dp;

/**
 * 暴力递归就是尝试
 * 1，把问题转化为规模缩小了的同类问题的子问题
 * 2，有明确的不需要继续进行递归的条件(base case)
 * 3，有当得到了子问题的结果之后的决策过程
 * 4，不记录每一个子问题的解
 * 打印 n 层汉诺塔从最左边移动到最右边的全部过程
 */
public class Hanoi {
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    public static void leftToRight(int n) {
        // base case
        // 如果只剩下最上一层的圆盘，直接移动
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        // 把剩下 n - 1 层圆盘从左移到中
        leftToMid(n - 1);
        // 把底层的 n 移到右
        System.out.println("Move " + n + " from left to right");
        // 把剩下 n - 1 层圆盘从中移到右
        midToRight(n - 1);
    }

    public static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }

    public static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            move(n, "left", "right", "mid");
        }
    }

    // 将 n 个圆盘从 from 移动到 to，other 作为辅助
    public static void move(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move " + n + " from " + from + " to " + to);
            return;
        }
        // from 为 left，to 为 mid，other 为 right 作为辅助
        // 相当于将剩下 n - 1 个圆盘从 left 移到 right
        move(n - 1, from, other, to);
        System.out.println("Move " + n + " from " + from + " to " + to);
        // from 为 mid，to 为 right，other 为 left 作为辅助
        // 相当于将剩下 n - 1 个圆盘从 mid 移到 right
        move(n - 1, other, to, from);
    }

    public static void main(String[] args) {
        hanoi1(3);
        System.out.println("===========================");
        hanoi2(3);
    }
}
