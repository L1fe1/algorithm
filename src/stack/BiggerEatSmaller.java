package stack;

import java.util.Stack;

/**
 * 在水中有许多鱼，可以认为这些鱼停放在 x 轴上。再给定两个数组 Size，Dir，
 * Size[i] 表示第 i 条鱼的大小，Dir[i] 表示鱼的方向 （0 表示向左游，1 表示向右游）。
 * 这两个数组分别表示鱼的大小和游动的方向，并且两个数组的长度相等。鱼的行为符合以下几个条件:
 * 	1. 所有的鱼都同时开始游动，每次按照鱼的方向，都游动一个单位距离；
 * 	2. 当方向相对时，大鱼会吃掉小鱼；
 * 	3. 鱼的大小都不一样。
 * 输入：Size = [4, 2, 5, 3, 1], Dir = [1, 1, 0, 0, 0]
 * 输出：3
 */
public class BiggerEatSmaller {
    public static int biggerEatSmaller(int[] fishSize, int[] fishDirection) {
        // 如果鱼的数量小于等于1，那么直接返回鱼的数量
        if (fishSize.length <= 1) {
            return fishSize.length;
        }
        // 存储鱼的索引位置
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < fishSize.length; i ++) {
            // 当前的鱼是否被栈中的鱼吃掉了
            boolean hasEat = false;
            // 如果栈不为空，而且栈中索引位置的鱼和当前鱼的方向是相对的（右 -> <- 左）
            while (!stack.empty() && fishDirection[stack.peek()] == 1 && fishDirection[i] == 0) {
                // 如果栈顶的鱼比较大，那么把新来的吃掉
                if (fishSize[stack.peek()] > fishSize[i]) {
                    hasEat = true;
                    break;
                }
                // 否则将栈中索引位置的鱼吃掉，相应的进行出栈操作
                stack.pop();
            }
            // 如果新来的鱼没有被吃掉，压入栈中
            if (!hasEat) {
                stack.push(i);
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {
        int[] fishSize = new int[]{4, 2, 5, 3, 1};
        int[] fishDirection = new int[]{1, 1, 0, 0, 0};
        System.out.println(biggerEatSmaller(fishSize, fishDirection));;
    }
}
