package binary_search;

import java.util.HashMap;

/**
 * 定义何为 step sum？
 * 比如 680，680 + 68 + 6 = 754，
 * 那么 680 的 step sum 为 754。
 * 给定一个正数 num，判断它是不是某个数的 step sum
 */
public class IsStepSum {
    public static boolean isStepSum(int num) {
        int l = 0, r = num;
        int mid;
        int stepSum;
        while (l <= r) {
            mid = (l + r) / 2;
            stepSum = stepSum(mid);
            if (stepSum > num) {
                r = mid - 1;
            } else if (stepSum < num) {
                l = mid + 1;
            } else {
                System.out.println(num + " 是 " + mid + " 的 step sum");
                return true;
            }
        }
        return false;
    }

    private static int stepSum(int num) {
        int stepSum = 0;
        while (num > 0) {
            stepSum += num;
            num /= 10;
        }
        return stepSum;
    }

    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(stepSum(i), i);
        }
        return map;
    }

    public static void main(String[] args) {
        int max = 1000000;
        int maxStepSum = stepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
