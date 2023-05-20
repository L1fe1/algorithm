package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，
 * 其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 构建邻接表
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        // 小根堆，按照传递时间排序
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 添加起点 k
        heap.add(new int[]{k, 0});
        int num = 0;
        int max = 0;
        // 是否计算过时间
        boolean[] computed = new boolean[n + 1];
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int node = cur[0];
            int delay = cur[1];
            // 如果当前节点没计算过时间，计算
            if (!computed[node]) {
                // 统计计算过的节点数，也就是能到达的节点数
                num ++;
                // 统计最大延迟时间
                max = Math.max(max, delay);
                computed[node] = true;
                // 将相邻的节点添加到 heap 中
                for (int[] next : graph.get(node)) {
                    int nextNode = next[0];
                    if (!computed[nextNode]) {
                        heap.add(new int[]{nextNode, next[1] + delay});
                    }
                }
            }
        }
        // 如果有无法到达的节点，返回 -1，否则返回 max
        return num < n ? -1 : max;
    }
}
