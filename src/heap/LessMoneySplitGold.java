package heap;

import java.util.PriorityQueue;

/**
 * 一根金条需要分成指定长度的若干份（arr），计算最小的代价
 * 代价指的是当前参与分割的金条的长度
 */
public class LessMoneySplitGold {
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int gold : arr) {
            heap.add(gold);
        }
        int ans = 0;
        while (heap.size() > 1) {
            // 取出小根堆最上面的两根金条进行累加，其结果即为原始金条分割为当前的两根金条的代价
            int originalGold = heap.poll() + heap.poll();
            ans += originalGold;
            heap.add(originalGold);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        System.out.println(lessMoney(arr));
    }
}
