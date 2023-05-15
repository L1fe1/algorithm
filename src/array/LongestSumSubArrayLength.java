package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度
 * 如果不存在任意一个符合要求的子数组，则返回 0
 */
public class LongestSumSubArrayLength {
    public static int maxSubArrayLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        // 数组中有可能出现的所有前缀和与该前缀和最早出现的位置的映射
        Map<Integer, Integer> map = new HashMap<>();
        // 处理边界条件
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // i 位置的前缀和为 sum，如果找到前缀和为 k - sum 的位置，那么那个位置到 i 位置就满足和为 k 的条件
            if (map.containsKey(k - sum)) {
                ans = Math.max(ans, i - map.get(k - sum));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 0, -1, 0, 1, 4, 1, 5, 6};
        System.out.println(maxSubArrayLen(arr, 6));
    }
}
