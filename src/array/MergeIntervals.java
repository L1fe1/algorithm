package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/merge-intervals/">...</a>
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // 按 start 排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                // 当前区间的 start 大于前一个区间的 end，合并之前的区间
                intervals[index][0] = start;
                intervals[index++][1] = end;
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                // 否则，更新 end
                end = Math.max(end, intervals[i][1]);
            }
        }
        // 合并最后一个区间
        intervals[index][0] = start;
        intervals[index][1] = end;
        return Arrays.copyOf(intervals, index + 1);
    }
}
