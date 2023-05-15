package disjoint_set_union;

import java.util.HashMap;
import java.util.Map;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 */
public class MostStonesRemovedWithSameRowOrColumn {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        // 首次出现 x 坐标 -> 对应石头编号的映射
        Map<Integer, Integer> rowMap = new HashMap<>();
        // 首次出现 y 坐标 -> 对应石头编号的映射
        Map<Integer, Integer> colMap = new HashMap<>();
        CouplesHoldingHands.UnionFind unionFind = new CouplesHoldingHands.UnionFind(n);
        for (int i = 0; i < n; i++) {
            int row = stones[i][0];
            int col = stones[i][1];
            // 如果 i 位置的石头对应的 row 没出现过，添加到 rowMap 中，否则合并第 i 号石头和第一次出现 row 对应的石头
            if (!rowMap.containsKey(row)) {
                rowMap.put(row, i);
            } else {
                unionFind.union(i, rowMap.get(row));
            }
            // 如果 i 位置的石头对应的 col 没出现过，添加到 colMap 中，否则合并第 i 号石头和第一次出现 col 对应的石头
            if (!colMap.containsKey(col)) {
                colMap.put(col, i);
            } else {
                unionFind.union(i, colMap.get(col));
            }
        }
        // 并查集的数量即为可以剩余的石头数量，也就是最多可以移除 n - unionFind.sets 个石头
        return n - unionFind.sets;
    }
}
