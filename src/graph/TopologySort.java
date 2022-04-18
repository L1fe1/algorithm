package graph;

import java.util.*;

/**
 * 图的拓扑排序算法
 * 要求：有向图且其中没有环
 * 应用：事件安排、编译顺序
 * 1）在图中找到所有入度为0的点输出
 * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 */
public class TopologySort {
    // directed graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        // node -> 剩余入度 map
        Map<Node, Integer> inMap = new HashMap<>();
        // 所有入度为 0 的节点的队列
        Queue<Node> zeroQueue = new LinkedList<>();
        // 初始化
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }
        // 拓扑排序的结果，依次加入 results
        List<Node> results = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node cur = zeroQueue.poll();
            results.add(cur);
            // 节点弹出，相应的邻接节点入度 - 1
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return results;
    }
}
