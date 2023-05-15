package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * 请你返回「表现良好时间段」的最大长度。
 */
public class LongestWellPerformingInterval {
    public int longestWPI(int[] hours) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < hours.length; i++) {
            // 超过 8 小时，设置为 1，否则设置为 0
            hours[i] = hours[i] > 8 ? 1 : -1;
        }
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i];
            if (sum > 0) {
                // i 位置的前缀累加和大于 0，说明「劳累的天数」严格大于「不劳累的天数」
                ans = i + 1;
            } else {
                // 如果之前最早出现的某个位置的前缀和中有 sum - 1 的，那么该位置到 i 的累加和大于 0，记录「表现良好时间段」
                if (map.containsKey(sum - 1)) {
                    ans = Math.max(ans, i - map.get(sum - 1));
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
