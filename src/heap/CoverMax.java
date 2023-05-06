package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有 m 个线段，求最大重叠线段数
 * 比如对于线段 [1, 3]，[2, 4]，[2, 5]，[4, 6]
 * 最大重叠线段数为 3：[1, 3]，[2, 4]，[2, 5]
 */
public class CoverMax {
    public static int maxCover(int[][] m) {
        int ans = 0;
        // 按线段起点从小到大排序
        Arrays.sort(m, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int[] line : m) {
            // 把当前线段起点以前的所有线段弹出
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            // 将线段终点加入小根堆
            heap.add(line[1]);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3}, {2, 4}, {2, 5}, {4, 6}};
        System.out.println(maxCover(m));
    }
}
